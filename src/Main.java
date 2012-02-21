import html_generator.HtmlGenerator;
import html_generator.HtmlHorizontalTable;
import html_generator.HtmlVerticalHeaders;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

import parser.DukeCalParser;
import event.CalendarEvent;
import filter.FilterByDates;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, JDOMException {

		// this is the new parser
		DukeCalParser calendarParser = new DukeCalParser("NFL.xml");
		ArrayList<CalendarEvent> list = calendarParser.parseFile();

		// testing filter
		ArrayList<String> test = new ArrayList<String>();
		test.add("Lemurs");
		
//		FilterByKeywords f = new FilterByKeywords();
//		ArrayList<CalendarEvent> results = f.filter(list, test);
		
		FilterByDates f = new FilterByDates();
		ArrayList<CalendarEvent> results = f.filter(list, new DateTime(2010, 11,
		 11, 0, 0), new DateTime(2012, 12, 1, 23, 0));

		// html writing
		String outputOption = "horizontal";
		
		HtmlGenerator tableCreator = new HtmlHorizontalTable(results);
		HtmlGenerator headerCreator = new HtmlVerticalHeaders(results);
		
		if (outputOption.equals("horizontal"))
			tableCreator.generateOutput();
		else if (outputOption.equals("vertical"))
			headerCreator.generateOutput();
	}

}
