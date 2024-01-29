package dev.kertz.core;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Parser{
		private final static String QUERY =  "td[width]";

		/**
		 * Parse the html page to obtain the report
		 * @param html the html of the page
		 * @return the report parsed
		 */
		static List<String> getReports(Document html) {
			Elements results = html.select(QUERY);
			List<String> reports = new ArrayList<>();
			
			for(Element e : results )
				reports.add( e.text() );
			
			return reports;
		}

}
