package ss19.ue09.demo.points.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import ss19.ue09.demo.points.model.Point;
import ss19.ue09.demo.points.model.PointsEvent;
import ss19.ue09.demo.points.model.PointsListener;
import ss19.ue09.demo.points.model.PointsModel;

@SuppressWarnings("serial")
public class PointsPanel extends JPanel {
    private final PointsModel model;

    public PointsPanel(PointsModel model) {
        this.setPreferredSize(new Dimension(400, 400));
        this.model = model;
        this.addMouseListener(new ClickedListener());
        model.addPointsListener(new PointsListener() {

            @Override
            public void pointsCleared(PointsEvent e) {
                repaint();
            }

            @Override
            public void pointAdded(PointsEvent e) {
                repaint();
            }
        });
    }

    private class ClickedListener extends MouseAdapter { // show implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            model.add(e.getX(), e.getY());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point p : model.getPoints()) {
            g.fillRect(p.x - 2, p.y - 2, 4, 4);
        }
    }
}