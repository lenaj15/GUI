import html_generator.HtmlConflictTable;
import html_generator.HtmlGenerator;
import html_generator.HtmlSortedList;
import html_generator.HtmlTableCalendar;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

import parser.NFLCalParser;
import parser.TivooParser;
import event.CalendarEvent;

public class Main {
	public static void main(String[] args) throws IOException, JDOMException {

		// this is the new parser
		TivooParser calendarParser = new NFLCalParser("NFL.xml");
		ArrayList<CalendarEvent> list = calendarParser.parseFile();

		// testing filter
//		ArrayList<String> test = new ArrayList<String>();
//		test.add("Giants");
//		
//		FilterByKeywords f = new FilterByKeywords();
//		ArrayList<CalendarEvent> results = f.filter(list, test);
		
//		FilterByDates f = new FilterByDates();
//		ArrayList<CalendarEvent> results = f.filter(list, new DateTime(2010, 11,
//		 11, 0, 0), new DateTime(2012, 12, 1, 23, 0));
		ArrayList<CalendarEvent> results = list;

		// html writing
		String outputOption = "calendar";
		
		HtmlGenerator sortedlistCreator = new HtmlSortedList(results,
				null, null);
		HtmlGenerator conflictCreator = new HtmlConflictTable(results, 
				null, null);
		HtmlGenerator calendarCreator = new HtmlTableCalendar(results, 
				new DateTime(2011, 11, 1, 0, 0), new DateTime(2011, 11, 30, 23, 0));
		
		if (outputOption.equals("sorted"))
			sortedlistCreator.generateOutput();
		else if (outputOption.equals("conflict"))
			conflictCreator.generateOutput();
		else if (outputOption.equals("calendar"))
			calendarCreator.generateOutput();

	}

}
