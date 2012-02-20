package event;

import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class CalendarEvent {
	
	public String myTitle, mySummaries;
	public DateTime myStartTime, myEndTime;
	
	public CalendarEvent(String title, DateTime start, DateTime end, String summaries){	
		myTitle = title;
		myStartTime = start;
		myEndTime = end;
		mySummaries = summaries;	
	}

	public String getMyTitle() {
    	return myTitle;
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

   /* public static enum Order implements Comparator {
     ByTitle() {
       public int compare(CalendarEvent lhs, CalendarEvent rhs) {
          return lhs.myTitle.compareTo(rhs.myTitle);
       }
    },
    ByStartTime() {
       public int compare(CalendarEvent lhs, CalendarEvent rhs) {
          // TODO: Should really use a collator.
          return lhs.myStartTime.compareTo(rhs.myStartTime);
       },

    ByEndTime() {
       public int compare(CalendarEvent lhs, CalendarEvent rhs) {
          // TODO: Should really use a collator.
          return lhs.myEndTime.compareTo(rhs.myEndTime);
       }
    };

    public abstract int compare(CalendarEvent lhs, CalendarEvent rhs);

    public Comparator ascending() {
       return this;
    }

    public Comparator descending() {
       return Collections.reverseOrder(this);
    }
 }
	*/
}
