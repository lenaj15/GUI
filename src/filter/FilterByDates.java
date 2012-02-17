package filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import event.CalendarEvent;


public class FilterByDates extends Filter{
	//The map key will be the starting time of the event
		public Map <Object, ArrayList<CalendarEvent>> filter(ArrayList <CalendarEvent> lists, DateTime start, DateTime end){
			Map <Object, ArrayList<CalendarEvent>> eventMap = new HashMap <Object, ArrayList<CalendarEvent>> ();
			
			
			for (CalendarEvent e: lists){
				DateTime myTime = e.myStartTime;
				System.out.println("get millis: "+ e.myStartTime.getMillis()+ " start bound "+ start.getMillis()+ "get end millis: "+ e.myEndTime.getMillis()+ " end bound "+ end.getMillis() );
				if (e.myStartTime.getMillis() >= start.getMillis() && e.myEndTime.getMillis() <= end.getMillis()){
					if (!eventMap.containsKey(myTime)){
							ArrayList<CalendarEvent> dateFiltered = new ArrayList<CalendarEvent>();
							eventMap.put(myTime, dateFiltered);
						}
						ArrayList<CalendarEvent> dateFiltered = eventMap.get(myTime);
						dateFiltered.add(e);
						eventMap.put(myTime, dateFiltered);
				}
			}
			return eventMap;
		}
}
