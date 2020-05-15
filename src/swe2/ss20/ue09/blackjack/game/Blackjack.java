package swe2.ss20.ue09.blackjack.game;

import swe2.ss20.ue09.blackjack.player.Dealer;
import swe2.ss20.ue09.blackjack.player.HumanPlayer;
import swe2.ss20.ue09.blackjack.player.Player;
import swe2.inout.Out;

public class Blackjack {

    // TODO implement and extend this class

    public static final int TOTAL_NUM_CARDS = 52;

    private HumanPlayer human;
    private Dealer dealer;

    public Player getHumanPlayer() {
        return human;
    }

    public Player getDealer() {
        return dealer;
    }

    /**
     * evaluate which player won this round
     *
     * @return the result of this round
     */
    public GameResult evaluateCards() {

        // TODO implement

        return GameResult.Draw;
    }

    /**
     * draw a random card that is still in the deck
     *
     * @return the selected card
     * @throws OutOfCardsException when no more cards are available
     */
    public Card drawCard() {

        // TODO implement

        return null;
    }

    /**
     * play the game
     */
    public void play() {

        // TODO implement

    }

    /**
     * print the cards of both players and their value
     */
    public void printGameState() {

        Out.println("Dealer (" + dealer.getValue() + ")");
        for (Card c : dealer.getCards()) {
            Out.print(c + " ");
        }
        Out.println();

        Out.println("Player (" + human.getValue() + ")");
        for (Card c : human.getCards()) {
            Out.print(c + " ");
        }
        Out.println("\n");
    }
}