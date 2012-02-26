package parser;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class NFLCalParser extends TivooParser {

	protected String[] myPaths = { "/document/row/Col1", "/document/row/Col15",
	        "/document/row/Col8", "/document/row/Col9" };
	
	public NFLCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 10) + "T" + input.substring(11) + "Z";
		return new DateTime(input);
	}
	
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
			Element startTimeElement = (Element) readInformation.get(2).get(i);
			Element endTimeElement = (Element) readInformation.get(3).get(i);

			title = titleElement.getValue();
			description = descriptionElement.getValue();
			start = parseDate(startTimeElement.getValue());
			end = parseDate(endTimeElement.getValue());

			list.add(new CalendarEvent(title, start, end, description));
		}

		return list;
	}

}
