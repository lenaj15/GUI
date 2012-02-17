import html.Tag;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class HtmlHorizontalTable extends HtmlGenerator {

	public HtmlHorizontalTable(Map<Object, ArrayList<CalendarEvent>> map) {
		super(map);
	}

	@SuppressWarnings("unchecked")
	public void generateOutput()
			throws IOException {
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		Tag body = new Tag("body");
		
		Tag table = new Tag("table", "border=5 cellpadding=3 cellspacing=0 width=500");
		body.add(table);
		int rowIndex = 1;
		
		for (Object key: outputMap.keySet())
		{
			Tag tr = new Tag("tr", "align=center");
			Tag firstEntry = new Tag("td", "align=center valign=center bgcolor=#9c9c9c");
			firstEntry.add(key.toString());
			tr.add(firstEntry);
			for (CalendarEvent value: outputMap.get(key))
			{
				Tag subEntry = new Tag("td", "align=center valign=center bgcolor=#eeeeee");
				subEntry.add(value.getMyTitle()); // or value.getSummaries();
//				generateSubPage(value, rowIndex);
				tr.add(subEntry);
			}
			table.add(tr);
			rowIndex++;
		}
		
		html.add(head);
		html.add(body);
		printResult(html);
		createSubFolder();
	}

}
