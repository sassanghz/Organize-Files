package Exceptions;

public class BadDurationException extends Exception{
    
    public BadDurationException() {
        super("Invalid duration");
    }
}
