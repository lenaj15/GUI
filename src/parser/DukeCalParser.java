package parser;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class DukeCalParser extends TivooParser {

	protected String[] myPaths = { "/events/event/summary",
	        "/events/event/description", "/events/event/start/utcdate",
	        "/events/event/end/utcdate" };

	
	public DukeCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
	}

	@Override
	public DateTime parseDate(String input) {

		input = input.substring(0, 4) + "-" + input.substring(4, 6) + "-"
		        + input.substring(6, 11) + ":" + input.substring(11, 13) + ":"
		        + input.substring(13);

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
