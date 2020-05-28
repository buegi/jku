package swe2.ss20.ue10.blackjack.game;

public class OutOfCardsException extends RuntimeException {
    public OutOfCardsException() {
        super("No more cards left in the deck.");
    }
}