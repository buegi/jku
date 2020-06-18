package swe2.ss20.ue11.graphiceditor.decorator;

import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;
import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;
import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;

import java.awt.*;


public class FrameDecorator extends AbstractDecorator {

    public FrameDecorator(GraphicObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);

        g.setColor(Color.BLACK);
        g.drawRect(go.getX() - go.getWidth() / 2, go.getY() - go.getHeight() / 2, go.getWidth(), go.getHeight());

    }

    @Override
    public void accept(GraphicObjectVisitor gov) {
        go.accept(gov);
    }
}