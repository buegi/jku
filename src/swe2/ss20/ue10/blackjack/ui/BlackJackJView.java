package swe2.ss20.ue10.blackjack.ui;

import swe2.ss20.ue10.blackjack.game.Blackjack;
import swe2.ss20.ue10.blackjack.game.Card;
import swe2.ss20.ue10.blackjack.model.GameEvent;
import swe2.ss20.ue10.blackjack.model.GameListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlackJackJView extends JComponent {

    private final Blackjack blackjack;
    private JFrame blackJackFrame;

    public BlackJackJView(Blackjack blackJack) {
        super();
        this.blackjack = blackJack;
        init();
    }

    private void init() {
        blackjack.playUI();
        this.initBlackJackFrame();
        this.initBlackJackFrameMenu();
        this.initButtonPane();
        this.initMessagePane();
        this.initGameInformation();
        this.initDealerCards();
        this.initPlayerCards();
        blackJackFrame.setVisible(true);
    }

    private void initBlackJackFrame() {
        this.blackJackFrame = new JFrame();
        this.blackJackFrame.setTitle("Black Jack");
        this.blackJackFrame.setBounds(0, 0, 800, 800);
        this.blackJackFrame.setLocationRelativeTo(null);
        this.blackJackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.blackJackFrame.setResizable(false);
        this.blackJackFrame.getContentPane().setLayout(null);
        this.blackJackFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                blackjack.nextRound();
            }
        });
    }

    private void initBlackJackFrameMenu() {
        JMenuBar menuBar = new JMenuBar();
        blackJackFrame.setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        JMenuItem menuItemReset = new JMenuItem("Reset");
        menuItemReset.addActionListener(action -> blackjack.startOverWithFullReset());
        menuFile.add(menuItemReset);
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(ae -> this.blackJackFrame.dispose());
        menuFile.add(menuItemExit);
    }

    private void initGameInformation() {
        JLabel dealerInfo = new JLabel("Dealer" + "(" + blackjack.getDealer().getValue() + ")");
        dealerInfo.setFont(dealerInfo.getFont().deriveFont(20.0f));
        dealerInfo.setBounds(75, 0, 500, 50);
        blackjack.addGameListener(event -> dealerInfo.setText("Dealer " + "(" + blackjack.getDealer().getValue() + ")"));

        JLabel playerInfo = new JLabel("Player" + "(" + blackjack.getHumanPlayer().getValue() + ")");
        playerInfo.setFont(playerInfo.getFont().deriveFont(20.0f));
        playerInfo.setBounds(75, 300, 500, 50);
        blackjack.addGameListener(event -> playerInfo.setText("Player " + "(" + blackjack.getHumanPlayer().getValue() + ")"));

        JLabel chipInfo = new JLabel("Chips " + "(" + this.blackjack.getHumanPlayerChips() + ")");
        chipInfo.setFont(chipInfo.getFont().deriveFont(20.0f));
        chipInfo.setBounds(75, 600, 500, 50);
        blackjack.addGameListener(event -> chipInfo.setText("Chips " + "(" + this.blackjack.getHumanPlayerChips() + ")"));
        this.blackJackFrame.add(dealerInfo);
        this.blackJackFrame.add(playerInfo);
        this.blackJackFrame.add(chipInfo);
    }

    private void initDealerCards() {
        JPanel dealerCardPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                blackjack.addGameListener(new GameListener() {
                    @Override
                    public void gameEvent(GameEvent event) {
                        repaint();
                    }
                });
                int offset = 0;
                for (Card card : blackjack.getDealer().getCards()) {
                    offset += 75;
                    g.setColor(Color.WHITE);
                    g.fillRect(offset, 0, 150, 225);
                    if (card.toString().contains("\u2666") || card.toString().contains("\u2665")) {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.drawRect(offset, 0, 150, 225);
                    g.setFont(getFont().deriveFont(20.0f));
                    g.drawString(card.toString(), offset + 5, 25);
                }
            }
        };
        dealerCardPane.setSize(800, 250);
        dealerCardPane.setLocation(0, 50);
        this.blackJackFrame.add(dealerCardPane);
    }

    private void initPlayerCards() {
        JPanel playerCardPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                blackjack.addGameListener(new GameListener() {
                    @Override
                    public void gameEvent(GameEvent event) {
                        repaint();
                    }
                });
                int offset = 0;
                for (Card card : blackjack.getHumanPlayer().getCards()) {
                    offset += 75;
                    g.setColor(Color.WHITE);
                    g.fillRect(offset, 0, 150, 225);
                    if (card.toString().contains("\u2666") || card.toString().contains("\u2665")) {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.drawRect(offset, 0, 150, 225);
                    g.setFont(getFont().deriveFont(20.0f));
                    g.drawString(card.toString(), offset + 5, 25);
                }
            }
        };
        playerCardPane.setSize(800, 250);
        playerCardPane.setLocation(0, 350);
        this.blackJackFrame.add(playerCardPane);
    }

    private void initButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setSize(800, 50);
        buttonPane.setLocation(0, 650);
        JButton buttonHit = new JButton("Hit");
        buttonHit.addActionListener(action -> blackjack.buttonHitPressed());

        JButton buttonDoubleDown = new JButton("Double down");
        buttonDoubleDown.addActionListener(action -> blackjack.buttonDoubleDownPressed());

        JButton buttonStay = new JButton("Stay");
        buttonStay.addActionListener(action -> blackjack.buttonStayPressed());

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
        JLabel messageLabel = new JLabel(blackjack.getMessage());
        blackjack.addGameListener(event -> messageLabel.setText(blackjack.getMessage()));
        messagePane.add(messageLabel);
        this.blackJackFrame.add(messagePane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}