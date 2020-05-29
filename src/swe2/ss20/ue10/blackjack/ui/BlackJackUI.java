package swe2.ss20.ue10.blackjack.ui;

import swe2.ss20.ue10.blackjack.game.Blackjack;

import javax.swing.*;

public class BlackJackUI extends JComponent {

    private final Blackjack blackjack;
    private JFrame mainFrame;

    public BlackJackUI() {
        this.blackjack = new Blackjack();
        this.init();
    }

    public BlackJackUI(Blackjack blackjack) {
        this.blackjack = blackjack;
        this.init();
    }

    private void init() {
        // initialize MainFrame
        this.initMainFrame();
        this.initMainFrameMenu();
        mainFrame.setVisible(true);
    }

    private void initMainFrame() {
        this.mainFrame = new JFrame();
        mainFrame.setTitle("Black Jack");
        mainFrame.setBounds(0, 0, 800, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initMainFrameMenu() {
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        JMenuItem menuItemReset = new JMenuItem("Reset");
        menuFile.add(menuItemReset);

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(ae -> mainFrame.dispose());
        menuFile.add(menuItemExit);
    }

}