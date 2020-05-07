package swe2.ss19.ue09.four.app;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import swe2.ss19.ue09.four.game.*;

public class FourApp {

    private final Game game;
    private final JFrame mainFrame;
    private final JPanel fourGamePanel;
    private final JPanel fourInfo;

    public static void main(String[] args) {
        new FourApp().open();
    }

    public void open() {
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private FourApp() {
        this.game = new GameImpl();
        this.mainFrame = new JFrame("Connect Four");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // create menu
        JMenuBar menubar = new JMenuBar();
        mainFrame.setJMenuBar(menubar);

        // create menu "File"
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        // add "Reset" under "File" menu
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        fileMenu.add(resetMenuItem);

        // add action for reset menu item
        resetMenuItem.addActionListener(a -> {
            this.game.init();
            mainFrame.repaint();
        });
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);

        // add action for exit menu
        exitMenuItem.addActionListener(a -> {
            mainFrame.dispose();
        });

        // create gamePanel
        this.fourGamePanel = new FourGamePanel(game);
        mainFrame.add(fourGamePanel, BorderLayout.CENTER);

        // create infoPanel
        this.fourInfo = new FourInfoPanel(game);
        mainFrame.add(fourInfo, BorderLayout.SOUTH);
    }
}