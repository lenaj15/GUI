package filter;
import java.util.ArrayList;

import org.joda.time.DateTime;

import event.CalendarEvent;


public class FilterByDates extends Filter {
	//The parameter "include": true if user wants the events within the time frame to be included, false if user wants events
	public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> list, DateTime start, DateTime end, boolean include) {
		
		for (CalendarEvent e: list) {
			
			boolean condition = (e.getmyStartTime().getMillis() >= start.getMillis() && e.getmyEndTime().getMillis()<= end.getMillis());
			if ((condition && include) || !(condition || include))
				e.toggleIntersect();
			else
				e.setisOutput(false);
		}
		return list;
	}
}
