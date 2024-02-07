package dev.kertz.core;

import java.util.ArrayList;
import java.util.List;
import dev.kertz.model.Metar;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Parser{
	private final static String QUERY = "td[width]";

	/**
	 * Parse the html page to obtain the reports
	 * @param html the html of the page
	 * @return the reports list
	 */
	static List<Metar> parseMetars(Document html) {
		Elements results = html.select(QUERY);

		List<Metar> metars = new ArrayList<>();

		for(Element result : results )
			metars.add( new Metar(result.text()) );
		return metars;
	}

}
