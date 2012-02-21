package filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import event.CalendarEvent;


public abstract class Filter {
		//Default if there is no filter called, will put the arraylist of events with the key ""
		public Map <Object, ArrayList<CalendarEvent>> filter(ArrayList <CalendarEvent> lists){
			Map <Object, ArrayList<CalendarEvent>> eventMap = new HashMap <Object, ArrayList<CalendarEvent>> ();
			eventMap.put("", lists);
			return eventMap;
		}

}
