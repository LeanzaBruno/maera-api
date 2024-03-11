package dev.kertz.core;

import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Parser{
	private final static String QUERY = "td[width]";

	static List<String> getRawReports(Document html){
		Elements results = html.select(QUERY);
		List<String> reports = new LinkedList<>();
		for(Element result : results )
			reports.add( result.text() );
		return reports;
	}
}
