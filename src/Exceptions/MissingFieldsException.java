package Exceptions;

public class MissingFieldsException extends Exception{
    public MissingFieldsException() {
        super("Missing fields in record");
    }
}
