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
		
		public void toggle (CalendarEvent e, boolean include, boolean condition){
			if (condition)
				e.toggleIntersect();
			else
				e.setisOutput(false);
			if (!include)
				e.reverse();
		}
		
		public void sortByStart(ArrayList <CalendarEvent> lists) {
			 Collections.sort(lists, CalendarEvent.sortType.ByStartTime.ascending()); 
		}
		
		public void sortByStartDescend(ArrayList <CalendarEvent> lists) {
			 Collections.sort(lists, CalendarEvent.sortType.ByStartTime.descending()); 
		}
		
		public void sortByTitle(ArrayList <CalendarEvent> lists) {
			Collections.sort(lists, CalendarEvent.sortType.ByTitle.ascending());
		}
		
		public void sortByEnd(ArrayList <CalendarEvent> lists) {
			Collections.sort(lists, CalendarEvent.sortType.ByEndTime.ascending()); 
		}
		
		public void sortByTitleDescend(ArrayList <CalendarEvent> lists) {
			Collections.sort(lists, CalendarEvent.sortType.ByTitle.descending());
		}
		
		public void sortByEndDescend(ArrayList <CalendarEvent> lists) {
			Collections.sort(lists, CalendarEvent.sortType.ByEndTime.descending()); 
		}
}
