package ss20.ue04.demo.shapes1;

/**
 * Composite of multiple shapes.
 */
public class Group extends Shape {
	
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
