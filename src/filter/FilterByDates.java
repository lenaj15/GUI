package filter;
import java.util.ArrayList;

import org.joda.time.DateTime;

import event.CalendarEvent;


public class FilterByDates extends Filter {
	//The map key will be the starting time of the event
	public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> list, DateTime start, DateTime end) {
		
		for (CalendarEvent e: list) {
			if (e.getmyStartTime().getMillis() >= start.getMillis() && e.getmyEndTime().getMillis() <= end.getMillis())
				e.toggleIntersect();
			else
				e.setisOutput(false);
		}
		return list;
	}
}
