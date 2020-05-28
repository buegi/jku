package swe2.ss20.ue10.demo.circledraw.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import at.jku.ssw.circledraw.model.Circle;
import at.jku.ssw.circledraw.model.CircleEvent;
import at.jku.ssw.circledraw.model.CircleModel;

public class CircleDrawMain {
    private static final CircleModel model = new CircleModel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CircleDrawMain::initialize);
    }

    public static void initialize() {
        JFrame frame = new JFrame("CircleDraw");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLayout(new BorderLayout());

        frame.add(new CircleView(model), BorderLayout.CENTER);

        JLabel label = new JLabel("");
        model.addCircleModelListener(e -> {
            if (e.getKind() == CircleEvent.Kind.ADDED) {
                label.setText("Circle added " + e.getCircle());
            } else {
                label.setText("Circle deleted " + e.getCircle());
            }
        });
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 28));
        frame.add(label, BorderLayout.SOUTH);

        JPanel btnPnl = new JPanel();
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(a -> {
            model.clear();
        });
        btnPnl.add(clearBtn);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem exitMI = new JMenuItem("Exit");
        exitMI.addActionListener(a -> {
            frame.setVisible(false);
            frame.dispose();
        });
        fileMenu.add(exitMI);
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        JMenuItem clearMI = new JMenuItem("Clear");
        clearMI.addActionListener(a -> model.clear());
        editMenu.add(clearMI);

        frame.setVisible(true);
        frame.add(btnPnl, BorderLayout.NORTH);
        model.addCircle(new Circle(300, 200, 100));
    }
}
