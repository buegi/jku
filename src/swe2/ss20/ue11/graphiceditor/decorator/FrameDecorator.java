package swe2.ss20.ue11.graphiceditor.decorator;

import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;
import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;

import java.awt.*;

public class FrameDecorator extends AbstractDecorator {

    private final int BORDER_THICKNESS = 3;

    public FrameDecorator(GraphicObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(BORDER_THICKNESS));
        g2d.setColor(Color.BLACK);
        g2d.drawRect((go.getX() + BORDER_THICKNESS) - (go.getWidth() + BORDER_THICKNESS * 2) / 2, (go.getY() + BORDER_THICKNESS) - (go.getHeight() + BORDER_THICKNESS * 2) / 2, go.getWidth(), go.getHeight());
    }

    @Override
    public void accept(GraphicObjectVisitor gov) {
        go.accept(gov);
    }
}