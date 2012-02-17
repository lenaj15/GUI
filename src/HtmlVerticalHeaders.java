import html.Tag;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class HtmlVerticalHeaders extends HtmlGenerator {

	public HtmlVerticalHeaders(Map<Object, ArrayList<CalendarEvent>> map) {
		super(map);
	}

	@SuppressWarnings("unchecked")
	public void generateOutput()
			throws IOException {
		// Generate HTML file
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		Tag body = new Tag("body");
		
		int rowIndex = 1;
		// Vertical header creation
		for (Object key: outputMap.keySet())
		{
			Tag h1 = new Tag("h1", "align=center");
			h1.add(key.toString());
			body.add(h1);
			for (CalendarEvent value: outputMap.get(key))
			{
				Tag h4 = new Tag("h4", "align=center");
				h4.add(value.getMyTitle()); // or value.getSummaries();
//				generateSubPage(value, rowIndex);
				body.add(h4);
			}
			rowIndex++;
		}
		
		html.add(head);
		html.add(body);
		printResult(html);
	}

}
