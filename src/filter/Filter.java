package filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import event.CalendarEvent;


public abstract class Filter {
		
		//Default if there is no filter called, will just return the original list of events
		public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> lists) {
			return lists;
		}
		
		protected void toggle (CalendarEvent e, boolean include, boolean condition){
			if (condition)
				e.toggleIntersect();
			else
				e.setisOutput(false);
			if (!include)
				e.reverse();
		}
}
