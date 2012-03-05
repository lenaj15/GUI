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
			//User wants to include events based on the boolean include value
			if (include){
				if (condition)
					e.toggleIntersect();
				else
					e.setisOutput(false);
			}
			else{
				if (condition)
					e.setisOutput(false);
				else
					e.toggleIntersect();
			}
			/*if (condition)
				e.toggleIntersect();
			else
				e.setisOutput(false);
			if (!include)
				e.reverse();*/
		}
}
