package dev.kertz.service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dev.kertz.dto.*;
import dev.kertz.exception.*;
import dev.kertz.model.Airport;
import dev.kertz.model.FIR;
import dev.kertz.repository.AirportRepository;
import dev.kertz.repository.FirRepository;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Master service for reports
 */
@Service
public class ReportProviderService {

	private List<String> userAgents = new LinkedList<>(Arrays.asList(
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.3",
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.3",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:126.0) Gecko/20100101 Firefox/126.",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.3",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.3",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 Edg/123.0.0.",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.4",
		"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115."
	));
	private static final int MAX_TRIES = 100;

	@Getter
	private enum ReportType {
		METAR("https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar"),
		SPECI("https://ssl.smn.gob.ar/mensajes/index.php?observacion=speci&operacion=consultar"),
		TAF("https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar"),
		PRONAREA("https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar"),
		SIGMET("https://ssl.smn.gob.ar/mensajes/index.php?observacion=sigmet&operacion=consultar");

		private final String url;
		ReportType(String url) {
			this.url = url;
		}
	}


	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private FirRepository firRepository;

	@Autowired
	private ReportDecoderService decoder;


	public METAR getMETAR(String icao){
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
		String raw = downloadReport(ReportType.METAR, airport);
		return new METAR(raw);
	}


	public TAF getTAF(String icao){
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow(() -> new AirportNotFoundException(icao));

		if( ! airport.isTafStation())
			throw new InvalidTafStationException(airport);

		String raw = downloadReport(ReportType.TAF, airport);
		return new TAF(raw);
	}


	public SPECI getSPECI(String icao){
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
		String report = downloadReport(ReportType.SPECI, airport);
		if(report.isEmpty())
			throw new SpeciNotFoundException(icao);
		return new SPECI(report);
	}


	public PRONAREA getPRONAREA(String firIcao){
		FIR fir = firRepository.findByIcaoIgnoreCase(firIcao).orElseThrow( () -> new FirNotFoundException(firIcao));
		String raw = downloadReport(ReportType.PRONAREA, fir.getCapitalAirport());
		return new PRONAREA(raw);
	}


	public SIGMET getSIGMET(String firIcao){
		FIR fir = firRepository.findByIcaoIgnoreCase(firIcao).orElseThrow( () -> new FirNotFoundException(firIcao));
		String raw = downloadReport(ReportType.SIGMET, fir.getCapitalAirport());
		return new SIGMET(raw);
	}

	public List<SIGMET> getAllSIGMET() {
		List<Airport> firList = firRepository.findAll().stream().map(FIR::getCapitalAirport).toList();
		List<String> reports = downloadReports(ReportType.SIGMET, firList);

		List<SIGMET> sigmets = new LinkedList<>();

		for (String report : reports)
			sigmets.add(new SIGMET(report));

		return sigmets;
	}


	public List<SPECI> getAllSPECI(){
		List<String> reports = downloadReports(ReportType.SPECI, airportRepository.findAll());
		List<SPECI> specis = new LinkedList<>();

		for(String report : reports)
			specis.add(new SPECI(report));

		return specis;
	}


	/**
	 * Downloads and returns a list of reports based on an airports list and a type of report
	 * @param airport the airport
	 * @param type the type of the report
	 * @return the list of reports
	 */
	private String downloadReport(ReportType type, Airport airport) {
		StringBuilder urlBuilder = new StringBuilder(type.getUrl())
				.append('&')
				.append(airport.getWMO())
				.append("=on")
				.append('&')
				.append(airport.getWMO()-1)
				.append("=on");

		System.out.println("\n\nDownloading " + type.name() + " of " + airport.getICAO() + " from " + urlBuilder);

		Collections.shuffle(userAgents);
		String rawReport = null;
		for(int i = 0 ; rawReport == null && i < userAgents.size() ; ++i) {
			String userAgent = userAgents.get(i);
			System.out.print("Trying with " + userAgent + ": ");
			try {
				Document page = Jsoup.connect(urlBuilder.toString()).userAgent(userAgent).get();
				rawReport = parseReportFromPage(page).getFirst();
				System.out.println("OK");
			} catch (IOException e) {
				System.out.println("Failed");
			}
		}
		/*
		for(int i = 0 ; rawReport == null && i < USER_AGENTS.length ; ++i) {
			String userAgent = USER_AGENTS[i];
			System.out.println("\tTrying with user agent " + userAgent + ": ");

			for(int j = 0 ; rawReport == null && j < MAX_TRIES ; ++j){
				System.out.print("\t\tTry " + (j + 1) + ": ");
				try {
					Document page = Jsoup.connect(urlBuilder.toString()).userAgent(userAgent).get();
					rawReport = parseReportFromPage(page).getFirst();
					System.out.println("OK");
				} catch (IOException e) {
					System.out.println("Failed");
				}
			}

		}
		*/

		return rawReport;
	}


	/**
	 * Downloads and returns a list of reports based on an airports list and a type of report
	 * @param airports the airports list
	 * @param type the type of the report
	 * @return the list of reports
	 */
	private List<String> downloadReports(ReportType type, List<Airport> airports) {
		StringBuilder urlBuilder = new StringBuilder(type.getUrl());

		for(Airport airport : airports)
			urlBuilder.append('&')
					.append(airport.getWMO())
					.append("=on")
					.append('&')
					.append(airport.getWMO()-1)
					.append("=on");

		List<String> rawReports = new LinkedList<>();
		int tries = 0;
		while(true){
			System.out.println("Downloading " + type.name() + " from " + urlBuilder + " (try " + (tries+1) + ")");
			try {
				Document page = Jsoup.connect(urlBuilder.toString()).userAgent("Mozilla").get();
				rawReports = parseReportFromPage(page);
				break;
			}
			catch(IOException e) {
				if( tries < 3) ++tries;
				else break;
			}
		}
		return rawReports;
	}


	/**
	 * Extracts all reports from the HTML document and returns them as a list of strings
	 * @param html the document where the report(s) is/are.
	 * @return the list of reports
	 */
	private List<String> parseReportFromPage(Document html){
		final String QUERY = "input[type=hidden]";
		Pattern pattern = Pattern.compile("(?<=\\d{2} - \\d{2}:\\d{2} - ).*");
		Elements results = html.select(QUERY);
		List<String> rawReports = new LinkedList<>();
		for(Element result : results ) {
			Matcher matcher = pattern.matcher(result.attribute("value").getValue());
			if(matcher.find())
				rawReports.add(matcher.group());
		}
		return rawReports;
	}


	/*
	private List<String> getCoordinates(String raw){
		Pattern pattern = Pattern.compile("S(?<southDeg>\\d{2})(?<southMin>\\d{2}) W(?<westDeg>\\d{3})(?<westMin>\\d{2})");
		Matcher matcher = pattern.matcher(raw);
		List<String> coordinates = new LinkedList<>();
		while(matcher.find()){
			String southDeg = matcher.group("southDeg") + "°";
			String southMin = matcher.group("southMin") + "'";
			String westDeg = matcher.group("westDeg") + "°";
			String westMin = matcher.group("westMin") + "'";
			coordinates.add('S' + southDeg + southMin + " W" + westDeg + westMin);
		}
		return coordinates;
	}
	*/
}