package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class GoogleCalParser extends TivooParser {

	protected String myPath = "/feed/entry";
	protected String[] myAttributePaths = {"./title"};

	
	public GoogleCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPath = this.myPath;
		super.myAttributePaths = this.myAttributePaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 19)+"Z";
		return new DateTime(input);
	}

	@Override
    public ArrayList<CalendarEvent> parseEvent() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		List readInformation = super.parseXML();
		
		for(int i = 0; i<readInformation.size(); i++){
			Element individualElement = (Element) readInformation.get(i);

			String title, description;
			DateTime start, end;

			title = individualElement.getChild("title").getValue();
			description = individualElement.getChildText("content");

			start = parseDate(individualElement.getChildText("published"));
			end = parseDate(individualElement.getChildText("updated"));
			
			list.add(new CalendarEvent(title, start, end, description));
		}
		
		return list;
    }

}
