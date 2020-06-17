package swe2.ss20.ue11.graphiceditor.objects;

import java.awt.Graphics;
import java.awt.Polygon;

import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;

public class Star extends GraphicObject {

    private final int edges;
    private Polygon pol;
    private final int radius;

    public Star(final int x, final int y, final int edges, final int radius) {
        super(x, y);
        this.edges = edges;
        this.pol = new Polygon();
        createPolygon();
        this.radius = radius;
    }

    @Override
    public void paint(Graphics g) {
        g.fillPolygon(pol);
    }

    @Override
    public int getWidth() {
        return pol.getBounds().width;
    }

    @Override
    public int getHeight() {
        return pol.getBounds().height;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        createPolygon();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        createPolygon();
    }

    private void createPolygon() {
        final double radiant = Math.PI / edges;
        int xCoord, yCoord, factor;
        pol = new Polygon();
        for (int i = 0; i <= edges * 2; i++) {
            factor = (i & 1) + 1;
            xCoord = (int) ((Math.cos(radiant * i) * radius * factor) + x);
            yCoord = (int) ((Math.sin(radiant * i) * radius * factor) + y);
            pol.addPoint(xCoord, yCoord);
        }
    }

    public String toString() {
        return "Star [" + radius + ", " + edges + "]";
    }

    @Override
    public GraphicObject copy() {
        return super.copy();
    }

    public void accept(GraphicObjectVisitor gov) {
        gov.visit(this);
    }
}