import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.joda.time.DateTime;


public class Main {
	
	public static void main(String[] args) throws IOException{
		
		// Choose the file to parse
		Scanner in = new Scanner(System.in);
		System.out.println("Please select a file to parse (don't include .xml)");
		String xmlFile = in.nextLine();
		ArrayList<CalendarEvent> list = Parser.parseFile(xmlFile + ".xml");
		
		// Choose the method of filtering
		System.out.println("Please select an option (keyword or date):");
		String filterOption = in.nextLine();
		
		Map<Object, ArrayList<CalendarEvent>> outputMap = new TreeMap<Object, ArrayList<CalendarEvent>>();
		if (filterOption.equals("keyword"))
			outputMap = Filter.filterByKeyword(list, "Bears");
		else if (filterOption.equals("date"))
			outputMap = Filter.filterByDates(list, new DateTime(2010, 11, 11, 0, 0), new DateTime(2012, 12, 1, 23, 0));
		
		// Choose the method of output
		System.out.println("Please select the type of output you would like (vertical or horizontal):");
		String outputOption = in.nextLine();
		
		HtmlGenerator tableCreator = new HtmlHorizontalTable(outputMap);
		HtmlGenerator headerCreator = new HtmlVerticalHeaders(outputMap);
		
		if (outputOption.equals("horizontal"))
			tableCreator.generateOutput();
		else if (outputOption.equals("vertical"))
			headerCreator.generateOutput();
	}

}
