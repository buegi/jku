package swe2.ss20.ue11.demo.circledraw.model;

public class Circle {

    public final int x;
    public final int y;
    public final int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean contains(int x, int y) {
        double xDiff = Math.abs(this.x - x);
        double yDiff = Math.abs(this.y - y);
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= radius;
    }

    @Override
    public String toString() {
        return "Circle [x=" + x + ", y=" + y + ", radius=" + radius + "]";
    }
}