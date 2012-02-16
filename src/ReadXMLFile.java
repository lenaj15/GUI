import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import html.*;
 
public class ReadXMLFile {
 
	public static void main(String argv[]) {
 
		try {
 
			File fXmlFile = new File("dukecal.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("event");
			
			Tag html = new Tag("html");
			Tag head = new Tag("head");
			Tag header = new Tag("h1");
			header.add("Tivoo");
			head.add(header);
			Tag body = new Tag("body");
			 
					// Example map
			Map<Object, ArrayList<CalendarEvent>> outputMap = new TreeMap<Object, ArrayList<CalendarEvent>>();
			ArrayList<CalendarEvent> list1 = new ArrayList<CalendarEvent>();
			list1.add(new CalendarEvent("Bulbasaur", new DateTime(1328110987*1000), new DateTime(1328111987*1000), "first grass evolution"));
			list1.add(new CalendarEvent("Ivysaur", new DateTime(1328112987*1000), new DateTime(1328113987*1000), "second grass evolution"));
			list1.add(new CalendarEvent("Venusaur", new DateTime(1328114987*1000), new DateTime(1328115987*1000), "third grass evolution"));
			ArrayList<CalendarEvent> list2 = new ArrayList<CalendarEvent>();
			list2.add(new CalendarEvent("Charmander", new DateTime(1328110987*1000), new DateTime(1328111987*1000), "first fire evolution"));
			list2.add(new CalendarEvent("Charmeleon", new DateTime(1328112987*1000), new DateTime(1328113987*1000), "second fire evolution"));
			list2.add(new CalendarEvent("Charizard", new DateTime(1328114987*1000), new DateTime(1328115987*1000), "third fire evolution"));
			ArrayList<CalendarEvent> list3 = new ArrayList<CalendarEvent>();
			list3.add(new CalendarEvent("Squirtle", new DateTime(1328110987*1000), new DateTime(1328111987*1000), "first water evolution"));
			list3.add(new CalendarEvent("Wartortle", new DateTime(1328112987*1000), new DateTime(1328113987*1000), "second water evolution"));
			list3.add(new CalendarEvent("Blastoise", new DateTime(1328114987*1000), new DateTime(1328115987*1000), "third water evolution"));
			outputMap.put("GRASS", list1);
			outputMap.put("FIRE", list2);
			outputMap.put("WATER", list3);
			
			// Table creation
			Tag table = new Tag("table", "border=5 cellpadding=3 cellspacing=0 width=500");
			body.add(table);
			
			//using to test 
			
			
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
				
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	  
		  
	  }
 
	  private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	  }
  
 
 
}