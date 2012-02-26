package event;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class CalendarEvent {
	
	protected String myTitle, mySummaries;
	protected DateTime myStartTime, myEndTime;
	protected boolean isOutput;
	protected HashMap<String, String> myAttributes;

	public CalendarEvent(String title, DateTime start, DateTime end, String summaries) {	
		myTitle = title;
		myStartTime = start;
		myEndTime = end;
		mySummaries = summaries;	
		isOutput = true;
		myAttributes = new HashMap<String, String>();
	}
	
	public void addAttribute(String attribute, String value){
		myAttributes.put(attribute, value);
	}
	
	public HashMap<String, String> getAttributeMap(){
		return myAttributes;
	}

	public void setisOutput(boolean value) {
    	isOutput = value;
    }

	public void toggleIntersect() {
    	isOutput &= true;
    }

	public String getMyTitle() {
    	return myTitle;
    }

	public boolean getisOutput() {
    	return isOutput;
    }
	public DateTime getmyStartTime() {
    	return myStartTime;
    }
	public DateTime getmyEndTime() {
    	return myEndTime;
    }

	public String getMySummaries() {
    	return mySummaries;
    }

	public String getMyDatesString() {
		DateTimeFormatter fmt = DateTimeFormat.longDateTime();
		String start = fmt.print(myStartTime);
		String end = fmt.print(myEndTime);
		return start + " - " + end;
	}
	
	public String toString(){
		return "Title: " + myTitle + "\nDescription: " + mySummaries + "\nStarted: " + myStartTime + "\nEnded: " + myEndTime;
	}

    public enum sortType  {
    ByTitle(new Comparator<CalendarEvent>() {
        public int compare(CalendarEvent event1, CalendarEvent event2) {
	        return event1.getMyTitle().compareTo(event2.getMyTitle());
        }
    }),
    
    ByStartTime(new Comparator<CalendarEvent>() {
       public int compare(CalendarEvent event1, CalendarEvent event2) {
          return event1.myStartTime.compareTo(event2.myStartTime);
       }
    }),

    ByEndTime(new Comparator<CalendarEvent>() {
       public int compare(CalendarEvent event1, CalendarEvent event2) {
          return event1.myEndTime.compareTo(event2.myEndTime);
       }
    });
    private Comparator <CalendarEvent> myComparator;

    private sortType(Comparator<CalendarEvent> comparator){
    	myComparator = comparator;
    }
    
    public Comparator<CalendarEvent> ascending() {
       return myComparator;
    }
    
    public Comparator<CalendarEvent> descending() {
       return Collections.reverseOrder(myComparator);
    }
    
    public Comparator<CalendarEvent> getComparator(){
    	return myComparator;
    }
 }
	
}
