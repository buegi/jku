package swe2.ss20.ue10.demo.circledraw.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import swe2.ss20.ue10.demo.circledraw.model.Circle;
import swe2.ss20.ue10.demo.circledraw.model.CircleModel;
import swe2.ss20.ue10.demo.circledraw.model.CircleModelListener;

@SuppressWarnings("serial")
public class CircleView extends JComponent {
    private final CircleModel model;

    public CircleView(CircleModel model) {
        this.model = model;
        model.addCircleModelListener(modelListener);
        addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        for (Circle circle : model) {
            int diameter = circle.radius * 2;
            g2d.fillOval(circle.x - circle.radius, circle.y -
                    circle.radius, diameter, diameter);
        }
    }

    private final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isControlDown()) {
                Circle[] cs = model.getCircles();
                for (Circle circle : cs) {
                    if (circle.contains(e.getX(), e.getY())) {
                        model.removeCircle(circle);
                    }
                }
            } else {
                model.addCircle(new Circle(e.getX(), e.getY(), 50));
            }
        }
    };

    private final CircleModelListener modelListener =
            event -> {
                repaint();
            };
}