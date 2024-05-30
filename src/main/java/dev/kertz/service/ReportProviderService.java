package dev.kertz.service;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dev.kertz.dto.*;
import dev.kertz.exception.*;
import dev.kertz.model.Airport;
import dev.kertz.model.FIR;
import dev.kertz.repository.AirportRepository;
import dev.kertz.repository.FirRepository;
import lombok.Getter;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


/**
 * Master service for reports
 */
@Service
public class ReportProviderService {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0";

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
		String raw = downloadReport(ReportType.METAR, airport.getWMO());
		return new METAR(raw);
	}


	public TAF getTAF(String icao){
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow(() -> new AirportNotFoundException(icao));

		if( ! airport.isTafStation())
			throw new InvalidTafStationException(airport);

		String raw = downloadReport(ReportType.TAF, airport.getWMO());
		return new TAF(raw);
	}


	public SPECI getSPECI(String icao){
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
		String report = downloadReport(ReportType.SPECI, airport.getWMO());
		if(report.isEmpty())
			throw new SpeciNotFoundException(icao);
		return new SPECI(report);
	}


	public PRONAREA getPRONAREA(String firIcao){
		FIR fir = firRepository.findByIcaoIgnoreCase(firIcao).orElseThrow( () -> new FirNotFoundException(firIcao));
		String raw = downloadReport(ReportType.PRONAREA, fir.getCapitalAirport().getWMO());
		return new PRONAREA(raw);
	}


	public SIGMET getSIGMET(String firIcao){
		FIR fir = firRepository.findByIcaoIgnoreCase(firIcao).orElseThrow( () -> new FirNotFoundException(firIcao));
		String raw = downloadReport(ReportType.SIGMET, fir.getCapitalAirport().getWMO());
		return new SIGMET(raw);
	}


	public List<SPECI> getAllSPECI(){
		List<String> specis = downloadReports(ReportType.SPECI, airportRepository.findAll().stream().map(Airport::getWMO).toList());
		return specis.stream().map(SPECI::new).toList();
	}


	/**
	 * Downloads and returns a list of reports based on an airports list and a type of report
	 * @param station station
	 * @param type the type of the report
	 * @return the list of reports
	 */
	private String downloadReport(ReportType type, Integer station) {
		StringBuilder urlBuilder = new StringBuilder(type.getUrl()).append('&').append(station).append("=on");

		System.out.println("URL: " + urlBuilder);

		String rawReport = null;
		int tries = 0;
		while(true){
			System.out.println("try number: " + (tries+1) + '\n');
			try {
				Document page = Jsoup.connect(urlBuilder.toString()).userAgent(USER_AGENT).get();
				rawReport = parseReportFromPage(page).getFirst();
				break;
			}
			catch(IOException e) {
				if( tries < 3) ++tries;
				else break;
			}
		}
		return rawReport;
	}


	/**
	 * Downloads and returns a list of reports based on an airports list and a type of report
	 * @param stations list
	 * @param type the type of the report
	 * @return the list of reports
	 */
	private List<String> downloadReports(ReportType type, List<Integer> stations) {
		StringBuilder urlBuilder = new StringBuilder(type.getUrl());

		for(Integer wmo : stations)
			urlBuilder.append('&').append(wmo).append("=on");
		
		System.out.println("URL: " + urlBuilder);

		List<String> rawReports = new LinkedList<>();
		int tries = 0;
		while(true){
			System.out.println("try number: " + (tries+1) + '\n');
			try {
				Document page = Jsoup.connect(urlBuilder.toString()).userAgent(USER_AGENT).get();
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