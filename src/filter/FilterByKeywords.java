package filter;

import java.util.ArrayList;

import event.CalendarEvent;

public class FilterByKeywords extends Filter {

	// adds an event if it matches all the keywords unless the boolean 'include' is false
	// filters through the title, the summary, and the other attributes
	public ArrayList<CalendarEvent> filter(ArrayList<CalendarEvent> list, ArrayList<String> wordList, boolean include) {

		for (CalendarEvent e : list) {
			for (String s : wordList) {

				boolean title = e.getMyTitle().contains(s);
				boolean summaries = e.getMySummaries().contains(s);
				boolean condition = e.getAttributeMap().containsValue(s);
				
				super.toggle(e, include, title);
				super.toggle(e, include, summaries);
				super.toggle(e, include, condition);
					
			}
		}
		return list;

	}
	
	
	/* This method filters through only the attribute field specified by the parameter: String attribute
	 * and adds the event if it matches all the keywords, unless the boolean 'include' is false.
	 * If the event does not have this attribute (not applicable) then it adds the event
	*/
	public ArrayList<CalendarEvent> filter(ArrayList<CalendarEvent> list, ArrayList<String> wordList, boolean include, String attribute) {

		for (CalendarEvent e : list) {
			if (e.getAttributeMap().containsKey(attribute)){
				for (String s : wordList) {
					String entry = e.getAttributeMap().get(attribute);
					boolean condition = entry.contains(s);
					super.toggle(e, include, condition);
				}
			}
		}
		return list;

	}

}
