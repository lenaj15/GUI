package parser;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

public class NFLCalParser extends TivooParser {

	protected String[] myPaths = { "/document/row/Col1", "/document/row/Col15",
	        "/document/row/Col8", "/document/row/Col9" };
	
	public NFLCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 10) + "T" + input.substring(11) + "Z";
		return new DateTime(input);
	}

}
