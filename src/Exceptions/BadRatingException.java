package Exceptions;

public class BadRatingException extends Exception{
    
    public BadRatingException() {
        super("Invalid rating");
    }
}
