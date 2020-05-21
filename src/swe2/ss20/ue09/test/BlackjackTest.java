package swe2.ss20.ue09.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import swe2.ss20.ue09.blackjack.game.Blackjack;
import swe2.ss20.ue09.blackjack.game.Card;
import swe2.ss20.ue09.blackjack.game.GameResult;
import swe2.ss20.ue09.blackjack.game.OutOfCardsException;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {

    private Blackjack blackJack;

    @BeforeEach
    void setUp() {
        this.blackJack = new Blackjack();
    }

    @Test
    void testDrawAllCards() {
        for (int i = 0; i < 52; i++) {
            blackJack.drawCard();
        }
        assertThrows(OutOfCardsException.class, () -> blackJack.drawCard());
    }

    @Test
    void testDraw() {
        this.blackJack.getDealer().getCards().clear();
        this.blackJack.getHumanPlayer().getCards().clear();
        blackJack.getDealer().getCards().add(new Card(0));
        blackJack.getHumanPlayer().getCards().add(new Card(0));
        assertEquals(this.blackJack.evaluateCards(), GameResult.Draw);
    }

    @Test
    void PlayerWin() {
        this.blackJack.getDealer().getCards().clear();
        this.blackJack.getHumanPlayer().getCards().clear();
        blackJack.getDealer().getCards().add(new Card(6));
        blackJack.getHumanPlayer().getCards().add(new Card(8));
        assertEquals(this.blackJack.evaluateCards(), GameResult.PlayerWins);
    }

    @Test
    void DealerWin() {
        this.blackJack.getDealer().getCards().clear();
        this.blackJack.getHumanPlayer().getCards().clear();
        blackJack.getDealer().getCards().add(new Card(9));
        blackJack.getHumanPlayer().getCards().add(new Card(4));
        assertEquals(this.blackJack.evaluateCards(), GameResult.DealerWins);
    }

    @Test
    void testEvaluationWithInitialConfiguration() {
        if (this.blackJack.getHumanPlayer().getValue() > this.blackJack.getDealer().getValue()) {
            assertEquals(this.blackJack.evaluateCards(), GameResult.PlayerWins);
        } else if (this.blackJack.getHumanPlayer().getValue() < this.blackJack.getDealer().getValue()) {
            assertEquals(this.blackJack.evaluateCards(), GameResult.DealerWins);
        } else {
            assertEquals(this.blackJack.evaluateCards(), GameResult.Draw);
        }
    }
}