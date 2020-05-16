package swe2.ss20.ue09.blackjack.player;

import java.util.ArrayList;
import java.util.List;

import swe2.ss20.ue09.blackjack.game.Card;
import swe2.ss20.ue09.blackjack.game.Turn;

public abstract class Player {

    // TODO implement and extend this class
    private ArrayList<Card> cards;

    public Player() {
        this.cards = new ArrayList<Card>();
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getValue() {
        return this.cards.stream().mapToInt(c -> {
            if (c.getIndex() == 1) {
                return 11;
            }
            if (c.getIndex() >= 11) {
                return 10;
            }
            return c.getIndex();
        }).sum();
    }

    public boolean hasBlackJack() {
        return this.getValue() == 21;
    }

    public abstract Turn makeTurn();
}