package parser;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

public class DukeCalParser extends TivooParser {

	protected String[] myPaths = { "/events/event/summary",
	        "/events/event/description", "/events/event/start/utcdate",
	        "/events/event/end/utcdate" };

	public DukeCalParser(String filename) throws JDOMException {

		super(filename);
	}

	@Override
	public DateTime parseDate(String input) {

		input = input.substring(0, 4) + "-" + input.substring(4, 6) + "-"
		        + input.substring(6, 11) + ":" + input.substring(11, 13) + ":"
		        + input.substring(13);

		return new DateTime(input);
	}

}
