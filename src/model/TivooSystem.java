package model;

import html_generator.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

import parser.TivooParser;
import parser.TivooParserFactory;
import event.CalendarEvent;
import filter.FilterByDates;
import filter.FilterByKeywords;
import filter.Sorter;

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
	
	/****
	 * Reset boolean values
	 * @param s
	 */
	public void resetisOutput(){
		for (CalendarEvent e: myList){
			e.setisOutput(true);
		}
	}
	
	/***
	 * Loads all files and add them to the system's array list of objects
	 * @param filename
	 * @throws JDOMException
	 */
	public void loadFile(String filename) throws JDOMException{
		TivooParserFactory factory = new TivooParserFactory(filename);
		TivooParser parser = factory.createParser();
		ArrayList<CalendarEvent> results = parser.parseEvent();
		myList.addAll(results);
	}
	
	public void loadFile(File file) throws JDOMException{
		TivooParserFactory factory = new TivooParserFactory(file.getName());
		TivooParser parser = factory.createParser();
		ArrayList<CalendarEvent> results = parser.parseEvent();
		myList.addAll(results);
	}
	
	/****
	 * Filters by an arraylist of multiple keywords
	 * @param query
	 */
	public void filterByKeywords(ArrayList<String> query, boolean val){
		FilterByKeywords filterer = new FilterByKeywords();
		myList = filterer.filter(myList, query, val);
		
	}
	
	/****
	 * Filters by single keywords
	 * @param s
	 */
	public void filterByKeyWords(String s, boolean val){
		ArrayList<String> list = new ArrayList<String>();
		list.add(s);
		FilterByKeywords filterer = new FilterByKeywords();
		myList = filterer.filter(myList, list, val);
		
	}

	/****
	 * Filters by single keywords in a specific attribute
	 * @param s
	 */
	public void filterByKeyWords(String s, String attribute, boolean val){
		ArrayList<String> list = new ArrayList<String>();
		list.add(s);
		FilterByKeywords filterer = new FilterByKeywords();
		myList = filterer.filter(myList, list, val, attribute);
		
	}

	/****
	 * Filters by date period
	 * @param s
	 */
	public void filterByDates(int syear, int smonth, int sday, int shour, int smin, int eyear, int emonth, int eday, int ehour, int emin, boolean val){
		
		FilterByDates filterer = new FilterByDates();
		myList = filterer.filter(myList, new DateTime(syear, smonth, sday, shour, smin), new DateTime(eyear, emonth, eday, ehour, emin), val);
		
	}
	
	/****
	 * Filters by date period, taking in datetime objects
	 * @param s
	 */
	public void filterByDates(DateTime start, DateTime end, boolean val){
		
		FilterByDates filterer = new FilterByDates();
		myList = filterer.filter(myList, start, end, val);
		
	}

	
	/****
	 * Sorts by title (ascending order)
	 * @param s
	 */
	public void sortByTitle(){
		Sorter.sortByTitle(myList);
		
	}
	
	public void sortByTitleDesc(){
		
		Sorter.sortByTitleDescend(myList);
		
	}
	
	/****
	 * Sorts by start date
	 * @param s
	 */
	public void sortByStartDate(){
		Sorter.sortByStart(myList);
		
	}
	
	public void sortByStartDesc(){
		Sorter.sortByStartDescend(myList);
		
	}
	
	/****
	 * Prints out list of CalendarEvents, debugging purposes
	 */
	public void printList() {
		for (CalendarEvent e: myList) {
			if (e.getisOutput())
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
