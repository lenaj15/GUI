package html_generator;

import html.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import event.CalendarEvent;

@SuppressWarnings("unchecked")
public class HtmlHorizontalCalendar extends HtmlGenerator {

	public HtmlHorizontalCalendar(ArrayList<CalendarEvent> list) {
		super(list);
	}

	public void generateOutput() throws IOException {
		createSubFolder();
		sortIntoMap();
		
		Tag html = generateHeader();
		Tag body = new Tag("body");
		
		Tag table = new Tag("table", "border=5 cellpadding=3 cellspacing=0 width=500");
		body.add(table);
		int fileIndex = 1;
		
		for (Object key: outputMap.keySet())
		{
			Tag tr = generateKeyEntry(key);
			for (CalendarEvent value: outputMap.get(key)) {
				tr = generateValueEntry(tr, value, fileIndex);
				fileIndex++;
			}
			table.add(tr);
		}
		html.add(body);

		printResult(html);
	}
	
	protected void sortIntoMap() {
		outputMap = new TreeMap<Object, ArrayList<CalendarEvent>>();
		
		for (CalendarEvent e: outputList) {
			String date = e.getMyDatesString();
			if (outputMap.keySet().contains(date)) {
				ArrayList<CalendarEvent> value = outputMap.get(date);
				value.add(e);
				outputMap.put(date, value);
			}
			else {
				ArrayList<CalendarEvent> value = new ArrayList<CalendarEvent>();
				value.add(e);
				outputMap.put(date, value);
			}
		}
	}
	
	protected Tag generateKeyEntry(Object key) {
		Tag tr = new Tag("tr", "align=center");
		Tag firstEntry = new Tag("td", "align=center valign=center bgcolor=#9c9c9c");
		firstEntry.add(key.toString());
		tr.add(firstEntry);
		return tr;
	}
	
	protected Tag generateValueEntry(Tag tr, CalendarEvent value, int fileIndex) throws IOException {
		generateSubPage(value, fileIndex);
		
		Tag subEntry = new Tag("td", "align=center valign=center bgcolor=#eeeeee");
		Tag link = new Tag("a", "href="+ FILEPATH + FOLDER + "output" + Integer.toString(fileIndex)+ ".html");
		link.add(value.getMyTitle());
		subEntry.add(link);
		tr.add(subEntry);
		return tr;
	}
}
