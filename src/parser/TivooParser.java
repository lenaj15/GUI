package parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.joda.time.DateTime;

import event.CalendarEvent;

/**
 * Super class of all different parsers.
 * 
 * @author bryanyang
 * 
 */
@SuppressWarnings("rawtypes")
abstract public class TivooParser {

	private String myFileName;
	protected Document myDocument;

	// path of individual elements
	protected String myPath;

	// patth of attributes from individual elements
	protected String[] myAttributePaths;

	/***
	 * Constructor
	 * 
	 * @param doc
	 */
	public TivooParser(Document doc) {
		myDocument = doc;
	}

	/***
	 * Helper method to read all information from file first
	 * 
	 * @return
	 */
	public List parseXML() {
		try {
			XPath currentPath = XPath.newInstance(myPath);
			List listToAdd = currentPath.selectNodes(myDocument);
			return listToAdd;
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * Method responsible for parsing and returning an array list of events to
	 * output in an ArrayList of Calendar Events. Loops through all event
	 * elements and grab specific fields needed
	 * 
	 * @return
	 */
	public abstract ArrayList<CalendarEvent> parseEvent();

	/***
	 * Method responsible for parsing date and return DateTime object
	 * 
	 * @param input
	 * @return
	 */
	protected abstract DateTime parseDate(String input);

	/***
	 * Called by parseEvent to finalize event's attributes
	 * 
	 * @throws JDOMException
	 */
	protected void parseAdditionalAttributes(CalendarEvent event,
	        Element current) throws JDOMException {
		for (String temp : myAttributePaths) {
			XPath newpath = XPath.newInstance(temp);
			List list = newpath.selectNodes(current);
			for(int i = 0 ; i<list.size(); i++){
				Element e = (Element) list.get(i);
				event.addAttribute(temp.substring(temp.lastIndexOf("/")+1), e.getValue());
			}
		}
	}

	/***
	 * Used by parserfactory to check document type
	 * 
	 * @param filename
	 * @return
	 */
	public static Document validateType(String filename) {
		try {
			SAXBuilder builder = new SAXBuilder();
			FileReader XmlFile = new FileReader(filename);
			Document doc = builder.build(XmlFile);
			return doc;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
