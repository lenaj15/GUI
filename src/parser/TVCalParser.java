package parser;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class TVCalParser extends TivooParser {

	protected String[] myPaths = { "/tv/programme"};
	
	public TVCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 4) + "-" + input.substring(4, 6) + "-"
		        + input.substring(6, 8) + "T" + input.substring(8, 10) + ":"
		        + input.substring(10,12) + ":" + input.substring(12, 13) + "Z";

		return new DateTime(input);
	}

	@Override
    public ArrayList<CalendarEvent> parseFile() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();

		ArrayList<List> readInformation = super.parseXML();

		int size = readInformation.get(0).size();

		for (int i = 0; i < size; i++) {

			String title, description;
			DateTime start, end;

			Element titleElement = (Element) readInformation.get(0).get(i);
			Element descriptionElement = (Element) readInformation.get(1)
			        .get(i);
			Element individualElement = (Element) readInformation.get(2).get(i);
			
			String startTimeString = individualElement.getAttributeValue("start");
			String endTimeString = individualElement.getAttributeValue("stop");

			title = titleElement.getValue();
			description = descriptionElement.getValue();
			start = parseDate(startTimeString);
			end = parseDate(endTimeString);
			
			CalendarEvent thisEvent = new CalendarEvent(title, start, end, description);

			list.add(thisEvent);
		}

		return list;
    }

}
