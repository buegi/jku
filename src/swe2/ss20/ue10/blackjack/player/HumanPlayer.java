package swe2.ss20.ue10.blackjack.player;

import swe2.ss20.ue10.blackjack.game.Turn;
import swe2.inout.In;
import swe2.inout.Out;

public class HumanPlayer extends Player {

    private int numChips;

    public HumanPlayer(int numChips) {
        this.numChips = numChips;
    }

    @Override
    public Turn makeTurn() {
        if (getValue() >= 21) {
            return Turn.Stay;
        }

        Out.println("What do you want to do?");
        printAvailableTurns();

        // accept upper and lower case
        char turn = Character.toLowerCase(In.readChar());

        while (invalidAnswer(turn)) {
            Out.println("Invalid Answer. What do you want to do?");
            printAvailableTurns();

            // accept upper and lower case
            turn = Character.toLowerCase(In.readChar());
        }

        if (turn == 'h') {
            return Turn.Hit;
        }
        if (turn == 'd') {
            return Turn.DoubleDown;
        }
        return Turn.Stay;
    }

    private boolean invalidAnswer(char turn) {
        return turn != 'h' && turn != 's' && (this.getCards().size() != 2 || turn != 'd');
    }

    private void printAvailableTurns() {
        Out.println("Stay (s)");
        Out.println("Hit (h)");
        if (this.getCards().size() == 2) {
            Out.println("Double down (d)");
        }
    }

    public void resetChips() {
        this.numChips = 10;
    }

    public void updateChips(int update) {
        this.numChips += update;
    }

    public int getChips() {
        return this.numChips;
    }
}