package filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import event.CalendarEvent;


public class FilterByKeywords extends Filter{
	
	//adds events if they match the keyword. Map <Keyword, ArrayList of CalendarEvents that match the keyword>
	public Map <Object, ArrayList<CalendarEvent>> filter(ArrayList <CalendarEvent> lists, ArrayList<String> wordList){
		Map <Object, ArrayList<CalendarEvent>> eventMap = new HashMap <Object, ArrayList<CalendarEvent>> ();
		for (String s: wordList){
			ArrayList<CalendarEvent> wordFiltered = new ArrayList<CalendarEvent>();
			for (CalendarEvent e : lists){
				if (e.getMyTitle().contains(s))
					wordFiltered.add(e);
			}
			eventMap.put(s, wordFiltered);
		}
		
		return eventMap;
	}
	
	
	/* Is there a better way to be able to filter an event that matches all the criteria
//adds an event if it matches all the keywords
public static Map <Object, ArrayList<CalendarEvent>> filterByAllKeywords(ArrayList <CalendarEvent> lists, ArrayList<String> wordList){
	Map <Object, ArrayList<CalendarEvent>> eventMap = new HashMap <Object, ArrayList<CalendarEvent>> ();
	ArrayList<CalendarEvent> wordFiltered = new ArrayList<CalendarEvent>();
	for (CalendarEvent e: lists){
		for (String s : wordList){
			if (e.getMyTitle().equals(s))
				wordFiltered.add(e);
		}
	}
	eventMap.put(wordList, wordFiltered);
	return eventMap;
}
*/
}
