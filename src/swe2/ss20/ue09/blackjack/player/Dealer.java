package swe2.ss20.ue09.blackjack.player;

import swe2.ss20.ue09.blackjack.game.Turn;

public class Dealer extends Player {

    public Dealer() {
        super();
    }

    @Override
    public Turn makeTurn() {
        return Turn.Hit;
    }
}