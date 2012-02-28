import html_generator.HtmlConflictTable;
import html_generator.HtmlGenerator;
import html_generator.HtmlSortedList;
import html_generator.HtmlTableCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.TivooSystem;

import org.jdom.JDOMException;
import org.joda.time.DateTime;


public class Main {
	public static void main(String[] args) throws IOException, JDOMException {

		TivooSystem s = new TivooSystem();
		s.loadFile("dukecal.xml");
		int x  = 3;

	}

}
