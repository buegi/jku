package ss19.ue09.four.app;

import ss19.ue09.four.game.*;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class FourInfoPanel extends JPanel {

    private final Game game;
    private final JLabel gameState;

    public FourInfoPanel(Game game) {
        super();
        this.game = game;
        TitledBorder messageBorder = new TitledBorder("Messages");
        this.setBorder(messageBorder);
        this.setPreferredSize(new Dimension(600, 100));

        gameState = new JLabel();
        this.add(gameState);

        this.game.addGameStateListener(new GameStateListener() {

            @Override
            public void gameStateChanged(GameStateEvent e) {
                FourInfoPanel.this.gameState.setText(e.getGameState().toString());
            }
        });
    }
}