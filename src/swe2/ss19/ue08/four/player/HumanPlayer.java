package swe2.ss19.ue08.four.player;

import swe2.inout.In;
import swe2.inout.Out;
import swe2.ss19.ue08.four.game.Stone;
import swe2.ss19.ue08.four.game.Board;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Stone stone) {
        super(name, stone);
    }

    @Override
    public int getMove(Board board) {
        int col = readColumn();
        while (!board.isValidMove(col - 1)) {
            Out.println("Invalid move: " + col);
            col = readColumn();
        }
        return col - 1;
    }

    private int readColumn() {
        int col;
        Out.println("Input column for player " + this.name);
        col = In.readInt();
        if (!In.done()) {
            In.readLine();
        }
        Out.println();
        return col;
    }
}