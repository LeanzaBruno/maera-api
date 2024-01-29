package dev.kertz.core;

import java.util.ArrayList;
import java.util.List;

import dev.kertz.model.Fir;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import dev.kertz.model.Airport;

public final class ReportDownloader {

	/**
	 * Gets taf from a list of airports
	 * @param airports list of airports
	 * @return list of string containing the tafs
	 */
	public static List<String> getTafs(List<Airport> airports) {
		List<String> taf = new ArrayList<>();
		String url = WeatherReport.TAF.url;

		for(Airport airport : airports )
			url += airport.getICAO() + "+";
		
		try {
			Document page = Jsoup.connect(url).get();
			taf = Parser.getReports(page);
		}
		catch(Exception e) { e.printStackTrace(); }
		return taf;
	}
	
	
	/**
	 * Gets metar from a list of airports
	 * @param airports list
	 * @return a list of string containing the metars
	 */
	public static List<String> getMetars(List<Airport> airports) {
		String url = WeatherReport.METAR.url;
		for(Airport airport : airports )
			url += airport.getICAO() + "+";
			
		List<String> metars = new ArrayList<>();
		
		try {
			Document page = Jsoup.connect(url).get();
			metars = Parser.getReports(page);
		}
		catch(Exception e) { e.printStackTrace(); }
		return metars;
	}

	/**
	 * Gets the pronarea of a fir
	 * @param fir
	 * @return
	 */
	public static String getPronarea(Fir fir){
		String url = WeatherReport.PRONAREA.url;
		url = url.replace("??", String.valueOf(fir.getCode()) );
		String pronarea = "";

		try {
			Document page = Jsoup.connect(url).get();
			pronarea = Parser.getReports(page).get(0);
		}
		catch(Exception exception) { exception.printStackTrace(); }
		return pronarea;
	}

}
