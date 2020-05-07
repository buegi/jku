package swe2.ss19.ue11.demo.shapes;

import swe2.ss19.ue11.demo.shapes.visitor.ShapeVisitor;

/**
 * Class representing rectangular shapes.
 */
public class Rect extends Primitive {
	
	/** Width of the rectangle. */
    private final int width;
    
    /** Height of the rectangle. */
    private final int height;
    
    /**
     * Constructor initalizing position, width and heigth.
     * 
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     * @param width the width of this rectangle
     * @param height the heigth of this rectangle
     */
    public Rect(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

	/* (non-Javadoc)
	 * @see shapes.Shape#getLeft()
	 */
	@Override
	public int getLeft() {
		return getX();
	}

	/* (non-Javadoc)
	 * @see shapes.Shape#getTop()
	 */
	@Override
	public int getTop() {
		return getY();
	}

	/* (non-Javadoc)
	 * @see shapes.Shape#getWidth()
	 */
	@Override
    public int getWidth() {
        return width;
    }
    
	/* (non-Javadoc)
	 * @see shapes.Shape#getHeight()
	 */
	@Override
	public int getHeight() {
        return height;
    }
    
	/* (non-Javadoc)
	 * @see shapes.Shape#accept(shapes.visitor.ShapeVisitor)
	 */
	@Override
	public void accept(ShapeVisitor v) {
		v.visit(this);
	}

}
