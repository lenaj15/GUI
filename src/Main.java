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
import parser.TivooParserFactory;
import event.CalendarEvent;

public class Main {
	public static void main(String[] args) throws IOException, JDOMException {

		// this is the new parser
		TivooParserFactory factory = new TivooParserFactory("NFL.xml");
		TivooParser parser = factory.createParser();
		
		
		ArrayList<CalendarEvent> results = parser.parseFile();

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
