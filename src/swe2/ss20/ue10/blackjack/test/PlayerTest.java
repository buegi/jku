package swe2.ss20.ue10.blackjack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import blackjack.game.Card;
import blackjack.game.Card.Color;
import blackjack.game.Turn;
import blackjack.player.Player;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player() {
            @Override
            public Turn makeTurn() {
                return Turn.Stay;
            }
        };
    }

    @Test
    public void testGetValueSingleCard() {
        Card c = new Card(Color.Clubs, 2);
        player.resetCards(c);
        assertEquals(player.getValue(), 2);
    }

    @Test
    public void testGetValueTwoCards() {
        Card c1 = new Card(Color.Hearts, 4);
        Card c2 = new Card(Color.Spades, 11);
        player.resetCards(c1, c2);
        assertEquals(player.getValue(), 14);
    }

    @Test
    public void testGetValueThreeCards() {
        Card c1 = new Card(Color.Clubs, 13);
        Card c2 = new Card(Color.Diamonds, 9);
        Card c3 = new Card(Color.Diamonds, 7);
        player.resetCards(c1, c2, c3);
        assertEquals(player.getValue(), 26);
    }

    @Test
    public void testGetValueAceLow() {
        Card c1 = new Card(Color.Clubs, 12);
        Card c2 = new Card(Color.Diamonds, 9);
        Card c3 = new Card(Color.Diamonds, 1);
        player.resetCards(c1, c2, c3);
        assertEquals(player.getValue(), 20);
    }

    @Test
    public void testGetValueAceHigh() {
        Card c1 = new Card(Color.Hearts, 10);
        Card c2 = new Card(Color.Spades, 1);
        player.resetCards(c1, c2);
        assertEquals(player.getValue(), 21);
    }

    @Test
    public void testHasBlackJackTrue() {
        Card c1 = new Card(Color.Hearts, 10);
        Card c2 = new Card(Color.Spades, 1);
        player.resetCards(c1, c2);
        assertTrue(player.hasBlackJack());
    }

    @Test
    public void testHasBlackJackFalse1() {
        Card c1 = new Card(Color.Hearts, 9);
        Card c2 = new Card(Color.Spades, 1);
        player.resetCards(c1, c2);
        assertFalse(player.hasBlackJack());
    }

    @Test
    public void testHasBlackJackFalse2() {
        Card c1 = new Card(Color.Hearts, 10);
        Card c2 = new Card(Color.Clubs, 11);
        Card c3 = new Card(Color.Spades, 1);
        player.resetCards(c1, c2, c3);
        assertFalse(player.hasBlackJack());
    }

}