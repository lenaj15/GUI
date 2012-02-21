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
		Document doc = loadFile();
		
		String rootNode = doc.getRootElement().getValue();
		
		if(rootNode.equals("events")) return new DukeCalParser(doc);
		
		return null;
		
	}
	
	private Document loadFile() {
		SAXBuilder builder = new SAXBuilder();
		Document returnDocument;

		File XmlFile = new File(myFileName);

		try {
			returnDocument = builder.build(XmlFile);
			return returnDocument;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return null;
	}
}
