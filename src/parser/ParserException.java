package parser;

@SuppressWarnings("serial")
public class ParserException extends RuntimeException{
	public static enum Type { INVALID_FILE, PARSING_ERROR };
    private Type myType;
    
    public ParserException (String message)
    {
        this(message, Type.PARSING_ERROR);
    }
    
    public ParserException (String message, Type type)
    {
        super(message);
        myType = type;
    }
    
    public Type getType()
    {
    	return myType;
    }
}
