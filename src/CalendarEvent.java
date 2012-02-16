import java.util.Date;

import org.joda.time.DateTime;


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

	
	public String toString(){
		return "Title: " + myTitle + "\nDescription: " + mySummaries + "\nStarted: " + myStartTime + "\nEnded: " + myEndTime;
	}

	
}
