package swe2.ss20.ue09.blackjack.game;

import swe2.ss20.ue09.blackjack.player.Dealer;
import swe2.ss20.ue09.blackjack.player.HumanPlayer;
import swe2.ss20.ue09.blackjack.player.Player;
import swe2.inout.Out;

import java.util.ArrayList;
import java.util.Random;

public class Blackjack {

    // TODO implement and extend this class
    private Player player;
    private GameResult result;
    private ArrayList<Card> deck;

    public static final int TOTAL_NUM_CARDS = 52;

    private HumanPlayer human;
    private Dealer dealer;

    public Blackjack() {
        this.human = new HumanPlayer();
        this.dealer = new Dealer();
        this.deck = new ArrayList<Card>(TOTAL_NUM_CARDS);
        this.player = this.dealer;
        this.initializeDeck();
    }

    private void initializeDeck() {
        for (int i = 0; i < TOTAL_NUM_CARDS; i++) {
            deck.add(new Card(i));
        }
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
        return GameResult.Draw;
    }

    /**
     * draw a random card that is still in the deck
     *
     * @return the selected card
     * @throws OutOfCardsException when no more cards are available
     */
    public Card drawCard() throws OutOfCardsException {
        // TODO implement
        if (deck.isEmpty()) {
            throw new OutOfCardsException();
        }
        return deck.remove(new Random().nextInt(deck.size()));
    }

    /**
     * play the game
     */
    public void play() {
        player.getCards().add(this.drawCard());
        this.player = getHumanPlayer();
        player.getCards().add(this.drawCard());
        player.getCards().add(this.drawCard());

        this.result = this.evaluateCards();
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