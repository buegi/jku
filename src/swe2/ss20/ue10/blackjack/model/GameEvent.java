package swe2.ss20.ue10.blackjack.model;

import swe2.ss20.ue10.blackjack.game.Blackjack;

import java.util.EventObject;

public class GameEvent extends EventObject {
    Blackjack source;

    public GameEvent(Blackjack source) {
        super(source);
        this.source = source;
    }
}