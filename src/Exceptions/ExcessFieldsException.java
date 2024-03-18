package Exceptions;

public class ExcessFieldsException extends Exception{
    
    public ExcessFieldsException() {
        super("Error: Excess fields in record! " );
    }
}
