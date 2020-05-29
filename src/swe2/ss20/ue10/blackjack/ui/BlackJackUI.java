package swe2.ss20.ue10.blackjack.ui;

import swe2.ss20.ue10.blackjack.game.Blackjack;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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
        this.initMainFrame();
        this.initMainFrameMenu();
        this.initDealerPane();
        this.initPlayerPane();
        this.initButtonPane();
        this.initMessagePane();
        mainFrame.setVisible(true);
    }

    private void initMainFrame() {
        this.mainFrame = new JFrame();
        this.mainFrame.setTitle("Black Jack");
        this.mainFrame.setBounds(0, 0, 800, 800);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setResizable(false);
    }

    private void initMainFrameMenu() {
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        JMenuItem menuItemReset = new JMenuItem("Reset");
        menuFile.add(menuItemReset);
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(ae -> this.mainFrame.dispose());
        menuFile.add(menuItemExit);
    }

    private void initDealerPane() {
        JPanel dealerPane = new JPanel();
        dealerPane.setSize(800, 200);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        dealerPane.setBorder(blackLine);
        JLabel dealerLabel = new JLabel("DealerPane ( points )");
        dealerPane.add(dealerLabel);
        this.mainFrame.add(dealerPane, BorderLayout.WEST);
    }

    private void initPlayerPane() {
        JPanel playerPane = new JPanel();
        playerPane.setSize(800, 200);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        playerPane.setBorder(blackLine);
        JLabel playerLabel = new JLabel("PlayerPane ( points )");
        playerPane.add(playerLabel);
        this.mainFrame.add(playerPane, BorderLayout.EAST);
    }

    private void initButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setSize(800, 200);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        buttonPane.setBorder(blackLine);
        JButton buttonHit = new JButton("Hit");
        JButton buttonDoubleDown = new JButton("Double down");
        JButton buttonStay = new JButton("Stay");
        buttonPane.add(buttonHit);
        buttonPane.add(buttonDoubleDown);
        buttonPane.add(buttonStay);
        this.mainFrame.add(buttonPane, BorderLayout.NORTH);
    }

    private void initMessagePane() {
        JPanel messagePane = new JPanel();
        messagePane.setSize(800, 200);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        messagePane.setBorder(blackLine);
        JLabel messageLabel = new JLabel("This is a message, that something's going on!");
        messagePane.add(messageLabel);
        this.mainFrame.add(messagePane, BorderLayout.SOUTH);
    }
}