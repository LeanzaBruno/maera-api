package dev.kertz.core;

import java.util.ArrayList;
import java.util.List;
import dev.kertz.model.Metar;
import dev.kertz.model.Taf;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Parser{
	private final static String QUERY = "td[width]";

	static List<Metar> parseMetars(Document html) {
		Elements results = html.select(QUERY);

		List<Metar> metars = new ArrayList<>();

		for(Element result : results )
			metars.add( new Metar(result.text()) );
		return metars;
	}

	static List<Taf> parseTafs(Document html) {
		Elements results = html.select(QUERY);

		List<Taf> tafs = new ArrayList<>();

		for(Element result : results )
			tafs.add( new Taf( result.text()) );
		return tafs;
	}

	static List<String> getReportsFromPage(Document html){
		Elements results = html.select(QUERY);
		List<String> reports = new ArrayList<>();
		for(Element result : results )
			reports.add( result.text() );
		return reports;
	}
}
