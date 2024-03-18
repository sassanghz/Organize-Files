package Exceptions;

public class BadGenreException  extends Exception{
        
        public BadGenreException() {
            super("Invalid genre");
        }
}
