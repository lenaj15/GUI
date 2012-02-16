import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.joda.time.DateTime;

public class Parser {
	
	

	public static ArrayList<CalendarEvent> parseFile(String filename) {
		
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		
		SAXBuilder builder = new SAXBuilder();

		File XmlFile = new File(filename);
		Document document = null;
		try {
			document = builder.build(XmlFile);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = document.getRootElement();

		List listOfChildren = root.getChildren();

		for (int i = 0; i < listOfChildren.size(); i++) {
			Element tempElement = (Element) listOfChildren.get(i);

			String subject, description;
			DateTime start, end;

			if (filename.contains("duke")) {
				subject = tempElement.getChild("summary").getValue();
				description = tempElement.getChild("description").getValue();

				String toFix = tempElement.getChild("start")
				        .getChild("utcdate").getValue();

				toFix = toFix.substring(0, 4) + "-" + toFix.substring(4, 6)
				        + "-" + toFix.substring(6, 11) + ":"
				        + toFix.substring(11, 13) + ":" + toFix.substring(13);

				start = new DateTime(toFix);
				toFix = tempElement.getChild("end").getChild("utcdate")
				        .getValue();

				toFix = toFix.substring(0, 4) + "-" + toFix.substring(4, 6)
				        + "-" + toFix.substring(6, 11) + ":"
				        + toFix.substring(11, 13) + ":" + toFix.substring(13);

				end = new DateTime(toFix);

				CalendarEvent thisEvent = new CalendarEvent(subject, start,
				        end, description);
				
				list.add(thisEvent);

				System.out.println(thisEvent);

			} else if (filename.contains("NFL")) {
				subject = tempElement.getChild("Col1").getValue();
				description = tempElement.getChild("Col15").getValue();

				String toFix = tempElement.getChild("Col8").getValue();

				toFix = toFix.substring(0, 10) + "T" + toFix.substring(11) + "Z";

				start = new DateTime(toFix);
				//System.out.println("DEBUGGING IN PARSER: "+start.toString());

				toFix = tempElement.getChild("Col9").getValue();

				toFix = toFix.substring(0, 10) + "T" + toFix.substring(11) + "Z";
				end = new DateTime(toFix);

				CalendarEvent thisEvent = new CalendarEvent(subject, start,
				        end, description);
				
				list.add(thisEvent);

				System.out.println(thisEvent);

			}
			
			
		}
		
		return list;

	}
}
