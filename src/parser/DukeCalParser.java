package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;
import org.joda.time.DateTime;

import event.CalendarEvent;

public class DukeCalParser extends TivooParser {
	
	

	public DukeCalParser(String filename) throws JDOMException {
	    super(filename);
    }
    

	@Override
    public DateTime parseDate(String input) {

		input = input.substring(0, 4) + "-" + input.substring(4, 6)
		        + "-" + input.substring(6, 11) + ":"
		        + input.substring(11, 13) + ":" + input.substring(13);

		return new DateTime(input);
    }

	@Override
    public void createPaths() throws JDOMException {
		myPaths = new ArrayList<XPath>();
	    myPaths.add(XPath.newInstance("/events/event/summary"));
	    myPaths.add(XPath.newInstance("/events/event/description"));
	    myPaths.add(XPath.newInstance("/events/event/start/utcdate"));
	    myPaths.add(XPath.newInstance("/events/event/end/utcdate"));
	   
    }

}
