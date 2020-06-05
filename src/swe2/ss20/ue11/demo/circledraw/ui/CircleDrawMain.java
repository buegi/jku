package swe2.ss20.ue11.demo.circledraw.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import swe2.ss20.ue11.demo.circledraw.model.Circle;
import swe2.ss20.ue11.demo.circledraw.model.CircleEvent;
import swe2.ss20.ue11.demo.circledraw.model.CircleModel;

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


        CircleView circleView = new CircleView(model);
        frame.add(circleView, BorderLayout.CENTER);

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
        clearBtn.addActionListener(a -> model.clear());
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

        // UE11 Tools -------------------------------------------------------------

        JToolBar toolBar = new JToolBar();
        frame.getContentPane().add(toolBar, BorderLayout.PAGE_START);
        JToggleButton r10Btn = new JToggleButton("10");
        JToggleButton r20Btn = new JToggleButton("20");
        JToggleButton r30Btn = new JToggleButton("30");

        ButtonGroup btnGroup = new ButtonGroup();

        toolBar.add(r10Btn);
        toolBar.add(r20Btn);
        toolBar.add(r30Btn);
        btnGroup.add(r10Btn);
        btnGroup.add(r20Btn);
        btnGroup.add(r30Btn);

        r10Btn.addActionListener(ae -> circleView.setRadius(10));

        r20Btn.addActionListener(ae -> circleView.setRadius(20));

        r30Btn.addActionListener(ae -> circleView.setRadius(30));

        // ------------------------------------------------------------------------

        frame.setVisible(true);
        frame.add(btnPnl, BorderLayout.NORTH);
    }
}