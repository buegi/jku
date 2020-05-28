package swe2.ss20.ue09.blackjack.player;

import java.util.ArrayList;
import java.util.List;

import swe2.ss20.ue09.blackjack.game.Card;
import swe2.ss20.ue09.blackjack.game.Turn;

public abstract class Player {

    private ArrayList<Card> cards;
    private int aceCount;
    private static int BLACK_JACK = 21;

    public Player() {
        this.cards = new ArrayList<Card>();
        this.aceCount = 0;
    }

    public void clear() {
        this.cards = new ArrayList<Card>();
        this.aceCount = 0;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void addCard(Card card) {
        if (card.getIndex() == 1) {
            this.aceCount++;
        }
        this.cards.add(card);
    }

    public int getValue() {
        int value = 0;
        for (Card card : this.cards) {
            if (card.getIndex() == 1) {
                value += 11;
            } else if (card.getIndex() >= 11) {
                value += 10;
            } else {
                value += card.getIndex();
            }
        }
        int tempAceCount = this.aceCount;
        while (value > BLACK_JACK && tempAceCount > 0) {
            value -= 10;
            tempAceCount--;
        }
        return value;
    }

    public boolean hasBlackJack() {
        return this.getValue() == BLACK_JACK;
    }

    public boolean overDrawn() {
        return this.getValue() > BLACK_JACK;
    }

    public abstract Turn makeTurn();
}