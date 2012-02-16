import html.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.*;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.joda.time.DateTime;


public class Main {
	
	public static void main(String[] args) throws IOException{
		ArrayList<CalendarEvent> list = Parser.parseFile("NFL.xml");
		
		//Map<Object, ArrayList<CalendarEvent>> map = Filter.filterByKeyword(list, "Bears");
		
		/* //Test multiple keywords
		 * ArrayList<String> test = new ArrayList<String>();
		test.add("Bears");
		test.add("Lions");
		Map<Object, ArrayList<CalendarEvent>> map = Filter.filterByKeywords(list, test);*/
		
		//System.out.println("DEJLASJ FLDKSAJFLASDJ FLKSDJFLKAS JDF"+ new DateTime(2011, 12, 12, 9, 0).getHourOfDay()+" fdfdfa "+  new DateTime(2011, 12, 12, 9, 0).getMillis());
		Map<Object, ArrayList<CalendarEvent>> map = Filter.filterByDates(list, new DateTime(2010, 11, 11, 0, 0), new DateTime(2012, 12, 1, 23, 0));
		
		//System.out.println(list.size());
		//System.out.println(map.get("Bears"));
		
		Map<Object, ArrayList<CalendarEvent>> outputMap = map;
		
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
		//Parser.parseFile("NFL.xml");
	}

}
