package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class DukeBasketballCalParser extends TivooParser {

	protected String myPath = "/dataroot/Calendar";
	protected String[] myAttributePaths = {""};

	public DukeBasketballCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPath = this.myPath;
		super.myAttributePaths = this.myAttributePaths;

	}

	public DateTime parseDate(String input) {
		
		Scanner in = new Scanner(input);
		in.useDelimiter("/");

		int month = in.nextInt();
		int day = in.nextInt();
		int year = in.nextInt();

		int hour = Integer.parseInt(input.substring(input.indexOf(" ") + 1,
		        input.indexOf(":", 10)));
		int minute = Integer.parseInt(input.substring(
		        input.indexOf(":", 10) + 1, input.indexOf(":", 10) + 3));

		return new DateTime(year, month, day, hour, minute);
	}

	@Override
	public ArrayList<CalendarEvent> parseEvent() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		List readInformation = super.parseXML();
		for (int i = 0; i < readInformation.size(); i++) {
			Element individualElement = (Element) readInformation.get(i);

			String title, description;
			DateTime start, end;

			title = individualElement.getChildText("Subject");
			description = individualElement.getChildText("Location");

			start = parseDate(individualElement.getChildText("StartDate") + "/ "
			        + individualElement.getChildText("StartTime"));
			end = parseDate(individualElement.getChildText("EndDate") + "/ "
			        + individualElement.getChildText("EndTime"));
			
			list.add(new CalendarEvent(title, start, end, description));

		}

		return list;
	}

}
