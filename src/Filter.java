
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;


public class Filter {


	public static Map <Object, ArrayList<CalendarEvent>> filterByKeyword(ArrayList <CalendarEvent> lists, String word){
		Map <Object, ArrayList<CalendarEvent>> eventMap = new HashMap <Object, ArrayList<CalendarEvent>> ();
		ArrayList<CalendarEvent> wordFiltered = new ArrayList<CalendarEvent>();
		for (CalendarEvent e: lists){

			if (e.getMyTitle().contains(word)) { 
				
				wordFiltered.add(e);
			}
		}
		eventMap.put(word, wordFiltered);
		return eventMap;
	}
	
	//The map key will be the starting time of the event
	public static Map <Object, ArrayList<CalendarEvent>> filterByDates(ArrayList <CalendarEvent> lists, DateTime start, DateTime end){
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
	
	//adds events if they match the keyword. Map <Keyword, ArrayList of CalendarEvents that match the keyword>
	public static Map <Object, ArrayList<CalendarEvent>> filterByKeywords(ArrayList <CalendarEvent> lists, ArrayList<String> wordList){
		Map <Object, ArrayList<CalendarEvent>> eventMap = new HashMap <Object, ArrayList<CalendarEvent>> ();
		
		for (String s: wordList){
			ArrayList<CalendarEvent> wordFiltered = new ArrayList<CalendarEvent>();
			for (CalendarEvent e : lists){
				if (e.getMyTitle().equals(s))
					wordFiltered.add(e);
			}
			eventMap.put(s, wordFiltered);
		}
		
		return eventMap;
	}
	
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
	
	
}
