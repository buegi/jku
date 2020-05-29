package swe2.ss20.ue10.blackjack;

import swe2.ss20.ue10.blackjack.game.Blackjack;
import swe2.ss20.ue10.blackjack.ui.BlackJackUI;

public class GameMain {
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        BlackJackUI view = new BlackJackUI(game);
        game.play();
    }
}