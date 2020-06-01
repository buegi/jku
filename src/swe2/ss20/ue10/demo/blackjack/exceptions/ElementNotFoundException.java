package swe2.ss20.ue10.demo.blackjack.exceptions;

public class ElementNotFoundException extends RuntimeException {
    //-----------------------------------------------------------------
    //  Sets up this exception with an appropriate message.
    //-----------------------------------------------------------------
    public ElementNotFoundException(String collection) {
        super("The target element is not in this " + collection);
    }
}

