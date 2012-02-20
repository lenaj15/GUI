package html_generator; 
import html.Tag;

import event.CalendarEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class HtmlGenerator {
	Map<Object, ArrayList<CalendarEvent>> outputMap;
	public final static String filepath = "";
	public final static String folder = "subpages";
	
	public abstract void generateOutput() throws IOException;
	public abstract Tag generateKeyEntry(Object o);
	public abstract Tag generateValueEntry(Tag t, CalendarEvent e, int i) throws IOException;
	
	public HtmlGenerator(Map<Object, ArrayList<CalendarEvent>> map)
	{
		outputMap = map;
	}
	
	public static void createSubFolder()
	{
		File f = new File(filepath + folder);
		f.mkdir();
	}
	
	public Tag generateHeader()
	{
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		html.add(head);
		return html;
	}
	
	public static void generateSubPage(CalendarEvent event, int fileNum) throws IOException
	{
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		Tag body = new Tag("body");
		
		Tag title = generateDetail("Title", event.getMyTitle());
		body.add(title);
		
		Tag summary = generateDetail("Event summary", event.getMySummaries());
		body.add(summary);
		
		Tag time = generateDetail("Date", event.getMyDatesString());
		body.add(time);
		
		html.add(head);
		html.add(body);
		
		PrintWriter pw = new PrintWriter(new FileWriter(filepath + folder + "output" + Integer.toString(fileNum)+ ".html"));
		pw.println(html.toString());
		pw.close();
	}
	
	public static Tag generateDetail(String item, String info)
	{
		Tag detail = new Tag("h4");
		detail.add(item + ": " + info);
		return detail;
	}
	
	public void printResult(Tag html) throws IOException
	{
		PrintWriter pw = new PrintWriter(new FileWriter(filepath + "output.html"));
		pw.println(html.toString());
		pw.close();
	}
}
