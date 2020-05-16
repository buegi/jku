package swe2.ss20.ue09.blackjack.player;

import swe2.ss20.ue09.blackjack.game.Turn;

public class HumanPlayer extends Player {

    // TODO implement this class
    private static final int START_CHIPS = 10;
    private int chips;

    public HumanPlayer() {
        super();
        this.chips = START_CHIPS;
    }

    public int getChips() {
        return this.chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    @Override
    public Turn makeTurn() {
        return Turn.Stay;
    }
}