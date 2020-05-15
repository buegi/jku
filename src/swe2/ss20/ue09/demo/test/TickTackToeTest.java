package swe2.ss20.ue09.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swe2.ss20.ue09.demo.tictactoe.Field;
import swe2.ss20.ue09.demo.tictactoe.Player;

public class TickTackToeTest {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
    }

    @Test
    void testFieldStartsEmpty() {
        for (int row = 0; row < Field.SIZE; ++row) {
            for (int column = 0; column < Field.SIZE; ++column) {
                assertEquals(Player.NIL, field.get(row, column));
            }
        }
    }

    @Test
    void testMove() {
        field.set(1, 1, Player.X);
        assertEquals(Player.X, field.get(1, 1));
        field.set(0, 0, Player.O);
        assertEquals(Player.X, field.get(1, 1));
    }

    @Test
    void testInvalidMove() {
        field.set(1, 1, Player.X);
        assertThrows(IllegalStateException.class, () -> field.set(0, 1, Player.X));
    }

    @Test
    void testInvalidMoveOnTopOfOtherPlayer() {
        field.set(1, 1, Player.X);
        assertThrows(IllegalStateException.class, () -> field.set(1, 1, Player.O));
    }

    @Test
    void testInvalidMoveWithNilPlayer() {
        assertThrows(IllegalArgumentException.class, () -> field.set(1, 1, Player.NIL));
    }

    @Test
    public void testInvalidMoveWithNullPlayer() {
        assertThrows(NullPointerException.class, () -> field.set(1, 1, null));
    }

    @Test
    public void testWinnerDetectionRow() {
        assertEquals(Player.NIL, field.getWinner());
        field.set(0, 0, Player.X);
        field.set(1, 0, Player.O);
        field.set(0, 1, Player.X);
        field.set(1, 1, Player.O);
        field.set(0, 2, Player.X);
        assertEquals(Player.X, field.getWinner());
    }

//	@Test
//	public void testWinnerDetectionRow() {
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(0, 0, Player.X);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(1, 0, Player.O);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(0, 1, Player.X);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(1, 1, Player.O);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(0, 2, Player.X);
//		assertTrue(field.isGameOver());
//		assertEquals(Player.X, field.getWinner());
//	}

    @Test
    public void testWinnerDetectionColumn() {
        assertEquals(Player.NIL, field.getWinner());
        field.set(0, 0, Player.X);
        field.set(0, 1, Player.O);
        field.set(2, 2, Player.X);
        field.set(1, 1, Player.O);
        field.set(0, 2, Player.X);
        field.set(2, 1, Player.O);
        assertEquals(Player.O, field.getWinner());
    }

//	@Test
//	public void testWinnerDetectionColumn() {
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(0, 0, Player.X);
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(0, 1, Player.O);
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(2, 2, Player.X);
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(1, 1, Player.O);
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(0, 2, Player.X);
//		assertEquals(Player.NIL, field.getWinner());
//		assertFalse(field.isGameOver());
//		field.set(2, 1, Player.O);
//		assertTrue(field.isGameOver());
//		assertEquals(Player.O, field.getWinner());
//	}

    @Test
    public void testWinnerDetectionDiagonal() {
        assertEquals(Player.NIL, field.getWinner());
        field.set(0, 0, Player.X);
        field.set(1, 0, Player.O);
        field.set(1, 1, Player.X);
        field.set(2, 1, Player.O);
        field.set(2, 2, Player.X);
        assertEquals(Player.X, field.getWinner());
//		assertThrows(IllegalStateException.class, () -> field.set(1, 2, Player.O));
    }

//	@Test
//	public void testWinnerDetectionDiagonal() {
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(0, 0, Player.X);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(1, 0, Player.O);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(1, 1, Player.X);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(2, 1, Player.O);
//		assertFalse(field.isGameOver());
//		assertEquals(Player.NIL, field.getWinner());
//		field.set(2, 2, Player.X);
//		assertTrue(field.isGameOver());
//		assertEquals(Player.X, field.getWinner());
//		assertThrows(IllegalStateException.class, () -> field.set(1, 2, Player.O));
//	}

    @Test
    public void testGetNextPlayer() {
        field.set(0, 0, Player.X);
        assertEquals(Player.O, field.getNextPlayer());
    }

}