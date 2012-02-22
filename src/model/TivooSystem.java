package model;

import html_generator.HtmlGenerator;
import html_generator.HtmlTableCalendar;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

import event.CalendarEvent;
import parser.*;
import filter.*;

/***
 * Model for tivoo
 * @author bryanyang
 *
 */
public class TivooSystem {
	
	private ArrayList<CalendarEvent> myList;
	
	/***
	 * Constructor initializing the arraylist
	 */
	public TivooSystem(){
		myList = new ArrayList<CalendarEvent>();
	}
	
	/***
	 * Loads all files and add them to the system's array list of objects
	 * @param filename
	 * @throws JDOMException
	 */
	public void loadFile(String filename) throws JDOMException{
		TivooParserFactory factory = new TivooParserFactory(filename);
		TivooParser parser = factory.createParser();
		ArrayList<CalendarEvent> results = parser.parseFile();
		myList.addAll(results);
	}
	
	/****
	 * Filters by an arraylist of multiple keywords
	 * @param query
	 */
	public void filterByKeywords(ArrayList<String> query){
		FilterByKeywords filterer = new FilterByKeywords();
		myList = filterer.filter(myList, query);
	}
	
	/****
	 * Filters by single keywords
	 * @param s
	 */
	public void filterByKeyWords(String s){
		ArrayList<String> list = new ArrayList<String>();
		list.add(s);
		FilterByKeywords filterer = new FilterByKeywords();
		myList = filterer.filter(myList, list);
	}

	
	/****
	 * Generates calendar html output
	 * @throws IOException
	 */
	public void generateCalendar() throws IOException{
		HtmlGenerator calendarCreator = new HtmlTableCalendar(myList, 
				new DateTime(2011, 11, 1, 0, 0), new DateTime(2011, 11, 30, 23, 0));
		calendarCreator.generateOutput();
	}
	

}
