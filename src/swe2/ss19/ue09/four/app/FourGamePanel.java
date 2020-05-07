package swe2.ss19.ue09.four.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import swe2.ss19.ue09.four.game.*;

public class FourGamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Game game;
    private final int width = 600;
    private final int height = 520;

    public FourGamePanel(Game game) {
        super();
        this.game = game;
        this.setLayout(new GridLayout(Game.COLS, Game.ROWS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(width, height));
        this.addMouseListener(new ClickedListener());
    }

    private class ClickedListener extends MouseAdapter { // show implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int col = (int) (e.getX() / ((float) width / (float) Game.COLS));
            if (game.getGameState() == GameState.Draw || game.getGameState() == GameState.BLUEWon
                    || game.getGameState() == GameState.REDWon) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                game.init();
            }
            game.setStone(col, game.getCurrent());
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int padding = 10;
        int width = getWidth() - 1;
        int height = getHeight() - 1;

        int xOff = (getWidth() - width) / 2;
        int yOff = (getHeight() - height) / 2;
        Graphics2D g2d = (Graphics2D) g.create();
        int diameter = 83;

        for (int row = 0; row < Game.ROWS; row++) {
            int yPos = (yOff + padding) + (diameter * row);
            for (int col = 0; col < Game.COLS; col++) {
                int xPos = (xOff + padding) + (diameter * col);
                Stone actual = game.getStone(row, col);
                switch (actual) {
                    case RED:
                        g2d.setColor(Color.RED);
                        break;
                    case BLUE:
                        g2d.setColor(Color.BLUE);
                        break;
                    case None:
                        g2d.setColor(Color.BLACK);
                        break;
                }
                g2d.fill(new Ellipse2D.Double(xPos, yPos, diameter, diameter));
            }
        }
    }
}