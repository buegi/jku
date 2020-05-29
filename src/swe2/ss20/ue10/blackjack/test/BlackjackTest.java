package swe2.ss20.ue10.blackjack.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swe2.ss20.ue10.blackjack.game.Blackjack;
import swe2.ss20.ue10.blackjack.game.Card;
import swe2.ss20.ue10.blackjack.game.Card.Color;
import swe2.ss20.ue10.blackjack.game.GameResult;
import swe2.ss20.ue10.blackjack.game.OutOfCardsException;

public class BlackjackTest {

    private Blackjack blackjack;

    @BeforeEach
    void setUp() {
        blackjack = new Blackjack();
    }

    @Test
    void testEvaluateCardsPlayerWin() {
        blackjack.getHumanPlayer().resetCards(new Card(Color.Clubs, 13), new Card(Color.Diamonds, 9));
        blackjack.getDealer().resetCards(new Card(Color.Spades, 9), new Card(Color.Clubs, 8));
        assertEquals(GameResult.PlayerWins, blackjack.evaluateCards());
    }

    @Test
    void testEvaluateCardsDealerWin() {
        blackjack.getHumanPlayer().resetCards(new Card(Color.Clubs, 5), new Card(Color.Diamonds, 9),
                new Card(Color.Diamonds, 2));
        blackjack.getDealer().resetCards(new Card(Color.Spades, 9), new Card(Color.Clubs, 8));
        assertEquals(GameResult.DealerWins, blackjack.evaluateCards());
    }

    @Test
    void testEvaluateCardsBlackjack() {
        blackjack.getHumanPlayer().resetCards(new Card(Color.Clubs, 10), new Card(Color.Diamonds, 1));
        blackjack.getDealer().resetCards(new Card(Color.Spades, 9), new Card(Color.Clubs, 8), new Card(Color.Clubs, 4));
        assertEquals(GameResult.PlayerWins, blackjack.evaluateCards());
    }

    @Test
    void testEvaluateCardsDraw() {
        blackjack.getHumanPlayer().resetCards(new Card(Color.Clubs, 10), new Card(Color.Spades, 10),
                new Card(Color.Diamonds, 1));
        blackjack.getDealer().resetCards(new Card(Color.Spades, 9), new Card(Color.Clubs, 8), new Card(Color.Clubs, 4));
        assertEquals(GameResult.Draw, blackjack.evaluateCards());
    }

    @Test
    void testGetCardNumberOfCards() {
        for (int i = 0; i < Blackjack.TOTAL_NUM_CARDS; i++) {
            blackjack.drawCard();
        }
        try {
            blackjack.drawCard();
            assertTrue(false);
        } catch (OutOfCardsException e) {
            // expected Exception
        }
    }

    @Test
    void testGetCardDuplicates() {
        HashSet<Card> cards = new HashSet<>(Blackjack.TOTAL_NUM_CARDS);
        for (int i = 0; i < Blackjack.TOTAL_NUM_CARDS; i++) {
            cards.add(blackjack.drawCard());
        }
        assertEquals(cards.size(), Blackjack.TOTAL_NUM_CARDS);
    }

    /**
     * test that after a reset all cards are used again
     */
    @Test
    void testGetCardReset() {
        // pick 5 cards
        HashSet<Card> cards = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            cards.add(blackjack.drawCard());
        }
        blackjack.resetCards();

        // check that the five cards appear again after a reset
        for (int i = 0; i < Blackjack.TOTAL_NUM_CARDS; i++) {
            Card c = blackjack.drawCard();
            if (cards.contains(c)) {
                cards.remove(c);
            }
        }
        assertEquals(cards.size(), 0);
    }
}