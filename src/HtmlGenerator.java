import html.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public abstract class HtmlGenerator {
	Map<Object, ArrayList<CalendarEvent>> outputMap;
	public final static String filepath = "C:\\Users\\atm15\\Desktop\\";
	public final static String folder = "subpages\\";
	
	public HtmlGenerator(Map<Object, ArrayList<CalendarEvent>> map)
	{
		outputMap = map;
	}
	
	public abstract void generateOutput() throws IOException;
	
	public static void createSubFolder()
	{
		File f = new File(filepath + folder);
		f.mkdir();
	}
	
	@SuppressWarnings("unchecked")
	public static void generateSubPage(CalendarEvent event, int row) throws IOException
	{
		Tag html = new Tag("html");
		Tag head = new Tag("head");
		Tag header = new Tag("h1");
		header.add("Tivoo");
		head.add(header);
		Tag body = new Tag("body");
		
		Tag h4 = new Tag("h4", "align=center");
		h4.add(event.getMySummaries());
		body.add(h4);
		
		html.add(head);
		html.add(body);
		
		PrintWriter pw = new PrintWriter(new FileWriter(filepath + folder + "output" + Integer.toString(row)+ ".html"));
		pw.println(html.toString());
		pw.close();
	}
	
	public void printResult(Tag html) throws IOException
	{
		PrintWriter pw = new PrintWriter(new FileWriter(filepath + "output.html"));
		pw.println(html.toString());
		pw.close();
	}
}
