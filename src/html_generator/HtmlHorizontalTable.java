package html_generator;

import html.Tag;

import java.io.IOException;
import java.util.ArrayList;

import event.CalendarEvent;

@SuppressWarnings("unchecked")
public class HtmlHorizontalTable extends HtmlGenerator {

	public HtmlHorizontalTable(ArrayList<CalendarEvent> list) {
		super(list);
	}

	public void generateOutput() throws IOException {
		createSubFolder();
		
		Tag html = generateHeader();
		Tag body = new Tag("body");
		
		Tag table = new Tag("table", "border=5 cellpadding=3 cellspacing=0 width=500");
		body.add(table);
		int fileIndex = 1;
		
		for (Object key: outputMap.keySet())
		{
			Tag tr = generateKeyEntry(key);
			for (CalendarEvent value: outputMap.get(key))
			{
				tr = generateValueEntry(tr, value, fileIndex);
				fileIndex++;
			}
			table.add(tr);
		}
		html.add(body);

		printResult(html);
	}
	
	protected Tag generateKeyEntry(Object key)
	{
		Tag tr = new Tag("tr", "align=center");
		Tag firstEntry = new Tag("td", "align=center valign=center bgcolor=#9c9c9c");
		firstEntry.add(key.toString());
		tr.add(firstEntry);
		return tr;
	}
	
	protected Tag generateValueEntry(Tag tr, CalendarEvent value, int fileIndex) throws IOException
	{
		generateSubPage(value, fileIndex);
		
		Tag subEntry = new Tag("td", "align=center valign=center bgcolor=#eeeeee");
		Tag link = new Tag("a", "href="+ filepath + folder + "output" + Integer.toString(fileIndex)+ ".html");
		link.add(value.getMyTitle());
		subEntry.add(link);
		tr.add(subEntry);
		return tr;
	}
}
