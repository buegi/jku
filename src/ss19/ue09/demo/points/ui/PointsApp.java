package ss19.ue09.demo.points.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Menu;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ss19.ue09.demo.points.model.PointsEvent;
import ss19.ue09.demo.points.model.PointsListener;
import ss19.ue09.demo.points.model.PointsModel;

public class PointsApp {

    private final PointsModel model;
    private final JFrame frame;
    private final JButton clearBtn;

    public static void main(String[] args) {
        new PointsApp().open();
    }

    private PointsApp() {
        this.model = new PointsModel();
        this.frame = new JFrame("Points");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(new PointsPanel(model), BorderLayout.CENTER);
        JPanel btnPnl = new JPanel();
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        cp.add(btnPnl, BorderLayout.NORTH);

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(a -> {
            model.clear();
        });

        btnPnl.add(clearBtn);

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        JMenuItem exitMI = new JMenuItem("Exit");
        fileMenu.add(exitMI);
        exitMI.addActionListener(a -> {
            frame.dispose();
        });
        JMenu editMenu = new JMenu("Edit");
        menubar.add(editMenu);
        JMenuItem clearMI = new JMenuItem("Clear");
        editMenu.add(clearMI);
        clearMI.addActionListener(a -> {
            model.clear();
        });

        JLabel actionLbl = new JLabel();
        cp.add(actionLbl, BorderLayout.SOUTH);

        model.addPointsListener(new PointsListener() {

            @Override
            public void pointsCleared(PointsEvent e) {
                actionLbl.setText("Points cleared!");
            }

            @Override
            public void pointAdded(PointsEvent e) {
                actionLbl.setText("Point added: " + e.getPoints()[0].toString());
            }

        });

    }

    private void open() {
        frame.pack();
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}