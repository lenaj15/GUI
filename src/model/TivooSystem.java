package model;

import html_generator.*;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

import parser.TivooParser;
import parser.TivooParserFactory;
import event.CalendarEvent;
import filter.FilterByKeywords;

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
		myList = filterer.filter(myList, query, true);
	}
	
	/****
	 * Filters by single keywords
	 * @param s
	 */
	public void filterByKeyWords(String s){
		ArrayList<String> list = new ArrayList<String>();
		list.add(s);
		FilterByKeywords filterer = new FilterByKeywords();
		myList = filterer.filter(myList, list, true);
	}

	/****
	 * Sorts by title (ascending order)
	 * @param s
	 */
	public void sortByTitle(){
		FilterByKeywords filterer = new FilterByKeywords();
		filterer.sortByTitle(myList);
	}
	
	/****
	 * Sorts by start date
	 * @param s
	 */
	public void sortByStartDate(){
		FilterByKeywords filterer = new FilterByKeywords();
		filterer.sortByStart(myList);
	}
	
	
	/****
	 * Prints out list of CalendarEvents, debugging purposes
	 */
	public void printList() {
		for (CalendarEvent e: myList) {
			System.out.println(e.getMyTitle()+ " "+ e.getMyDatesString());
		}
	}
	
	/****
	 * Generates calendar html output
	 * @throws IOException
	 */
	public void generateCalendar() throws IOException{
		HtmlGenerator calendarCreator = new HtmlTableCalendar(myList, 
				new DateTime(2000, 11, 1, 0, 0), new DateTime(2012, 11, 30, 23, 0));
		calendarCreator.generateOutput();
	}
	
	/****
	 * Generates calendar html output
	 * @throws IOException
	 */
	public void generateConflictTable() throws IOException{
		HtmlGenerator conflictCreator = new HtmlConflictTable(myList, 
				null, null);
		conflictCreator.generateOutput();
	}
	
	/****
	 * Generates calendar html output
	 * @throws IOException
	 */
	public void generateSortedList() throws IOException{
		HtmlGenerator sortedListCreator = new HtmlSortedList(myList, 
				null, null);
		sortedListCreator.generateOutput();
	}
	

}
