package swe2.ss20.ue10.blackjack;

import swe2.ss20.ue10.blackjack.game.Blackjack;
import swe2.ss20.ue10.blackjack.ui.BlackJackJView;

import javax.swing.*;

public class GameMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMain::initialize);
    }

    private static void initialize() {
        JComponent blackJackFrame = new BlackJackJView(new Blackjack());
        blackJackFrame.setVisible(true);
    }
}