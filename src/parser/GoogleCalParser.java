package parser;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

public class GoogleCalParser extends TivooParser {

	protected String[] myPaths = {"/feed/entry/title", "/feed/entry/content", ""};
	
	public GoogleCalParser(Document doc) throws JDOMException {
		super(doc);
		super.myPaths = this.myPaths;
		
	}

	public DateTime parseDate(String input) {

		input = input.substring(0, 10) + "T" + input.substring(11) + "Z";
		return new DateTime(input);
	}

}
