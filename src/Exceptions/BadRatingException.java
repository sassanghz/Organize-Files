package Exceptions;

public class BadRatingException extends Throwable{
    
    public BadRatingException(String message) {
        super(message);
    }
}
