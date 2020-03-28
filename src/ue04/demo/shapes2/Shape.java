package ue04.demo.shapes2;

import java.awt.Color;

import inout.Window;

/**
 * Base class for shapes 
 */
abstract public class Shape implements Drawable, Moveable {
	
    /**
     * Gets the left coordinate of this shape.
     * 
     * @return the left coordinate
     */
    public abstract int getLeft(); 
    
    /**
     * Gets the top coordinate of this shape.
     * 
     * @return the top coordinate
     */
    public abstract int getTop(); 
    
    /**
     * Gets the width of this shape.
     * 
     * @return the width 
     */
    public abstract int getWidth(); 
    
    /**
     * Gets the height of this shape.
     *  
     * @return the height
     */
    public abstract int getHeight(); 
    
    /**
     * Abstract class as a base class for graphical primitives. 
     * Defines position. 
     */
    static abstract class Primitive extends Shape {
    	
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

    public static class Rect extends Primitive {
    	
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
         * @see shapes.Shape#draw()
         */
        @Override
    	public void draw() {
            Window.fillRectangle(getX(), getY(), getWidth(), getHeight(), Color.GRAY);
        }
    }
    
    public static class Circle extends Primitive {
    	
    	/** Radius of the circle. */
        private final int radius;

        /**
         * Constructor initializing position and radius.
         * 
         * @param x the x-coordinate of the position
         * @param y the y-coordinate of the position
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

    public static class Group extends Shape {
    	
    	/** Elements of the group. */
        private final Shape[] subShapes; 

        /**
         * Constructor initializing elements. 
         * @param subShapes the array with the elements  
         */
        public Group(Shape... subShapes) {
            this.subShapes = subShapes;
        }
        
        /**
         * Gets the elements of this composite object.
         * 
         * @return the array of graphical elements 
         */
        public Shape[] getSubshapes() {
            return subShapes.clone();
        }
         
    	/* (non-Javadoc)
    	 * @see shapes.Shape#getLeft()
    	 */
    	@Override
    	public int getLeft() {
    		int min = Integer.MAX_VALUE; 
    		for (Shape s : subShapes) {
    			if (s.getLeft() < min) {
    				min = s.getLeft(); 
    			}
    		}
    		return min;
    	}

    	/* (non-Javadoc)
    	 * @see shapes.Shape#getTop()
    	 */
    	@Override
    	public int getTop() {
    		int min = Integer.MAX_VALUE; 
    		for (Shape s : subShapes) {
    			if (s.getTop() < min) {
    				min = s.getTop(); 
    			}
    		}
    		return min;
    	}

    	/* (non-Javadoc)
    	 * @see shapes.Shape#getWidth()
    	 */
    	@Override
    	public int getWidth() {
    		int right = 0; 
    		for (Shape sub : subShapes) {
    			int subRight = sub.getLeft() + sub.getWidth(); 
    			if (right < subRight) {
    				right = subRight; 
    			}
    		}
    		return right - getLeft();
    	}

    	/* (non-Javadoc)
    	 * @see shapes.Shape#getHeight()
    	 */
    	@Override
    	public int getHeight() {
    		int bottom = 0; 
    		for (Shape sub : subShapes) {
    			int subBottom = sub.getTop() + sub.getHeight(); 
    			if (bottom < subBottom) {
    				bottom = subBottom; 
    			}
    		}
    		return bottom - getTop();
    	}
    	
        /* (non-Javadoc)
         * @see shapes.Shape#draw()
         */
        @Override
    	public void draw() {
            for (Shape s : subShapes) {
                s.draw();
            }
        }

    	@Override
    	public void move(int dx, int dy) {
    		for (Shape s : subShapes) {
                s.move(dx, dy);
            }
    	}

    }

}
