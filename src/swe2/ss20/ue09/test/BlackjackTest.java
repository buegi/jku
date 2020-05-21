package swe2.ss20.ue09.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import swe2.ss20.ue09.blackjack.game.Blackjack;
import swe2.ss20.ue09.blackjack.game.OutOfCardsException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlackjackTest {

    // TODO add tests here

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
}