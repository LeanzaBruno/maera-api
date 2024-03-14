package dev.kertz.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import dev.kertz.model.Airport;
import dev.kertz.model.Fir;
import dev.kertz.model.Report;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service
public final class ReportDownloaderService {

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

	/**
	 * Wrapper for single metar report
	 */
	public Optional<Report> getMetar(Airport airport){
		return getReports( List.of(airport), "metar").stream().findFirst();
	}


	/**
	 * Wrapper for single taf report
	 */
	public Optional<Report> getTaf(Airport airport){
		return getReports( List.of(airport), "taf").stream().findFirst();
	}


	/**
	 * Wrapper for single speci report
	 */
	public Optional<Report> getSpeci(Airport airport){
		return getReports(List.of(airport), "speci").stream().findFirst();
	}


	/**
	 * Wrapper for single pronarea report
	 */
	public Optional<Report> getPronarea(Fir fir){
		return getPronareas(List.of(fir)).stream().findFirst();
	}


	/**
	 * Wrapper for multiple metar reports
	 */
	public List<Report> getMetars(List<Airport> airports) {
		return getReports(airports, "metar");
	}


	/**
	 * Wrapper for multiple taf reports
	 */
	public List<Report> getTafs(List<Airport> airports) {
		return getReports(airports, "taf");
	}


	/**
	 * Wrapper for multiple speci reports
	 */
	public List<Report> getSpecis(List<Airport> airports){
		return getReports(airports, "speci");
	}


	/**
	 * Downloads and returns a list of reports based on an airports list and a type of report
	 * @param airports list
	 * @param type the type of the report
	 * @return the list of reports
	 */
	public List<Report> getReports(List<Airport> airports, String type) {
		StringBuilder urlBuilder = new StringBuilder(
				switch(type) {
					case "metar" -> ReportType.METAR.getUrl();
					case "taf" -> ReportType.TAF.getUrl();
					case "speci" -> ReportType.SPECI.getUrl();
					case "sigmet" -> ReportType.SIGMET.getUrl();
					default -> throw new NoSuchElementException();
			}
		);

		airports.forEach(airport -> urlBuilder.append('&').append(airport.getWMO()).append("=on"));

		List<Report> reports = new LinkedList<>();
		try {
			Document page = Jsoup.connect(urlBuilder.toString()).get();
			List<String> rawReports = getRawReports(page);

			for(int index = 0 ; index < rawReports.size() ; ++index)
				reports.add( new Report(type, rawReports.get(index), airports.get(index)) );
		}
		catch(Exception e) {
			System.out.println("Error: Reports couldn't be downloaded");
		}
		return reports;
	}


	/**
	 * Downloads and returns the pronarea reports of a list of firs
	 * @param firList the fir list
	 * @return the reports
	 */
	public List<Report> getPronareas(List<Fir> firList) {
		StringBuilder urlBuilder = new StringBuilder(ReportType.PRONAREA.getUrl());
		firList.forEach( fir -> urlBuilder.append('&').append( fir.getCapitalAirport().getWMO() ).append("=on") );

		List<Report> reports = new LinkedList<>();
		try {
			Document page = Jsoup.connect(urlBuilder.toString()).get();
			List<String> rawReports = getRawReports(page);

			for(int index = 0 ; index < rawReports.size() ; ++index)
				reports.add( new Report("pronarea", rawReports.get(index), firList.get(index).getCapitalAirport() ) );
		}
		catch(Exception e) {
			System.out.println("Error: Reports couldn't be downloaded");
		}
		return reports;
	}


	private List<String> getRawReports(Document html){
		final String QUERY = "td[width]";
		Elements results = html.select(QUERY);
		List<String> reports = new LinkedList<>();
		for(Element result : results )
			reports.add( result.text() );
		return reports;
	}
}
