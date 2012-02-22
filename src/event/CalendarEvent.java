package event;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class CalendarEvent {
	
	protected String myTitle, mySummaries;
	protected DateTime myStartTime, myEndTime;
	protected boolean isOutput;

	public CalendarEvent(String title, DateTime start, DateTime end, String summaries) {	
		myTitle = title;
		myStartTime = start;
		myEndTime = end;
		mySummaries = summaries;	
		isOutput = true;
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
