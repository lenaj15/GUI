import html_generator.HtmlGenerator;
import html_generator.HtmlHorizontalCalendar;
import html_generator.HtmlVerticalList;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;

import parser.NFLCalParser;
import parser.TivooParser;
import event.CalendarEvent;
import filter.FilterByKeywords;

public class Main {
	public static void main(String[] args) throws IOException, JDOMException {

		// this is the new parser
		TivooParser calendarParser = new NFLCalParser("NFL.xml");
		ArrayList<CalendarEvent> list = calendarParser.parseFile();

		// testing filter
		ArrayList<String> test = new ArrayList<String>();
		test.add("Giants");
		
		FilterByKeywords f = new FilterByKeywords();
		ArrayList<CalendarEvent> results = f.filter(list, test);
		
//		FilterByDates f = new FilterByDates();
//		ArrayList<CalendarEvent> results = f.filter(list, new DateTime(2010, 11,
//		 11, 0, 0), new DateTime(2012, 12, 1, 23, 0));

		// html writing
		String outputOption = "horizontal";
		
		HtmlGenerator calendarCreator = new HtmlHorizontalCalendar(results);
		HtmlGenerator listCreator = new HtmlVerticalList(results);
		
		if (outputOption.equals("horizontal"))
			calendarCreator.generateOutput();
		else if (outputOption.equals("vertical"))
			listCreator.generateOutput();
	}

}
