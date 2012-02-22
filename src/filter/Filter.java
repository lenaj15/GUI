package filter;

import java.util.ArrayList;

import event.CalendarEvent;


public abstract class Filter {
		//Default if there is no filter called, will put the arraylist of events with the key ""
		public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> lists) {
			return lists;
		}

}
