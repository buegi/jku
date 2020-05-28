package swe2.ss20.ue10.blackjack.player;

import swe2.ss20.ue10.blackjack.game.Turn;
import swe2.ss20.ue10.blackjack.player.Player;

public class Dealer extends Player {

    @Override
    public Turn makeTurn() {
        if (getValue() <= 17) {
            return Turn.Hit;
        }
        return Turn.Stay;
    }
}