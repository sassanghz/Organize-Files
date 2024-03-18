package Exceptions;

public class BadScoreException extends Exception{
    
    public BadScoreException() {
        super("Invalid score");
    }
}
