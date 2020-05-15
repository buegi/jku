package swe2.ss20.ue09.blackjack.player;

import java.util.List;

import swe2.ss20.ue09.blackjack.game.Card;
import swe2.ss20.ue09.blackjack.game.Turn;

public abstract class Player {

    // TODO implement and extend this class

    public List<Card> getCards() {

        // TODO implement this

        return null;
    }

    public int getValue() {

        // TODO implement

        return 0;
    }

    public boolean hasBlackJack() {

        // TODO implement

        return false;
    }

    public abstract Turn makeTurn();
}