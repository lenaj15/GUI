package html_generator; 
import html.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import org.joda.time.DateTime;

import event.CalendarEvent;

@SuppressWarnings("unchecked")
public abstract class HtmlGenerator {
	protected ArrayList<CalendarEvent> outputList;
	protected DateTime startDate;
	protected DateTime endDate;
	
	protected Map<Object, ArrayList<CalendarEvent>> outputMap;
	
	protected final static String FILEPATH = "C:\\Users\\atm15\\Desktop\\";
	protected final static String FOLDER = "subpages\\";
	protected final static String FILENAME = "output";
	protected final static String HTML_EXT = ".html";
	
	public abstract void generateOutput() throws IOException;
	protected abstract Tag generateKeyEntry(Object o);
	protected abstract Tag generateValueEntry(Tag t, CalendarEvent e, int i) throws IOException;
	
	/***
	 * Constructor with an array list to be generated
	 * 
	 * @param list
	 */
	public HtmlGenerator(ArrayList<CalendarEvent> list, DateTime start, DateTime end) {
		outputList = trimList(list);
		startDate = start;
		endDate = end;
	}
	
	/***
	 * Constructor with a map to be generated
	 * 
	 * @param list
	 */
	public ArrayList<CalendarEvent> trimList(ArrayList<CalendarEvent> list) {
		ArrayList<CalendarEvent> result = new ArrayList<CalendarEvent>();
		for (CalendarEvent e: list)
		{
			if (e.getisOutput())
				result.add(e);
		}
		return result;
	}
	
	/***
	 * Creates a folder to hold all of the subpages
	 */
	protected static void createSubFolder() {
		File f = new File(FILEPATH + FOLDER);
		f.mkdir();
	}
	
	/***
	 * Generates the first couple of tags for every page
	 */
	protected static Tag generateHeader() {
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		html.add(head);
		return html;
	}
	
	/***
	 * Generate a page to be linked to a title in the main page
	 * 
	 * @param CalendarEvent event, int fileNum
	 * @throws IOException
	 */
	protected static void generateSubPage(CalendarEvent event, int fileNum) throws IOException {
		Tag html = generateHeader();
		Tag body = new Tag("body");
		
		Tag title = generateDetail("Title", event.getMyTitle());
		body.add(title);
		
		Tag summary = generateDetail("Event summary", event.getMySummaries());
		body.add(summary);
		
		Tag time = generateDetail("Date", event.getMyDatesString());
		body.add(time);
		
		html.add(body);
		
		PrintWriter pw = new PrintWriter(new FileWriter(FILEPATH + FOLDER + FILENAME + Integer.toString(fileNum)+ HTML_EXT));
		pw.println(html.toString());
		pw.close();
	}
	
	/***
	 * Generate a detail item to be used in a subpage
	 * 
	 * @param String item, String info
	 */
	private static Tag generateDetail(String item, String info) {
		Tag detail = new Tag("h4");
		detail.add(item + ": " + info);
		return detail;
	}
	
	/***
	 * Create the main file
	 * 
	 * @param Tag html
	 * @throws IOException
	 */
	public void printResult(Tag html) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(FILEPATH + FILENAME + HTML_EXT));
		pw.println(html.toString());
		pw.close();
	}
}
