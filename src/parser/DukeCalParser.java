package parser;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class DukeCalParser extends TivooParser {

	protected String myPaths = "/events/event";
	protected String myAttributePaths = "";

	public DukeCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPath = this.myPaths;
		super.myAttributePaths = this.myAttributePaths;
	}

	@Override
	public DateTime parseDate(String input) {

		input = input.substring(0, 4) + "-" + input.substring(4, 6) + "-"
		        + input.substring(6, 11) + ":" + input.substring(11, 13) + ":"
		        + input.substring(13);

		return new DateTime(input);
	}

	public ArrayList<CalendarEvent> parseEvent() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		List readInformation = super.parseXML();
		int size = readInformation.size();
		for (int i = 0; i < size; i++) {
			String title, description;
			DateTime start, end;
			Element individualElement = (Element) readInformation.get(i);
			title = individualElement.getChildText("summary");
			description = individualElement.getChildText("description");
			start = parseDate(individualElement.getChild("start").getChildText(
			        "utcdate"));
			end = parseDate(individualElement.getChild("end").getChildText(
			        "utcdate"));
			list.add(new CalendarEvent(title, start, end, description));
		}

		return list;
	}

	@Override
	protected void parseAdditionalAttributes() {
		// no additional attributes required for this parser

	}
}
