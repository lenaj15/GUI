package html_generator;

import html.Tag;

import java.io.IOException;
import java.util.ArrayList;

import event.CalendarEvent;

@SuppressWarnings("unchecked")
public class HtmlVerticalHeaders extends HtmlGenerator {

	public HtmlVerticalHeaders(ArrayList<CalendarEvent> list) {
		super(list);
	}

	public void generateOutput() throws IOException {
		createSubFolder();
		Tag html = generateHeader();
		Tag body = new Tag("body");
		
		int fileIndex = 1;
		
		for (Object key: outputMap.keySet())
		{
			Tag h1 = generateKeyEntry(key);
			for (CalendarEvent value: outputMap.get(key))
			{
				h1 = generateValueEntry(h1, value, fileIndex);
				fileIndex++;
			}
			body.add(h1);
		}
		html.add(body);
		
		printResult(html);
	}

	protected Tag generateKeyEntry(Object key) {
		Tag h1 = new Tag("h1");
		h1.add(key.toString());
		return h1;
	}

	protected Tag generateValueEntry(Tag h1, CalendarEvent value, int fileIndex) throws IOException {
		generateSubPage(value, fileIndex);
		
		Tag h4 = new Tag("h4");
		Tag link = new Tag("a", "href="+ filepath + folder + "output" + Integer.toString(fileIndex)+ ".html");
		link.add(value.getMyTitle());
		h4.add(link);
		h1.add(h4);
		return h1;
	}

}
