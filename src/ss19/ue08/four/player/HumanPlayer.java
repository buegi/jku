package ss19.ue08.four.player;

import inout.In;
import inout.Out;
import ss19.ue08.four.game.Stone;
import ss19.ue08.four.game.Board;

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