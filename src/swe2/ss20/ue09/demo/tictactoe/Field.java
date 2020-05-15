package swe2.ss20.ue09.demo.tictactoe;

import java.util.Arrays;
import java.util.Objects;

import static swe2.ss20.ue09.demo.tictactoe.Player.NIL;

public class Field {

    public static final int SIZE = 3;
    private static final int MAX_ROUNDS = SIZE * SIZE;
    private final Player[][] grid = new Player[SIZE][SIZE];
    private Player lastPlayer = NIL;

    public Field() {
        Arrays.stream(grid).forEach(row -> Arrays.fill(row, NIL));
    }

    public Player get(int row, int column) {
        return grid[row][column];
    }

    public void set(int row, int column, Player player) {
        Objects.requireNonNull(player);
        if (player == NIL) {
            throw new IllegalArgumentException("NIL is not a player.");
        }
        if (lastPlayer == player) {
            throw new IllegalStateException("Players must take turns.");
        }
        if (isGameOver()) {
            throw new IllegalStateException("This game is over.");
        }
        if (grid[row][column] != NIL) {
            throw new IllegalStateException("This field is already taken.");
        }
        lastPlayer = player;
        grid[row][column] = player;
    }

    public boolean isGameOver() {
        return getWinner() != NIL && getRound() < MAX_ROUNDS;
    }

    private int getRound() {
        return (int) Arrays.stream(grid).flatMap(Arrays::stream).filter(player -> player != NIL).count();
    }

    public Player getWinner() {
        for (Player[] row : grid) {
            if (row[0] != NIL && row[0] == row[1] && row[0] == row[2]) {
                return row[0];
            }
        }
        for (int column = 0; column < SIZE; ++column) {
            if (grid[0][column] != NIL && grid[0][column] == grid[1][column] && grid[0][column] == grid[2][column]) {
                return grid[0][column];
            }
        }
        if (grid[1][1] != NIL && ((grid[1][1] == grid[0][0] && grid[1][1] == grid[2][2])
                || (grid[1][1] == grid[2][0] && grid[1][1] == grid[0][2]))) {
            return grid[1][1];
        }
        return NIL;
    }

    public Player getNextPlayer() {
        return lastPlayer == NIL || lastPlayer == Player.O ? Player.X : Player.O;
    }
}