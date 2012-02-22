package filter;
import java.util.ArrayList;

import org.joda.time.DateTime;

import event.CalendarEvent;


public class FilterByDates extends Filter {
	//The map key will be the starting time of the event
	public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> list, DateTime start, DateTime end) {
		
		for (CalendarEvent e: list) {
			if (e.myStartTime.getMillis() >= start.getMillis() && e.myEndTime.getMillis() <= end.getMillis())
				e.isOutput &= true;
			else
				e.isOutput = false;
		}
		return list;
	}
}
