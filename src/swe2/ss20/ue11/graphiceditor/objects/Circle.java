package swe2.ss20.ue11.graphiceditor.objects;

import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;

import java.awt.Graphics;

/**
 * Circular graphical object.
 */
public class Circle extends GraphicObject {

    private final int r;

    /**
     * Constructor initializing center and radius
     *
     * @param x the x-coordinate of the circle
     * @param y the y-coordinate of the circle
     * @param r the radius of the circle
     */
    public Circle(int x, int y, int r) {
        super(x, y);
        this.r = r;
    }

    /**
     * Paints the circle on the graphics context
     *
     * @param g the graphics context
     */
    @Override
    public void paint(Graphics g) {
        g.fillOval(x - r, y - r, r * 2, r * 2);
    }

    /**
     * Returns a string representation.
     */
    @Override
    public String toString() {
        return "Circle[" + r + "]";
    }

    /**
     * Gets the width of this object
     *
     * @return the width
     */
    @Override
    public int getWidth() {
        return 2 * r;
    }

    /**
     * Gets the height of this object
     *
     * @return the height
     */
    @Override
    public int getHeight() {
        return getWidth();
    }

    // TODO Task 2: copy method
    @Override
    public GraphicObject copy() {
        return super.copy();
    }

    // TODO Task 4: accept method
    public void accept(GraphicObjectVisitor gov) {
        gov.visit(this);
    }
}
