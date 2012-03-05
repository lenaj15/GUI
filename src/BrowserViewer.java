import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.*;
import javax.swing.*;

import org.jdom.JDOMException;

import model.TivooSystem;


/**
 * A class used to display the viewer for a simple HTML browser.
 * 

 */
@SuppressWarnings("serial")
public class BrowserViewer extends JPanel
{
	// constants
    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    // web page
    private JEditorPane myPage;
    // information area
    private JLabel myStatus;
    // navigation
    private JTextField myStart;
    private JTextField myEnd;
    private JButton myLoadButton;
    private JButton myNextButton;
    private JButton myResetFilterButton;
    private DefaultListModel myChosenFiles;
    // favorites
    private MutableComboBoxModel myIncludeFilter;
    private MutableComboBoxModel myExcludeFilter;
    private JComboBox myIncludeDisplay;
    private JComboBox myExcludeDisplay;
   
    private JRadioButton myDatesAscend;
    private JRadioButton myDatesDescend; 
   
    private JRadioButton myKeywordsAscend;
    private JRadioButton myKeywordsDescend;
    
    private ButtonGroup myDates;
    private ButtonGroup myKeywords;
    
    
    // Go preview the html page
    private JButton myPreviewButton;
    // the real worker
    protected BrowserModel myModel;
    


    /**
     * Create a view of the given model of a web browser.
     */
    public BrowserViewer (BrowserModel model)
    {
        myModel = model;
        // add components to frame
        setLayout(new BorderLayout());
        // must be first since other panels may refer to page
       // add(makePageDisplay(), BorderLayout.CENTER);
       
        add(makeNavigationPanel(), BorderLayout.NORTH);
        add(makePreferencesPanel(), BorderLayout.WEST);
        add(makeInputPanel(), BorderLayout.SOUTH);
       // add(makeInformationPanel(), BorderLayout.SOUTH);
        // control the navigation
        enableButtons();
    }


    /**
     * Display given URL.
     * @throws IOException 
     * @throws JDOMException 
     */
    public void showPage () throws JDOMException, IOException
    {
        //myModel.go();
        
    }

    /**
     * Display given message as an error in the GUI.
     */
    public void showError (String message)
    {
        JOptionPane.showMessageDialog(this,
        		                      message, 
        		                      "Browser Error",
        		                      JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Display given message as information in the GUI.
     */
    public void showStatus (String message)
    {
        myStatus.setText(message);
    }

  

    // load selected file
    private void load ()
    {
    	 JFileChooser fc = new JFileChooser();
         
         int returnVal = fc.showOpenDialog(null);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
             File file = fc.getSelectedFile(); 
             myModel.load(file);
             myChosenFiles.addElement(file);
         }
         
    }


    
    // only enable buttons when useful to user
    private void enableButtons ()
    {
    	
        myLoadButton.setEnabled(true);
       
        myResetFilterButton.setEnabled(true);
    }

    // convenience method to create HTML page display
	private JComponent makePageDisplay ()
	{
        // displays the web page
        myPage = new JEditorPane();
        myPage.setPreferredSize(SIZE);
        // allow editor to respond to link-clicks/mouse-overs
        myPage.setEditable(false);
        myPage.addHyperlinkListener(new LinkFollower());
		return new JScrollPane(myPage);
	}

	private JComponent makeDatePanel(){
		JPanel panel = new JPanel();
        JLabel text = new JLabel("Filter between: Start date YYYY/MM/DD");
        panel.add(text);
        myStart = new JTextField(35);
        panel.add(myStart);
        JLabel end = new JLabel("End Date YYYY/MM/DD");
        panel.add(end);
        myEnd = new JTextField(35);
        panel.add(myEnd);
        return panel;
	}
	
	
    // organize user's options for controlling/giving input to model
    private JComponent makeInputPanel ()
    {
        JPanel panel = new JPanel(new BorderLayout());
       
        panel.add(makeDatePanel(),BorderLayout.WEST);
        panel.add(makeSortPanel(), BorderLayout.NORTH);
        panel.add(makePreviewPanel(), BorderLayout.AFTER_LAST_LINE);
        return panel;
    }

    // make the panel where "would-be" clicked URL is displayed
    private JComponent makeInformationPanel ()
    {
        // BLANK must be non-empty or status label will not be displayed in GUI
        myStatus = new JLabel(BLANK);
        return myStatus;
    }

    // make user-entered URL/text field and back/next buttons
    private JComponent makeNavigationPanel ()
    {
        JPanel panel = new JPanel();
        myLoadButton = new JButton("Add File(s) to process:");
        myLoadButton.addActionListener(new ShowLoadAction());
        panel.add(myLoadButton);
        myChosenFiles= new DefaultListModel();
        myChosenFiles.addElement("Files selected: ");
        JList list = new JList(myChosenFiles);
        panel.add(list);

     
        return panel;
    }
    class MyActionListener implements ActionListener {
        // Retain the previously selected item in order to determine whether
        // the new item is the same
        Object oldItem;

        // This method is called whenever the user or program changes the selected item.
        // Note: The new item may be the same as the previous item.
        public void actionPerformed(ActionEvent evt) {
            JComboBox cb = (JComboBox)evt.getSource();

            // Get the new item
            Object newItem = cb.getSelectedItem();


            if ("comboBoxEdited".equals(evt.getActionCommand())) {
                // User has typed in a string; only possible with an editable combobox
            	if (!contains(cb, newItem))
            		cb.addItem(newItem);
            	
            } else if ("comboBoxChanged".equals(evt.getActionCommand())) {
                // User has selected an item; it may be the same item
            }
        }
    }
    
    private boolean contains(JComboBox box, Object item){
    	for (int i=0; i<box.getItemCount(); i++){
    		if (item.equals(box.getItemAt(i)))
    			return true;
    	}
    	return false;
    }
    
    private ArrayList toList(JComboBox box){
    	ArrayList<String> l= new ArrayList<String>();
    	for (int i=1; i<box.getItemCount(); i++){
    		l.add(box.getItemAt(i).toString());
    	}
    	return l;
    }
    
    
    // make buttons for setting favorites/home URLs
    private JComponent makePreferencesPanel ()
    {
        JPanel panel = new JPanel();
        myIncludeFilter = new DefaultComboBoxModel();  
        myIncludeFilter.addElement(" Include events with the below keywords "); 
        myIncludeDisplay = new JComboBox(myIncludeFilter);
        myIncludeDisplay.setEditable(true);
        MyActionListener includeListener = new MyActionListener();
        myIncludeDisplay.addActionListener(includeListener);
        panel.add(myIncludeDisplay);
        
        myExcludeFilter = new DefaultComboBoxModel();
        myExcludeFilter.addElement(" Exclude events with the below keywords ");
        myExcludeDisplay = new JComboBox(myExcludeFilter);
        MyActionListener excludeListener = new MyActionListener();
        myExcludeDisplay.addActionListener(excludeListener);
        myExcludeDisplay.setEditable(true);
        panel.add(myExcludeDisplay);

        myResetFilterButton = new JButton("Reset Filters");
        myResetFilterButton.addActionListener(new ResetFiltersAction());
        panel.add(myResetFilterButton);

        return panel;
    }
    
   
    
    private JComponent makeSortPanel ()
    {
        JPanel panel = new JPanel();
        JLabel text = new JLabel("Sort by Dates");
        panel.add(text);
      //  mySortDates.addActionListener(l);
       myDatesAscend = new JRadioButton("Ascending");
       myDatesDescend = new JRadioButton("Descending");
       ButtonGroup dates = new ButtonGroup();
       
       dates.add(myDatesAscend);
       dates.add(myDatesDescend);
       myDatesDescend.setSelected(true);
       myDates = dates;
       panel.add(myDatesAscend);
       panel.add(myDatesDescend);
       
        JLabel text2 = new JLabel("Sort by Title");
        panel.add(text2);
        myKeywordsAscend = new JRadioButton("Ascending");
        myKeywordsDescend = new JRadioButton("Descending");
        myKeywordsDescend.setSelected(true);
        ButtonGroup keywords = new ButtonGroup();
        
        keywords.add(myKeywordsAscend);
        keywords.add(myKeywordsDescend);
        panel.add(myKeywordsAscend);
        panel.add(myKeywordsDescend);
        myKeywords = keywords;
    
        
        return panel;
    }
    
    // make buttons for setting favorites/home URLs
    private JComponent makePreviewPanel ()
    {
        JPanel panel = new JPanel();
        //PREVIEW GO BUTTON
        myPreviewButton = new JButton("Preview HTML Output");
        myPreviewButton.addActionListener(new ShowPageAction());
        panel.add(myPreviewButton);

        return panel;
    }

	/**
     * Inner class to factor out showing page associated with the entered URL
     */
    private class ShowPageAction implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
			String[]x= new String [3]; 
			String[]y= new String [3]; 
			
			if (!myStart.getText().equals("") && !myEnd.getText().equals("")){
			 
				x=myStart.getText().split("/");
				y=myEnd.getText().split("/");
				
			}
				
			else{
				x[0]=new String("-1");
				y[0]=new String ("-1");
			}
			try {
	            myModel.go(toList(myIncludeDisplay),toList(myExcludeDisplay), x,y, myDatesAscend.isSelected(),myKeywordsAscend.isSelected());
            } catch (JDOMException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
            } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
            }
		}
	}
    /**
     * Inner class to factor out showing page associated with the entered URL
     */
    private class ShowLoadAction implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
		    load();
		    
		}
	}
    /**
     * Inner class to factor out showing page associated with the entered URL
     */
    private class ResetFiltersAction implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
		    myIncludeDisplay.removeAllItems();
		    myIncludeFilter.addElement(" Include events with the below keywords "); 
		    myExcludeDisplay.removeAllItems();
		    myExcludeFilter.addElement(" Exclude events with the below keywords "); 
			myModel.resetFilters();
		}
	}

    

	/**
     * Inner class to deal with link-clicks and mouse-overs
     */
    private class LinkFollower implements HyperlinkListener
    {
        public void hyperlinkUpdate (HyperlinkEvent evt)
        {
            // user clicked a link, load it and show it
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
            {
            //	showPage(evt.getURL().toString());
            }
            // user moused-into a link, show what would load
            else if (evt.getEventType() == HyperlinkEvent.EventType.ENTERED)
            {
            	showStatus(evt.getURL().toString());
            }
            // user moused-out of a link, erase what was shown
            else if (evt.getEventType() == HyperlinkEvent.EventType.EXITED)
            {
                showStatus(BLANK);
            }
        }
    }
}
