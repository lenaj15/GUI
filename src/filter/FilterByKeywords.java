package filter;
import java.util.ArrayList;

import event.CalendarEvent;


public class FilterByKeywords extends Filter {
	
	//adds an event if it matches all the keywords unless the boolean 'include' is false
	public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> list, ArrayList<String> wordList, boolean include) {
		
		for (CalendarEvent e: list) {
			for (String s : wordList) {

				boolean condition = e.getMyTitle().contains(s);
				if ((condition && include) || !(condition || include))			
					e.toggleIntersect();
				else
					e.setisOutput(false);
			}
		}
		return list;
		
	}
}
