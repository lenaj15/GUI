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

	    return null;
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
