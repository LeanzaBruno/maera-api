package dev.kertz.core;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import dev.kertz.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class ReportDownloader {

	/**
	 * Gets taf from a list of airports
	 * @param airports list of airports
	 * @return list of string containing the tafs
	 */
	public static List<Taf> getTafs(List<Airport> airports) {
		List<Taf> tafs = new ArrayList<>();
		StringBuilder url = new StringBuilder(WeatherBriefing.TAF.url);

		for(Airport airport : airports )
			url.append(airport.getICAO()).append("+");
		
		try {
			Document page = Jsoup.connect(url.toString()).get();
			tafs = Parser.parseTafs(page);
		}
		catch(Exception e) { e.printStackTrace(); }
		return tafs;
	}

	
	/**
	 * Gets metar from a list of airports
	 * @param airports list
	 * @return a list of string containing the metars
	 */
	public static List<Metar> getMetars(List<Airport> airports) {
		StringBuilder url = new StringBuilder(WeatherBriefing.METAR.url);
		for(Airport airport : airports )
			url.append(airport.getICAO()).append("+");
			
		List<Metar> metars = new ArrayList<>();
		
		try {
			Document page = Jsoup.connect(url.toString()).get();
			metars = Parser.parseMetars(page);
		}
		catch(Exception e) { e.printStackTrace(); }
		return metars;
	}

	public static Pronarea getPronarea(Fir fir){
		String url = WeatherBriefing.PRONAREA.url;
		url = url.replace("??", String.valueOf(fir.getCode()) );
		Pronarea pronarea = new Pronarea();

		try {
			Document page = Jsoup.connect(url).get();
			pronarea.setRaw( Parser.getReportsFromPage(page).get(0) );
		}
		catch(Exception exception) {
			System.out.println("Error: Ocurrió un error al intentar descargar el pronarea.");
		}
		return pronarea;
	}

}
