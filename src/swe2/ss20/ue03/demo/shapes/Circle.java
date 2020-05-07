package swe2.ss20.ue03.demo.shapes;

import swe2.inout.Window;

import java.awt.Color;

/**
 * Class representing circular shape.
 */
public class Circle extends Primitive {

    /**
     * Radius of the circle.
     */
    private final int radius;

    /**
     * Constructor initializing position and radius.
     *
     * @param x      the x-coordinate of the position
     * @param y      the y-coordinate of the position
     * @param radius the radius of this circle
     */
    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    /**
     * Gets the radius of this circle.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /* (non-Javadoc)
     * @see shapes.Shape#getLeft()
     */
    @Override
    public int getLeft() {
        return getX() - radius;
    }

    /* (non-Javadoc)
     * @see shapes.Shape#getTop()
     */
    @Override
    public int getTop() {
        return getY() - radius;
    }

    /* (non-Javadoc)
     * @see shapes.Shape#getWidth()
     */
    @Override
    public int getWidth() {
        return radius * 2;
    }

    /* (non-Javadoc)
     * @see shapes.Shape#getHeight()
     */
    @Override
    public int getHeight() {
        return radius * 2;
    }

    /* (non-Javadoc)
     * @see shapes.Shape#draw()
     */
    @Override
    public void draw() {
        Window.fillCircle(getX(), getY(), getRadius(), Color.GRAY);
    }
}