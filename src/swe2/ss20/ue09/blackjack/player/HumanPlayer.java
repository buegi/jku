package swe2.ss20.ue09.blackjack.player;

import swe2.inout.In;
import swe2.ss20.ue09.blackjack.game.Turn;

public class HumanPlayer extends Player {

    private static final int INITIAL_CHIP_COUNT = 10;
    private int nChips;

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

    @Override
    public Turn makeTurn() {
        System.out.println("What do you want to do: Stay(s), Hit(h), DoubleDown(d)?");
        char playerAction = In.readChar();
        while (playerAction != 's' && playerAction != 'h' && playerAction != 'd') {
            System.out.println("Invalid input!");
            playerAction = In.readChar();
        }
        if (playerAction == 's') {
            System.out.println("Player turn: " + Turn.Stay);
            return Turn.Stay;
        } else if (playerAction == 'h') {
            System.out.println("Player turn: " + Turn.Hit);
            return Turn.Hit;
        } else {
            System.out.println("Player turn: " + Turn.DoubleDown);
            return Turn.DoubleDown;
        }
    }
}