package html_generator;

import html.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import event.CalendarEvent;

@SuppressWarnings("unchecked")
public class HtmlVerticalList extends HtmlGenerator {

	public HtmlVerticalList(ArrayList<CalendarEvent> list) {
		super(list);
	}

	public void generateOutput() throws IOException {
		createSubFolder();
		sortIntoMap();
		
		Tag html = generateHeader();
		Tag body = new Tag("body");
		
		int fileIndex = 1;
		
		for (Object key: outputMap.keySet()) {
			Tag h1 = generateKeyEntry(key);
			for (CalendarEvent value: outputMap.get(key)) {
				h1 = generateValueEntry(h1, value, fileIndex);
				fileIndex++;
			}
			body.add(h1);
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
		Tag h1 = new Tag("h1");
		h1.add(key.toString());
		return h1;
	}

	protected Tag generateValueEntry(Tag h1, CalendarEvent value, int fileIndex) throws IOException {
		generateSubPage(value, fileIndex);
		
		Tag h4 = new Tag("h4");
		Tag link = new Tag("a", "href="+ FILEPATH + FOLDER + "output" + Integer.toString(fileIndex)+ ".html");
		link.add(value.getMyTitle());
		h4.add(link);
		h1.add(h4);
		return h1;
	}

}
