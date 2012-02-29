package html_generator;

import html.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.joda.time.DateTime;

import event.CalendarEvent;

/***
 * Purpose: creates a vertical list showing groups of conflicting events
 * 
 */
@SuppressWarnings("unchecked")
public class HtmlConflictTable extends HtmlGenerator {

	public HtmlConflictTable(ArrayList<CalendarEvent> list, DateTime start, DateTime end) {
		super(list, start, end);
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
		int conflictNum = 1;
		
		for (int i = 0; i < outputList.size(); i++) {
			for (int j = 0; j < outputList.size(); j++) {
				if (j > i) {
					CalendarEvent event1 = outputList.get(i);
					long start1 = event1.getmyStartTime().getMillis();
					long end1 = event1.getmyEndTime().getMillis();
					
					CalendarEvent event2 = outputList.get(j);
					long start2 = event2.getmyStartTime().getMillis();
					long end2 = event2.getmyEndTime().getMillis();
					
					if ((start2 >= start1 && start2 <= end1) || (end2 >= start1 && end2 <= end1)) {
						ArrayList<CalendarEvent> pair = new ArrayList<CalendarEvent>();
						pair.add(event1);
						pair.add(event2);
						outputMap.put("Conflict #" + conflictNum, pair);
					}
				}
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
