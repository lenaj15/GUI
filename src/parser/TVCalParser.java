package parser;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class TVCalParser extends TivooParser {

	protected String myPath = "/tv/programme";
	protected String[] myAttributePaths = { "./credits/actor" };

	public TVCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPath = this.myPath;
		super.myAttributePaths = this.myAttributePaths;

	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 4) + "-" + input.substring(4, 6) + "-"
		        + input.substring(6, 8) + "T" + input.substring(8, 10) + ":"
		        + input.substring(10, 12) + ":" + input.substring(12, 13) + "Z";

		return new DateTime(input);
	}

	@Override
	public ArrayList<CalendarEvent> parseEvent() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		List readInformation = super.parseXML();
		for (int i = 0; i < readInformation.size(); i++) {
			String title, description;
			DateTime start, end;

			Element individualElement = (Element) readInformation.get(i);

			String startTimeString = individualElement
			        .getAttributeValue("start");
			String endTimeString = individualElement.getAttributeValue("stop");

			title = individualElement.getChildText("title");
			description = individualElement.getChildText("desc");

			if (description == null)
				description = "";

			start = parseDate(startTimeString);
			end = parseDate(endTimeString);

			CalendarEvent thisEvent = new CalendarEvent(title, start, end,
			        description);

			try {
				parseAdditionalAttributes(thisEvent, individualElement);
			} catch (JDOMException e) {
				e.printStackTrace();
			}

			list.add(thisEvent);
		}

		return list;
	}

}
