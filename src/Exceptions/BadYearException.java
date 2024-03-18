package Exceptions;

public class BadYearException extends Exception{

    public BadYearException() {
        super("Invalid year");
    }
}
