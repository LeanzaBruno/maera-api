package dev.kertz.core;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import dev.kertz.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class ReportDownloader {

	/**
	 * Wrapper for single metar report
	 */
	public static Optional<Report> getMetar(Airport airport){
		return getReports( List.of(airport), "metar").stream().findFirst();
	}


	/**
	 * Wrapper for single taf report
	 */
	public static Optional<Report> getTaf(Airport airport){
		return getReports( List.of(airport), "taf").stream().findFirst();
	}


	/**
	 * Wrapper for single speci report
	 */
	public static Optional<Report> getSpeci(Airport airport){
		return getReports(List.of(airport), "speci").stream().findFirst();
	}


	/**
	 * Wrapper for single pronarea report
	 */
	public static Optional<Report> getPronarea(Fir fir){
		return getPronareas(List.of(fir)).stream().findFirst();
	}


	/**
	 * Wrapper for multiple metar reports
	 */
	public static List<Report> getMetars(List<Airport> airports) {
		return getReports(airports, "metar");
	}


	/**
	 * Wrapper for multiple taf reports
	 */
	public static List<Report> getTafs(List<Airport> airports) {
		return getReports(airports, "taf");
	}


	/**
	 * Wrapper for multiple speci reports
	 */
	public static List<Report> getSpecis(List<Airport> airports){
		return getReports(airports, "speci");
	}


	/**
	 * Downloads and returns a list of reports based on an airports list and a type of report
	 * @param airports list
	 * @param type the type of the report
	 * @return the list of reports
	 */
	public static List<Report> getReports(List<Airport> airports, String type) {
		StringBuilder urlBuilder = new StringBuilder(
				switch(type) {
					case "metar" -> ReportType.METAR.getUrl();
					case "taf" -> ReportType.TAF.getUrl();
					default -> ReportType.SPECI.getUrl();
				}
		);

		airports.forEach(airport -> urlBuilder.append('&').append(airport.getWMO()).append("=on"));

		List<Report> reports = new LinkedList<>();
		try {
			Document page = Jsoup.connect(urlBuilder.toString()).get();
			List<String> rawReports = Parser.getRawReports(page);

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
	public static List<Report> getPronareas(List<Fir> firList) {
		StringBuilder urlBuilder = new StringBuilder(ReportType.PRONAREA.getUrl());
		firList.forEach( fir -> urlBuilder.append('&').append( fir.getCapitalAirport().getWMO() ).append("=on") );

		List<Report> reports = new LinkedList<>();
		try {
			Document page = Jsoup.connect(urlBuilder.toString()).get();
			List<String> rawReports = Parser.getRawReports(page);

			for(int index = 0 ; index < rawReports.size() ; ++index)
				reports.add( new Report("pronarea", rawReports.get(index), firList.get(index).getCapitalAirport() ) );
		}
		catch(Exception e) {
			System.out.println("Error: Reports couldn't be downloaded");
		}
		return reports;
	}
}
