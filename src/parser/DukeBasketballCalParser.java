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

	protected String[] myPaths = { "/dataroot/Calendar" };

	public DukeBasketballCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;

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
	public ArrayList<CalendarEvent> parseFile() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		ArrayList<List> readInformation = super.parseXML();

		int size = readInformation.get(0).size();

		for (int i = 0; i < size; i++) {
			Element individualElement = (Element) readInformation.get(0).get(i);

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
