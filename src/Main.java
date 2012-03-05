import html_generator.HtmlConflictTable;
import html_generator.HtmlGenerator;
import html_generator.HtmlSortedList;
import html_generator.HtmlTableCalendar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.TivooSystem;

import org.jdom.JDOMException;
import org.joda.time.DateTime;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Main
{
    // convenience constants
    public static final String TITLE = "Lena Tivoo Browser";


    public static void main (String[] args) throws IOException, JDOMException
    {
    	
    	// create program specific components
        BrowserModel model = new BrowserModel();
        BrowserViewer display = new BrowserViewer(model);
        // create container that will work with Window manager
        JFrame frame = new JFrame(TITLE); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components to Frame and show it
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
      
    }
}



