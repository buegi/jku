package swe2.ss20.ue09.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import swe2.ss20.ue09.blackjack.game.Card;
import swe2.ss20.ue09.blackjack.game.Turn;
import swe2.ss20.ue09.blackjack.player.Dealer;
import swe2.ss20.ue09.blackjack.player.HumanPlayer;
import swe2.ss20.ue09.blackjack.player.Player;


public class PlayerTest {
    private Player dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
        this.player = new HumanPlayer();
    }

    @Test
        // draw Cards until 17 is reached, results in value 18
    void testDealerMakeTurn() {
        while (this.dealer.makeTurn().equals(Turn.Hit)) {
            this.dealer.getCards().add(new Card(2));
        }
        assertEquals(18, this.dealer.getValue());
    }

    @Test
        // check blackjack recognition of dealer
    void testDealerHasBlackJack() {
        this.dealer.getCards().add(new Card(0));
        this.dealer.getCards().add(new Card(10));
        assertEquals(2, this.dealer.getCards().size());
        assertEquals(21, this.dealer.getValue());
        assertEquals(true, this.dealer.hasBlackJack());
    }

    @Test
        // check blackjack recognition of player
    void testPlayerHasBlackJack() {
        this.player.getCards().add(new Card(0));
        this.player.getCards().add(new Card(10));
        assertEquals(2, this.player.getCards().size());
        assertEquals(21, this.player.getValue());
        assertEquals(true, this.player.hasBlackJack());
    }

    @Test
        // check if player calculation of aces is lowered on overdraw
    void testPlayerOverdrawsWithAces() {
        this.player.getCards().add(new Card(0));
        this.player.getCards().add(new Card(0));
        assertEquals(2, this.player.getCards().size());
        assertEquals(2, this.player.getValue());
        assertEquals(false, this.player.hasBlackJack());
    }

//   @Test
    // check if two aces and 9 give blackjack
//    void testTwoAcesAndNine() {
//        this.player.getCards().add(new Card(0));
//        this.player.getCards().add(new Card(0));
//        this.player.getCards().add(new Card(8));
//        assertEquals(11, this.player.getValue());
//        assertEquals(true, this.player.hasBlackJack());
//    }
}