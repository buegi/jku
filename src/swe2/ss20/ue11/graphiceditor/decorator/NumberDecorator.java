package swe2.ss20.ue11.graphiceditor.decorator;

import java.awt.Color;
import java.awt.Graphics;

import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;
import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;

public class NumberDecorator extends AbstractDecorator {

    private final int number;

    public NumberDecorator(GraphicObject go, int number) {
        super(go);
        this.number = number;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
        g.setColor(new Color(g.getColor().getRGB() ^ 0x00FFFFFF));
        g.drawString(Integer.toString(number), go.getX(), go.getY());
    }

    @Override
    public void accept(GraphicObjectVisitor gov) {
        go.accept(gov);
    }
}