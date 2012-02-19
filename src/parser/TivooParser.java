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
	protected ArrayList<XPath> myPaths;
	

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
		createPaths();
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

	public abstract void createPaths() throws JDOMException;
	
	/***
	 * Method responsible for parsing and returning an array list of events to
	 * output
	 * 
	 * @return
	 */
	public ArrayList<CalendarEvent> parseFile() {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		
		ArrayList<List> readInformation = new ArrayList<List>();
		
		int size=0;
		
		for(XPath temp: myPaths){
			try {
	            List listToAdd = temp.selectNodes(myDocument);
	            readInformation.add(listToAdd);
	            size=listToAdd.size();
            } catch (JDOMException e) {
	            e.printStackTrace();
            }
		}
		
		for(int i=0; i<size; i++){
			String title, description;
			DateTime start, end;
			
			Element titleElement = (Element) readInformation.get(0).get(i);
			Element descriptionElement = (Element) readInformation.get(0).get(i);
			Element startTimeElement = (Element) readInformation.get(0).get(i);
			Element endTimeElement = (Element) readInformation.get(0).get(i);
			
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
