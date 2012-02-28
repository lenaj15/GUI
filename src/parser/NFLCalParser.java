package parser;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class NFLCalParser extends TivooParser {

	protected String myPath = "/document/row";
	protected String[] myAttributePaths = {"./Col2"};
	
	public NFLCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPath = this.myPath;
		super.myAttributePaths = this.myAttributePaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 10) + "T" + input.substring(11) + "Z";
		return new DateTime(input);
	}
	
	public ArrayList<CalendarEvent> parseEvent() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();

		List readInformation = super.parseXML();

		for (int i = 0; i < readInformation.size(); i++) {

			String title, description;
			DateTime start, end;
			Element individualElement = (Element) readInformation.get(i);

			title = individualElement.getChildText("Col1");
			description = individualElement.getChildText("Col15");
			start = parseDate(individualElement.getChildText("Col8"));
			end = parseDate(individualElement.getChildText("Col9"));
			
			CalendarEvent e = new CalendarEvent(title, start, end, description);
			
			try {
	            parseAdditionalAttributes(e, individualElement);
            } catch (JDOMException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
            }

			list.add(e);
		}

		return list;
	}


}
