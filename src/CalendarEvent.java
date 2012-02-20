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

	
}
