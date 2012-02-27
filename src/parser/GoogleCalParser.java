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

	protected String[] myPaths = {"/feed/entry"};

	
	public GoogleCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 19)+"Z";
		return new DateTime(input);
	}

	@Override
    public ArrayList<CalendarEvent> parseFile() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();

		ArrayList<List> readInformation = super.parseXML();

		
		int size = readInformation.get(0).size();
		
		for(int i = 0; i<size; i++){
			Element individualElement = (Element) readInformation.get(0).get(i);

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
