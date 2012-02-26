package parser;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class TivooParserFactory {
	
	private String myFileName;
	
	public TivooParserFactory(String filename){
		myFileName = filename;
	}

	public TivooParser createParser() throws JDOMException{
		Document doc = TivooParser.validateType(myFileName);
		
		String rootNode = doc.getRootElement().getName();
		
		if(rootNode.equals("events")) return new DukeCalParser(doc);
		if(rootNode.equals("document")) return new NFLCalParser(doc);
		if(rootNode.equals("feed")) return new GoogleCalParser(doc);
		if(rootNode.equals("dataroot")) return new DukeBasketballCalParser(doc);
		if(rootNode.equals("tv")) return new TVCalParser(doc);
		
		
		return null;
		
	}
	
}
