package parser;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

public class DukeBasketballCalParser extends TivooParser {

	protected String[] myPaths = { "/dataroot/Calendar/Subject", "/dataroot/Calendar/Location",
	        "/document/row/Col8", "/document/row/Col9" };
	
	public DukeBasketballCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 10) + "T" + input.substring(11) + "Z";
		return new DateTime(input);
	}

}
