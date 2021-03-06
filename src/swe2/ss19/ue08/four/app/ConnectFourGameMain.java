package swe2.ss19.ue08.four.app;

import swe2.ss19.ue08.four.game.Game;
import swe2.ss19.ue08.four.game.Stone;
import swe2.ss19.ue08.four.player.HumanPlayer;

public class ConnectFourGameMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game(new HumanPlayer("X", Stone.X), new HumanPlayer("O", Stone.O));
        game.play();
    }
}