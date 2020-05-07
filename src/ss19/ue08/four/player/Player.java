package ss19.ue08.four.player;

import ss19.ue08.four.game.Board;
import ss19.ue08.four.game.Stone;

public abstract class Player {

    public final String name;
    public final Stone stone;

    public Player(String name, Stone stone) {
        super();
        this.name = name;
        this.stone = stone;
    }

    public String getName() {
        return this.name;
    }

    abstract public int getMove(Board board);
}