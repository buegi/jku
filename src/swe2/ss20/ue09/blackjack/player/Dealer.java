package swe2.ss20.ue09.blackjack.player;

import swe2.ss20.ue09.blackjack.game.Turn;

public class Dealer extends Player {

    // TODO implement this class

    public Dealer() {
        super();
    }

    @Override
    public Turn makeTurn() {
        return Turn.Stay;
    }
}