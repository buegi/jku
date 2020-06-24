package swe2.ss20.exam.circle;

import javax.swing.*;
import java.awt.*;

public class CirclePainter extends JComponent {

    private CircleModel model;

    // private final RadiusListener radiusListener = event -> repaint();

    // anonymous
    private RadiusListener radiusListener() {
        return new RadiusListener() {
            @Override
            public void radiusChanged() {
                repaint();
            }
        };
        // or return this::repaint;
        // or return () -> repaint();
    }

    public CirclePainter(CircleModel circleModel) {
        this.model = circleModel;
        model.addRadiusListener(radiusListener());
    }

    public void paint(Graphics g) {
        int radius = model.getRadius();
        g.fillOval(0, 0, radius * 2, radius * 2);
    }
}
