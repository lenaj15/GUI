package model;

import java.util.ArrayList;

import org.jdom.JDOMException;

import event.CalendarEvent;
import parser.*;

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
	
	
	
	

}
