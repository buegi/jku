package swe2.ss20.ue10.blackjack.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

import swe2.ss20.ue10.blackjack.model.GameEvent;
import swe2.ss20.ue10.blackjack.model.GameListener;
import swe2.ss20.ue10.blackjack.player.Dealer;
import swe2.ss20.ue10.blackjack.player.HumanPlayer;
import swe2.ss20.ue10.blackjack.player.Player;
import swe2.inout.In;
import swe2.inout.Out;

public class Blackjack {

    public static final int TOTAL_NUM_CARDS = 52;

    private HumanPlayer human = new HumanPlayer(10);
    private Dealer dealer = new Dealer();
    private String message;
    private GameResult gameResult;

    private List<GameListener> listeners = new ArrayList<>();

    /**
     * keeps track of cards that are still in the deck (false) or that have been
     * given to a player (true)
     */
    private boolean[] cardAvailable;

    /**
     * number of cards left
     */
    private int cardsLeft = TOTAL_NUM_CARDS;

    private Random rnd = new Random();

    public Blackjack() {
        cardAvailable = new boolean[TOTAL_NUM_CARDS];
        Arrays.fill(cardAvailable, true);
    }

    public Player getHumanPlayer() {
        return human;
    }

    public int getHumanPlayerChips() {
        return human.getChips();
    }

    public Player getDealer() {
        return dealer;
    }

    public String getMessage() {
        return message == null ? "Have fun playing BlackJack!" : this.message;
    }

    public GameResult getGameResult() {
        return this.gameResult;
    }

    /**
     * evaluate which player won this round
     *
     * @return the result of this round
     */
    public GameResult evaluateCards() {
        if (dealer.hasBlackJack() && !human.hasBlackJack()) {
            return GameResult.DealerWins;
        }

        if (human.hasBlackJack() && !dealer.hasBlackJack()) {
            return GameResult.PlayerWins;
        }

        if (dealer.getValue() <= 21 && (dealer.getValue() > human.getValue() || human.getValue() > 21)) {
            return GameResult.DealerWins;
        }

        if (human.getValue() <= 21 && (human.getValue() > dealer.getValue() || dealer.getValue() > 21)) {
            return GameResult.PlayerWins;
        }

        return GameResult.Draw;
    }

    /**
     * reset the deck - all cards can be drawn again after this method is called
     */
    public void resetCards() {
        Arrays.fill(cardAvailable, true);
        cardsLeft = TOTAL_NUM_CARDS;
        fireGameChangedEvent();
    }

    /**
     * draw a random card that is still in the deck
     *
     * @return the selected card
     */
    public Card drawCard() {
        if (cardsLeft == 0) {
            throw new OutOfCardsException();
        }

        // draw new values until you find an available card
        // TODO: we know this may perform poorly if only a few cards are left, could be optimized in the future
        int indexCard = rnd.nextInt(TOTAL_NUM_CARDS);
        while (!cardAvailable[indexCard]) {
            indexCard = rnd.nextInt(TOTAL_NUM_CARDS);
        }
        cardAvailable[indexCard] = false;

        cardsLeft--;
        return new Card(indexCard);
    }

    public void setUp() {
        resetCards();
        this.gameResult = null;
        this.message = null;
        dealer.resetCards(drawCard());
        human.resetCards(drawCard(), drawCard());
        printGameState();
        fireGameChangedEvent();
    }

    public void startOverWithFullReset() {
        resetCards();
        this.gameResult = null;
        this.message = null;
        human.resetChips();
        dealer.resetCards(drawCard());
        human.resetCards(drawCard(), drawCard());
        printGameState();
        fireGameChangedEvent();
        System.out.println("Start over with full reset");
    }


    /**
     * play the game on command line
     */
    public void play() {

        do {
            // set up
            setUp();

            // human turn
            boolean doubleDown = false;
            Turn t = human.makeTurn();
            if (t == Turn.DoubleDown) {
                // player has to stay after double down
                Out.println("Player turn: " + t + "\n");
                human.addCard(drawCard());
                printGameState();
                doubleDown = true;
            } else {
                while (t != Turn.Stay) {
                    Out.println("Player turn: " + t + "\n");
                    human.addCard(drawCard());
                    printGameState();
                    t = human.makeTurn();
                }
            }

            // dealer turn
            if (human.getValue() <= 21) {
                while (dealer.makeTurn() != Turn.Stay) {
                    dealer.addCard(drawCard());
                    printGameState();
                }
            } else {
                Out.println("Player value above 21.");
            }

            // print winner
            GameResult result = evaluateCards();
            if (result == GameResult.PlayerWins) {
                Out.println("Player wins!");
                human.updateChips((human.hasBlackJack() || doubleDown) ? 2 : 1);
            } else if (result == GameResult.DealerWins) {
                Out.println("Dealer wins!");
                human.updateChips(doubleDown ? -2 : -1);
            } else {
                Out.println("Draw!");
            }

            // update chips
            if (human.getChips() > 0) {
                Out.print("You have " + human.getChips() + " chips. ");
            } else {
                Out.println("You have no chips left.");
            }

        } while (human.getChips() > 0 && keepPlaying());

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

    private static boolean keepPlaying() {

        Out.println("Do you want to continue playing? (y/n)");

        // accept upper and lower case
        char answer = Character.toLowerCase(In.readChar());

        while (answer != 'y' && answer != 'n') {
            Out.println("Invalid Answer. Do you want to continue playing? (y/n)");

            // accept upper and lower case
            answer = Character.toLowerCase(In.readChar());
        }
        return answer == 'y';
    }


    /**
     * play the game with UI
     */

    public void playUI() {
        setUp();
    }


    public void buttonHitPressed() {
        if (human.getValue() <= 21 && this.gameResult == null) {
            human.addCard(drawCard());
        }
        if (getHumanPlayer().getValue() >= 21 && gameResult == null) {
            while (dealer.makeTurn() != Turn.Stay) {
                dealer.addCard(drawCard());
            }
            gameResult = evaluateCards();
            message = gameResult.toString() + " - Click anywhere in Window to start new round!";
        }
        fireGameChangedEvent();
    }

    public void buttonDoubleDownPressed() {
        if (human.getCards().size() == 2 && this.gameResult == null) {
            human.addCard(drawCard());
            if (human.getValue() <= 21) {
                while (dealer.makeTurn() != Turn.Stay) {
                    dealer.addCard(drawCard());
                }
            } else {
                message = "Player value above 21.";
            }
            gameResult = evaluateCards();
            message = gameResult.toString() + " - Click anywhere in Window to start new round!";
        } else {
            message = "DoubleDown only possible after start of round! Please make another turn!";
        }
        fireGameChangedEvent();
    }

    public void buttonStayPressed() {
        if (human.getValue() <= 21 && gameResult == null) {
            while (dealer.makeTurn() != Turn.Stay) {
                dealer.addCard(drawCard());
            }
            gameResult = evaluateCards();
            message = gameResult.toString() + " - Click anywhere in Window to start new round!";
        }
        fireGameChangedEvent();
    }

    public void nextRound() {
        if (human.getChips() > 0) {
            this.gameResult = null;
            this.setUp();
            this.message = "New Round started";
        } else {
            this.message = "You have no more chips available! Please use File/Reset to fully start over!";
        }
    }

    public void addGameListener(GameListener gameListener) {
        listeners.add(gameListener);
    }

    public void removeGameListener(GameListener gameListener) {
        listeners.remove(gameListener);
    }

    private void fireGameChangedEvent() {
        GameEvent ge = new GameEvent(this);
        for (GameListener listener : listeners) {
            listener.gameEvent(ge);
        }
    }
}