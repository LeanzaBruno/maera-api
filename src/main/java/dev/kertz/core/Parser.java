package dev.kertz.core;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Parser{
		private final static String FORM_TAG = "form[name='imprimir']";
		private final static String RESULT_HEADER = "tr[class=result]";
		private final static String TABLE_TAG = "table";
		private final static String REPORT =  "td[width]";

		/**
		 * Parse the html page to obtain the report
		 * @param html the html of the page
		 * @param type the type of the report
		 * @return the report parsed
		 */
		static String getReport(Document html, WeatherReport type) {
			Elements resultForm = html.select(FORM_TAG);
			Elements table = resultForm.select(TABLE_TAG);
			Elements result = table.select(RESULT_HEADER);
			if( result.isEmpty())
				return "No se encontró " + type.name();
			return result.select(REPORT).text();
		}


		static List<String> getReports(Document html, WeatherReport type) {
			Elements results = html.select(RESULT_HEADER);
			List<String> reports = new ArrayList<>();
			
			for(Element e : results )
				reports.add( e.text() );
			
			return reports;
		}

}
