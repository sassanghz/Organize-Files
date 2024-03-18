package Exceptions;

public class MissingQuotesException extends Exception{
    
    public MissingQuotesException() {
        super("Error: Missing quotes in record! " );
    }
}
