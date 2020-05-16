package swe2.ss20.ue09.blackjack.game;

import swe2.ss20.ue09.blackjack.player.Dealer;
import swe2.ss20.ue09.blackjack.player.HumanPlayer;
import swe2.ss20.ue09.blackjack.player.Player;
import swe2.inout.Out;

import java.util.Random;

public class Blackjack {

    // TODO implement and extend this class
    private Player actualPlayer;
    private GameResult actualGameResult;
    private int numCardsLeft;

    public static final int TOTAL_NUM_CARDS = 52;

    private HumanPlayer human;
    private Dealer dealer;

    public Blackjack() {
        this.human = new HumanPlayer();
        this.dealer = new Dealer();
        this.numCardsLeft = TOTAL_NUM_CARDS;
        this.actualPlayer = this.dealer;
    }

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
        if (this.getHumanPlayer().getValue() == 21) {
            this.actualGameResult = GameResult.PlayerWins;
        }
        if (this.getDealer().getValue() == 21) {
            this.actualGameResult = GameResult.DealerWins;
        }


        return this.actualGameResult;
    }

    /**
     * draw a random card that is still in the deck
     *
     * @return the selected card
     * @throws OutOfCardsException when no more cards are available
     */
    public Card drawCard() {
        // TODO implement
        Card newCard = new Card(new Random().nextInt(numCardsLeft));
        numCardsLeft--;
        actualPlayer.getCards().add(newCard);
        return newCard;
    }

    /**
     * play the game
     */
    public void play() {
        this.drawCard();
        this.actualPlayer = getHumanPlayer();
        this.drawCard();
        this.drawCard();

        this.actualGameResult = this.evaluateCards();
        this.printGameState();


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