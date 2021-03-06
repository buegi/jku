package swe2.ss20.ue04.demo.shapes1;

/**
 * Abstract class as a base class for graphical primitives. 
 * Defines position. 
 */
abstract public class Primitive extends Shape {
	
	/** x-coordinate of the figure. */
    private int x;
	/** y-coordinate of the figure. */
    private int y;

    /**
     * Constructor initializing position of graphical object.
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    public Primitive(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of this object.
     * 
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y-coordinate of this object.
     * 
     * @return the y-coordinate
     */
	public int getY() {
        return y;
    }
	
	public void move(int dx, int dy) {
		x += dx; 
		y += dy; 
	}

}
