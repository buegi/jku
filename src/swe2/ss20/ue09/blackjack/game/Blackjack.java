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

    public Player getHumanPlayer() {
        return human;
    }

    public Player getDealer() {
        return dealer;
    }

    private void initializeDeck() {
        for (int i = 0; i < TOTAL_NUM_CARDS; i++) {
            deck.add(new Card(i));
        }
    }

    private void initalizeGame() {
        // two cards for player
        this.human.setChips(this.human.getChips() - 1);
        this.commitment = 1;
        this.player = this.dealer;
        this.player.addCard(this.drawCard());
        this.otherPlayer();
        this.player.addCard(this.drawCard());
        this.player.addCard(this.drawCard());
        this.printGameState();
    }

    private void resetGame() {
        this.commitment = 0;
        this.deck.clear();
        this.human.clear();
        this.dealer.clear();
        this.initializeDeck();
        this.initalizeGame();
    }

    /**
     * evaluate which player won this round
     *
     * @return the result of this round
     */
    public GameResult evaluateCards() {
        // TODO implement
        // player has more than 21 => end round - commitment lost
        if (this.human.overDrawn()) {
            this.commitment = 0;
            return this.result = GameResult.DealerWins;
        }

        // player && dealer have blackjack - 1x commitment
        if (this.human.hasBlackJack() && this.dealer.hasBlackJack()) {
            this.human.setChips(this.human.getChips() + this.commitment);
            this.commitment = 0;
            return this.result = GameResult.Draw;
        }

        // only player has blackjack - 3x commitment
        if (this.human.hasBlackJack() && !(this.dealer.hasBlackJack())) {
            this.human.setChips(this.human.getChips() + (3 * this.commitment));
            return this.result = GameResult.PlayerWins;
        }

        // only dealer has blackjack
        if (this.dealer.hasBlackJack()) {
            this.commitment = 0;
            return this.result = GameResult.DealerWins;
        }

        // dealer overdraws - 2x commitment
        if (this.dealer.overDrawn()) {
            this.human.setChips(this.human.getChips() + (2 * this.commitment));
            this.commitment = 0;
            return this.result = GameResult.PlayerWins;
        }

        // player has higher value than dealer - 2x commitment
        if (this.human.getValue() > this.dealer.getValue()) {
            this.human.setChips(this.human.getChips() + (2 * this.commitment));
            this.commitment = 0;
            return this.result = GameResult.PlayerWins;
        }

        // dealer has higher value than player
        if (this.dealer.getValue() > this.human.getValue()) {
            this.commitment = 0;
            return this.result = GameResult.DealerWins;
        }

        // player and dealer have draw (last option) - 1x commitment
        this.human.setChips(this.human.getChips() + this.commitment);
        this.commitment = 0;
        return this.result = GameResult.Draw;
    }

    /**
     * draw a random card that is still in the deck
     *
     * @return the selected card
     * @throws OutOfCardsException when no more cards are available
     */
    public Card drawCard() throws OutOfCardsException {
        if (deck.isEmpty()) {
            throw new OutOfCardsException();
        }
        return deck.remove(new Random().nextInt(deck.size()));
    }

    private void otherPlayer() {
        if (this.player.equals(human)) {
            this.player = this.dealer;
        } else {
            this.player = this.human;
        }
    }

    private void doubleCommitment() {
        this.commitment *= 2;
    }

    private boolean playAgain() {
        System.out.println("You have " + this.human.getChips() + " chips left. Do you want to play again (y/n)?");
        char playerAction = In.readChar();
        while (playerAction != 'y' && playerAction != 'n') {
            System.out.println("Invalid input!");
            playerAction = In.readChar();
        }
        return playerAction == 'y';
    }


    /**
     * play the game
     */
    public void play() {
        while (this.human.getChips() > 0) {
            this.resetGame();
            // players turn
            while (this.player.equals(human) && !this.player.overDrawn()) {
                Turn playerTurn = this.player.makeTurn();
                if (player.hasBlackJack() || playerTurn.equals((Turn.Stay))) {
                    this.otherPlayer();
                }
                if (playerTurn.equals(Turn.DoubleDown) && this.player.getCards().size() == 2) {
                    this.player.addCard(this.drawCard());
                    this.doubleCommitment();
                    this.otherPlayer();
                }
                if (playerTurn.equals(Turn.Hit)) {
                    if (player.overDrawn() || player.hasBlackJack()) {
                        System.out.println("You already have 21 or above");
                    } else {
                        this.player.addCard(this.drawCard());
                        while (playerTurn.equals(Turn.Hit) && !this.player.overDrawn() && !this.player.hasBlackJack()) {
                            this.printGameState();
                            playerTurn = this.player.makeTurn();
                            this.player.addCard(this.drawCard());
                            if (this.player.getValue() >= 21) {
                                playerTurn = Turn.Stay;
                                this.otherPlayer();
                            }
                        }
                    }
                }
            }

            // dealers turn
            if (this.player.equals(dealer)) {
                while (this.player.makeTurn().equals(Turn.Hit)) {
                    this.player.addCard(this.drawCard());
                }
            }

            // result
            this.printGameState();
            this.evaluateCards();
            System.out.println(this.result);

            if (!this.playAgain()) {
                break;
            }
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