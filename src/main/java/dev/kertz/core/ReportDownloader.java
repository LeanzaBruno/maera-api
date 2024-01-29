package dev.kertz.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
			metar = Parser.getReport(page, WeatherReport.METAR);
		}
		catch(Exception e) { e.printStackTrace(); }
		return metar;
	}


	/**
	 * Gets taf from a single airport
	 * @param airport 
	 * @return 
	 */
	public static String getTaf(Airport airport) {
		String url = WeatherReport.TAF.url + airport.getICAO();
		String taf = "";
		
		try {
			Document page = Jsoup.connect(url).get();
			taf = Parser.getReport(page, WeatherReport.TAF);
		}
		catch(Exception e) { e.printStackTrace(); }
		return taf;
	}
	
	
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
