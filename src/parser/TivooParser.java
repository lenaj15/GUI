package parser;

import event.*;

import java.util.ArrayList;

/**
 * Super class of all different parsers
 * 
 * @author bryanyang
 * 
 */
abstract public class TivooParser {

	/**
	 * Constructors should contain file name that the particular instance of
	 * Tivoo Parser is responsible for parsing
	 */
	private String myFileName;

	/***
	 * Method responsible for parsing and returning an array list of events to
	 * output
	 * 
	 * @return
	 */
	public abstract ArrayList<CalendarEvent> parseFile();

}
