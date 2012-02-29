package filter;

import java.util.ArrayList;
import java.util.Collections;

import event.CalendarEvent;

public class Sorter {
	public static void sortByStart(ArrayList <CalendarEvent> lists) {
		 Collections.sort(lists, CalendarEvent.sortType.ByStartTime.ascending()); 
	}
	
	public static void sortByStartDescend(ArrayList <CalendarEvent> lists) {
		 Collections.sort(lists, CalendarEvent.sortType.ByStartTime.descending()); 
	}
	
	public static void sortByTitle(ArrayList <CalendarEvent> lists) {
		Collections.sort(lists, CalendarEvent.sortType.ByTitle.ascending());
	}
	
	public static void sortByEnd(ArrayList <CalendarEvent> lists) {
		Collections.sort(lists, CalendarEvent.sortType.ByEndTime.ascending()); 
	}
	
	public static void sortByTitleDescend(ArrayList <CalendarEvent> lists) {
		Collections.sort(lists, CalendarEvent.sortType.ByTitle.descending());
	}
	
	public static void sortByEndDescend(ArrayList <CalendarEvent> lists) {
		Collections.sort(lists, CalendarEvent.sortType.ByEndTime.descending()); 
	}
}
