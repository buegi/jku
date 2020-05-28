package swe2.ss20.ue10.blackjack.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import swe2.ss20.ue10.blackjack.game.Card;
import swe2.ss20.ue10.blackjack.game.Turn;

public abstract class Player {

    private ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void resetCards(Card... newCards) {
        cards.clear();
        Collections.addAll(cards, newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getValue() {
        int totalValue = 0;
        boolean hasAce = false;
        for (Card c : cards) {
            if (c.getIndex() == 1) {
                hasAce = true;
            }
            totalValue += Math.min(10, c.getIndex());
        }

        if (hasAce && totalValue + 10 <= 21) {
            totalValue += 10;
        }

        return totalValue;
    }

    public boolean hasBlackJack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public abstract Turn makeTurn();
}