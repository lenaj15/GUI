package filter;
import java.util.ArrayList;

import event.CalendarEvent;


public class FilterByKeywords extends Filter{
	//adds an event if it matches all the keywords
	public ArrayList<CalendarEvent> filter(ArrayList <CalendarEvent> list, ArrayList<String> wordList){
		
		for (CalendarEvent e: list){
			for (String s : wordList){
				if (e.getMyTitle().contains(s))
					e.isOutput &= true;
				else
					e.isOutput = false;
			}
		}
		return list;
		
	}
}
