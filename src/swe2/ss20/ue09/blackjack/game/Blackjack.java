package swe2.ss20.ue09.blackjack.game;

import swe2.inout.In;
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
    private int commitment;

    public static final int TOTAL_NUM_CARDS = 52;

    private HumanPlayer human;
    private Dealer dealer;

    public Blackjack() {
        this.human = new HumanPlayer();
        this.dealer = new Dealer();
        this.deck = new ArrayList<Card>(TOTAL_NUM_CARDS);
        this.commitment = 0;
        this.initializeDeck();
    }

    private void initializeDeck() {
        for (int i = 0; i < TOTAL_NUM_CARDS; i++) {
            deck.add(new Card(i));
        }
    }

    private void initalizeGame() {
        // two cards for player
        this.commitment = 1;
        this.human.setChips(this.human.getChips() - 1);
        this.player = this.getDealer();
        player.getCards().add(this.drawCard());
        player = this.getHumanPlayer();
        player.getCards().add(this.drawCard());
        player.getCards().add(this.drawCard());
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
        // player has more than 21 => end round - commitment lost
        if (this.human.getValue() > 21) {
            this.commitment = 0;
            return this.result = GameResult.DealerWins;
        }

        // player && dealer have blackjack - 1x commitment
        if (this.human.getValue() == 21 && this.dealer.getValue() == 21) {
            return this.result = GameResult.Draw;
        }

        // only player has blackjack - 3x commitment
        if (this.getHumanPlayer().getValue() == 21 && !(this.getDealer().getValue() == 21)) {
            return this.result = GameResult.PlayerWins;
        }

        // dealer overdraws - 2x commitment
        if (this.getDealer().getValue() > 21) {
            return this.result = GameResult.PlayerWins;
        }

        // player has higher value than dealer - 2x commitment
        if (this.getHumanPlayer().getValue() > this.getDealer().getValue()) {
            return this.result = GameResult.PlayerWins;
        }

        // player and dealer have draw (last option) - 1x commitment
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
        // TODO implement
        this.initalizeGame();
        this.printGameState();
        while (this.result != GameResult.DealerWins || this.result != GameResult.PlayerWins) {
            this.player.makeTurn();

            if (this.human.getState() == Turn.Hit) {


            }

            if (this.human.getState() == Turn.Stay) {

            }

            if (this.human.getState() == Turn.DoubleDown) {

            }

            this.result = this.evaluateCards();
        }

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