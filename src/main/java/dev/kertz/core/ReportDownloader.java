package dev.kertz.core;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import dev.kertz.model.Airport;

public final class ReportDownloader {
	
	/**
	 * Gets metar from a single airport
	 * @param aiport
	 * @return the metar
	 */
	public static String getMetar(Airport airport) {
		String url = WeatherReport.METAR.url + airport.getICAO();
		String metar = "";
		
		try {
			Document page = Jsoup.connect(url).get();
			metar = Parser.getReports(page, WeatherReport.METAR).get(0);
		}
		catch(Exception e) { e.printStackTrace(); }
		return metar;
	}


	/**
	 * Gets taf from a list of airports
	 * @param airport list
	 * @return a list of string containing the tafs
	 */
	public static List<String> getTafs(List<Airport> airports) {
		List<String> taf = new ArrayList<>();
		String url = WeatherReport.TAF.url;

		for(Airport airport : airports )
			url += airport.getICAO() + "+";
		
		try {
			Document page = Jsoup.connect(url).get();
			taf = Parser.getReports(page, WeatherReport.TAF);
		}
		catch(Exception e) { e.printStackTrace(); }
		return taf;
	}
	
	
	/**
	 * Gets metar from a list of airports
	 * @param airport list
	 * @return a list of string containing the metars
	 */
	public static List<String> getMetars(List<Airport> airports) {
		String url = WeatherReport.METAR.url;
		for(Airport airport : airports )
			url += airport.getICAO() + "+";
			
		List<String> metars = new ArrayList<>();
		
		try {
			Document page = Jsoup.connect(url).get();
			metars = Parser.getReports(page, WeatherReport.METAR);
		}
		catch(Exception e) { e.printStackTrace(); }
		return metars;
	}

}
