package swe2.ss20.ue10.blackjack.ui;

import swe2.ss20.ue10.blackjack.game.Blackjack;
import swe2.ss20.ue10.blackjack.game.Card;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Map;
import java.awt.Graphics;

public class BlackJackUI extends JComponent {

    private final Blackjack blackjack;
    private JFrame blackJackFrame;
    private Map<Card, Rectangle> playerCards;
    private Map<Card, Rectangle> dealerCards;

    public BlackJackUI() {
        this.blackjack = new Blackjack();
        this.init();
    }

    public BlackJackUI(Blackjack blackjack) {
        this.blackjack = blackjack;
        this.init();
    }

    private void init() {
        this.initBlackJackFrame();
        this.initBlackJackFrameMenu();
        this.initButtonPane();
        this.initMessagePane();
        this.initGameInformation();

        blackJackFrame.setVisible(true);
    }

    private void initGameInformation() {
        JLabel dealerInfo = new JLabel("Dealer" + "(" + this.blackjack.getDealer().getValue() + ")");
        dealerInfo.setBounds(50, 000, 100, 50);
        dealerInfo.setFont(dealerInfo.getFont().deriveFont(16.0f));
        dealerInfo.setVisible(true);
        JLabel playerInfo = new JLabel("Player" + "(" + this.blackjack.getHumanPlayer().getValue() + ")");
        playerInfo.setBounds(50, 200, 100, 50);
        playerInfo.setFont(playerInfo.getFont().deriveFont(16.0f));
        playerInfo.setVisible(true);
        JLabel chipInfo = new JLabel("Chips" + "(" + this.blackjack.getHumanPlayer() + ")");
        chipInfo.setFont(chipInfo.getFont().deriveFont(16.0f));
        chipInfo.setBounds(50, 400, 100, 50);
        chipInfo.setVisible(true);

        this.blackJackFrame.add(dealerInfo);
        this.blackJackFrame.add(playerInfo);
        this.blackJackFrame.add(chipInfo);
        repaint();
    }


    private void initBlackJackFrame() {
        this.blackJackFrame = new JFrame();
        this.blackJackFrame.setTitle("Black Jack");
        this.blackJackFrame.setBounds(0, 0, 800, 800);
        this.blackJackFrame.setLocationRelativeTo(null);
        this.blackJackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.blackJackFrame.setResizable(false);
        this.blackJackFrame.getContentPane().setLayout(null);
        repaint();
    }

    private void initBlackJackFrameMenu() {
        JMenuBar menuBar = new JMenuBar();
        blackJackFrame.setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        JMenuItem menuItemReset = new JMenuItem("Reset");
        menuFile.add(menuItemReset);
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(ae -> this.blackJackFrame.dispose());
        menuFile.add(menuItemExit);
        repaint();
    }

    private void initButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setSize(800, 50);
        buttonPane.setLocation(0, 650);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        buttonPane.setBorder(blackLine);
        JButton buttonHit = new JButton("Hit");
        JButton buttonDoubleDown = new JButton("Double down");
        JButton buttonStay = new JButton("Stay");
        buttonPane.add(buttonHit);
        buttonPane.add(buttonDoubleDown);
        buttonPane.add(buttonStay);
        this.blackJackFrame.add(buttonPane);
    }

    private void initMessagePane() {
        JPanel messagePane = new JPanel();
        messagePane.setSize(800, 50);
        messagePane.setLocation(0, 700);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        messagePane.setBorder(blackLine);
        JLabel messageLabel = new JLabel("This is a message, that something's going on!");
        messagePane.add(messageLabel);
        this.blackJackFrame.add(messagePane);
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//    }
}