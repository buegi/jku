package swe2.ss20.ue09.blackjack.player;

import swe2.inout.In;
import swe2.ss20.ue09.blackjack.game.Turn;

public class HumanPlayer extends Player {

    private static final int INITIAL_CHIP_COUNT = 10;
    private int nChips;
    private Turn state;

    public HumanPlayer() {
        super();
        this.nChips = INITIAL_CHIP_COUNT;
    }

    public int getChips() {
        return this.nChips;
    }

    public void setChips(int nChips) {
        this.nChips = nChips;
    }

    public Turn getState() {
        return this.state;
    }

    public void setState(Turn state) {
        this.state = state;
    }

    @Override
    public Turn makeTurn() {
        System.out.println("What do you want to do: Stay(s), Hit(h), DoubleDown(d)?");
        char playerAction = In.readChar();
        switch (playerAction) {
            case 's':
                this.setState(Turn.Stay);
                break;
            case 'h':
                this.setState(Turn.Hit);
                break;

            case 'd':
                this.setState(Turn.DoubleDown);
                break;

            default:
                System.out.println("Invalid input!");
                break;
        }
        System.out.println("Player turn: " + this.state);
        return this.state;
    }
}