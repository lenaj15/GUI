package parser;

import event.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;
import org.jdom.xpath.XPath;

/**
 * Super class of all different parsers
 * 
 * @author bryanyang
 * 
 */
abstract public class TivooParser {

	private String myFileName;
	protected Document myDocument;
	
	protected String[] myPaths;
	

	/***
	 * Constructor with name of file to be parsed
	 * 
	 * @param filename
	 * @throws JDOMException 
	 */
	public TivooParser(String filename) throws JDOMException {
		myFileName = filename;
		myDocument = null;
		loadFile();
	}
	
	public TivooParser(Document doc){
		myDocument = doc;
	}

	/***
	 * Creates parsed XML document based upon filename
	 */
	private void loadFile() {
		SAXBuilder builder = new SAXBuilder();

		File XmlFile = new File(myFileName);

		try {
			myDocument = builder.build(XmlFile);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/***
	 * Helper method to read all information from file first
	 * @return
	 */
	private ArrayList<List> parseXML(){
		ArrayList<List> parsedInformation = new ArrayList<List>();
		for(String temp: myPaths){
			System.out.println(temp);
			XPath currentPath;
            try {
	            currentPath = XPath.newInstance(temp);
	            List listToAdd = currentPath.selectNodes(myDocument);
	            parsedInformation.add(listToAdd);
            } catch (JDOMException e) {
	            e.printStackTrace();
            }
		}
		return parsedInformation;
	}
	
	/***
	 * Method responsible for parsing and returning an array list of events to
	 * output in an ArrayList of Calendar Events
	 * 
	 * @return
	 */
	public ArrayList<CalendarEvent> parseFile() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		
		ArrayList<List> readInformation = parseXML();
		
		int size=readInformation.get(0).size();
		
		for(int i=0; i<size; i++){
			String title, description;
			DateTime start, end;
			
			Element titleElement = (Element) readInformation.get(0).get(i);
			Element descriptionElement = (Element) readInformation.get(1).get(i);
			Element startTimeElement = (Element) readInformation.get(2).get(i);
			Element endTimeElement = (Element) readInformation.get(3).get(i);
			
			title = titleElement.getValue();
			description = descriptionElement.getValue();
			start = parseDate(startTimeElement.getValue());
			end = parseDate(endTimeElement.getValue());
			
			list.add(new CalendarEvent(title, start, end, description));
		}
		
	
		return list;
    }
	
	
	/***
	 * Class responsible for parsing date and return DateTime object
	 * @param input
	 * @return
	 */
	public abstract DateTime parseDate(String input);
	

}
