package parser;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

public class NFLCalParser extends TivooParser {

	public NFLCalParser(String filename) throws JDOMException {
		super(filename);
	}

	protected String[] myPaths = { "/document/row/Col1", "/document/row/Col15",
	        "/document/row/Col8", "/document/row/Col9" };

	public DateTime parseDate(String input) {

		input = input.substring(0, 10) + "T" + input.substring(11) + "Z";

		return new DateTime(input);
	}

}
