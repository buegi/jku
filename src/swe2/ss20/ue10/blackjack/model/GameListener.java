package swe2.ss20.ue10.blackjack.model;

import java.util.EventListener;

public interface GameListener extends EventListener {
    void gameEvent(GameEvent event);
}
