package ue04.demo.shapes4;

import java.awt.Color;

import inout.Window;

/**
 * Base class for shapes 
 */
abstract public class Shape implements Drawable, Moveable {
	
	/**
	 * Member class demo. 
	 * Creates an up animation with a member class YAnimation 
	 * @param dy
	 * @return
	 */
    public Animation createUpAnimation(int dy) {
        return new YAnimation(-dy); //this.new YAnimation(-dy);
    }
    
	/**
	 * Member class demo. 
	 * Creates an up animation with a member class YAnimation 
	 * @param dy
	 * @return
	 */
    public Animation createDownAnimation(int dy) {
        return new YAnimation(dy); // this.new YAnimation(dy);
    }
    	
    /**
     * Private inner member class for animation along the y-coordinate
     */
    public class YAnimation implements Animation {
    	private final int dy; 
    	
		public YAnimation(int dy) {
			super();
			this.dy = dy;
		}

		@Override
		public void animate() {
			if (dy < 0) {
				Shape.this.up(-dy); // up is called on Shape.this
			} else {
				Shape.this.down(dy); // down is called on Shape.this
			}
		}
    }

	/**
	 * Local class demo. 
	 * Creates an up animation with a local class RightAnimation 
	 * @param dx
	 * @return
	 */
    public Animation createRightAnimation(int dx) {
    	
	    	class RightAnimation implements Animation {
	    		private final int dx; 
	
				public RightAnimation(int dx) {
					super();
					this.dx = dx;
				}
	
				@Override
				public void animate() {
					right(dx);
					//dx += 1; // works if dx is not final
				}
	    	}

	    	return new RightAnimation(dx) {
        };
    }

	/**
	 * Local class demo version 2. 
	 * Creates an up animation with a local class RightAnimation 
	 * @param dx
	 * @return
	 */
    public Animation createRightAnimation2(int dx) {
    	
	    	class RightAnimation implements Animation {
	    		
				@Override
				public void animate() {
					right(dx);
					//dx += 1; // does not work because dx is local variable 
				}
	    	}
	    	
	        return new RightAnimation() {
        };
    }
    
    public Animation createLeftAnimation(int dx) {
        return new Animation() {
            public void animate() {
                left(dx);
				//dx += 1; // does not work because dx is local variable 
            }
        };
    }

    /**
	 * Creates a rectangular shape 
	 * @param x the x-position
	 * @param y the y-position 
	 * @param w the width
	 * @param h the height
	 * @return a rectangular shape 
	 */
	public static Shape rect(int x, int y, int w, int h) {
		return new Rect(x, y, w, h); 
	}
	
	/**
	 * Creates a circular shape 
	 * @param x the x-position
	 * @param y the y-position 
	 * @param r the radius
	 * @return a circular shape 
	 */
	public static Shape circle(int x, int y, int r) {
		return new Circle(x, y, r); 
	}
	
	/**
	 * Creates a group of shapes 
	 * @param shapes the shapes of this group
	 * @return the grouped shape
	 */
	public static Shape group(Shape...shapes) {
		return new Group(shapes); 
	}
	
	
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
     
 
    // -- inner Shape classes -------------------------------------------------
    
     /**
     * Abstract class as a base class for graphical primitives. 
     * Defines position. 
     */
    private static abstract class Primitive extends Shape {
    	
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
        private Primitive(int x, int y) {
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
    	
       	@Override
        public Animation createDownAnimation(int dy) {
            return new PrimitiveYAnimation(dy); // this.new YAnimation(dy);
        }

       	// Das gleiche mit anonymer Klasse 
//       @Override
//       public Animation createDownAnimation(int dy) {
//            return new Animation() {
//
//				@Override
//				public void animate() {
//					y += dy; // Primitive.this.y += this.dy; 
//				}
//            	
//            };
//        }

    	private class PrimitiveYAnimation implements Animation {
    		private final int dy; 
     		public PrimitiveYAnimation(int dy) {
    			this.dy = dy;
    		}

    		@Override
    		public void animate() {
    			y += dy; // Primitive.this.y += this.dy; 
    		}
    	}

    }

    private static class Rect extends Primitive {
    	
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
        private Rect(int x, int y, int width, int height) {
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
    
    private static class Circle extends Primitive {
    	
    	/** Radius of the circle. */
        private final int radius;

        /**
         * Constructor initializing position and radius.
         * 
         * @param x the x-coordinate of the position
         * @param y the y-coordinate of the position
         * @param radius the radius of this circle
         */
        private Circle(int x, int y, int radius) {
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

    private static class Group extends Shape {
    	
    	/** Elements of the group. */
        private final Shape[] subShapes; 

        /**
         * Constructor initializing elements. 
         * @param subShapes the array with the elements  
         */
        private Group(Shape... subShapes) {
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
