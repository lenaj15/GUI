import html.Tag;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.joda.time.DateTime;


public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		
		// Parse the file
		System.out.println("Please select a file to parse (don't include .xml)");
		Scanner in1 = new Scanner(System.in);
		String xmlFile = in1.nextLine();
		ArrayList<CalendarEvent> list = Parser.parseFile(xmlFile + ".xml");
		
		// Choose the method of filtering
		System.out.println("Please select and option (keyword or date):");
		Scanner in2 = new Scanner(System.in);
		String filterOption = in2.nextLine();
		
		Map<Object, ArrayList<CalendarEvent>> outputMap = new TreeMap<Object, ArrayList<CalendarEvent>>();
		if (filterOption.equals("keyword"))
		{
			ArrayList<String> test = new ArrayList<String>();
			test.add("Bears");
			test.add("Lions");
			outputMap = Filter.filterByKeywords(list, test);
		}
		else if (filterOption.equals("date"))
		{
			outputMap = Filter.filterByDates(list, new DateTime(2010, 11, 11, 0, 0), new DateTime(2012, 12, 1, 23, 0));
		}
		
		
		// Generate HTML file
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		Tag body = new Tag("body");
		
		Tag table = new Tag("table", "border=5 cellpadding=3 cellspacing=0 width=500");
		body.add(table);
		
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
				tr.add(subEntry);
			}
			table.add(tr);
		}
		
		// Vertical header creation
		for (Object key: outputMap.keySet())
		{
			Tag h1 = new Tag("h1", "align=center");
			h1.add(key.toString());
			body.add(h1);
			for (CalendarEvent value: outputMap.get(key))
			{
				Tag h4 = new Tag("h4", "align=center");
				h4.add(value.getMyTitle()); // or value.getSummaries();
				body.add(h4);
			}
		}
		
		html.add(head);
		html.add(body);
		System.out.println(html);
		
		PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\atm15\\Desktop\\output.html"));
		pw.println(html.toString());
		pw.close();
	}

}
