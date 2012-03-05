import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jdom.JDOMException;

import model.TivooSystem;

public class BrowserModel
{
    private String myHome;
    private String myCurrentURL;
    private int myCurrentIndex;
    private List<File> myFiles;
    private List<String> myIncludeKeywords;
    private List<String> myExcludeKeywords;
    private TivooSystem mySystem;
   // private Map<String, String> myFavorites;


    /**
     * Creates an empty model.
     */
    public BrowserModel ()
    {
        myHome = null;
        myCurrentURL = "";
        myCurrentIndex = -1;
        myFiles = new ArrayList<File>();
        myIncludeKeywords = new ArrayList<String>();
        myExcludeKeywords = new ArrayList<String>();
        mySystem = new TivooSystem();
        // myFavorites = new HashMap<String, String>();
    }


    /**
     * Returns the first page in next history, null if next history is empty.
     */
    public String next ()
    {
        if (hasNext())
        {
            myCurrentIndex++;
            return "";
        }
        return null;
    }


    /**
     * Returns the first page in back history, null if back history is empty.
     */
    public String back ()
    {
        if (hasPrevious())
        {
            myCurrentIndex--;
            return "";
        }
        return null;
    }


    /**
     * Changes current page to given URL, removing next history.
     * @throws JDOMException 
     * @throws IOException 
     */
    public void go (ArrayList<String> include, ArrayList<String> exclude, String[] start, String []end, boolean dates, boolean keywords) throws JDOMException, IOException
    {	
    	
    	for (File f : myFiles){
    		mySystem.loadFile(f);
    	}
    	
    	mySystem.filterByKeywords(include, true);
    	mySystem.filterByKeywords(exclude, false);
    	
    	
    	if (!start[0].equals("-1") && !end[0].equals("-1")){
    		mySystem.filterByDates(new Integer(start[0]).intValue(), new Integer(start[1]).intValue(), new Integer(start[2]).intValue(),0, 0, new Integer(end[0]).intValue(), new Integer(end[1]).intValue(), new Integer(end[2]).intValue(), 23, 59, true);
    	}
    	if(dates)
    		mySystem.sortByStartDate();
    	else
    		mySystem.sortByStartDesc();
    	if (keywords)
    		mySystem.sortByTitle();
    	else
    		mySystem.sortByTitleDesc();
    	mySystem.generateSortedList();
    	File f= new File("output.html");
    	
    	FileChooser filechoose= new FileChooser(f.toURI().toString());
    	
    }
   

    /**
     * adds file into linked list.
     */
    public void load (File f)
    {
    	myFiles.add(f);
    }

    /**
     * Returns true if there is a next URL available
     */
    public boolean hasNext ()
    {
        return false;
    }


    /**
     * Returns true if there is a previous URL available
     */
    public boolean hasPrevious ()
    {
        return myCurrentIndex > 0;
    }

    /**
     * Sets current home page to the current URL being viewed.
     */
    public void resetFilters ()
    {
    	
        mySystem.resetisOutput();
    	myIncludeKeywords.clear();
        myExcludeKeywords.clear();
    }


   /* 
    public void addFavorite (String name)
    {
    	myFavorites.put(name, myCurrentURL);
    }

    public String getFavorite (String name)
    {
    	return myFavorites.get(name);
    }
    */
}
