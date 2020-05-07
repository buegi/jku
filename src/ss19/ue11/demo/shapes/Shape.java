package ss19.ue11.demo.shapes;

import ss19.ue11.demo.shapes.visitor.ShapeVisitor;

/**
 * Base class for the following shapes with polymorphic draw method 
 *  
 *                  +-------------------+
 *                  |  Shape            +<-elems---------+
 *                  +-------------------+  *             |
 *                  |  draw()           |                |
 *                  |  getTop(): int    |                |  
 *                  |  ...              |                |
 *                  +-------------------+                |
 *                         /_\                           |
 *                          |                            |
 *                   +------+----------------------+     |
 *                   |                             |     |
 *          +-------------------+        +--------------------+
 *          | Primitive         |        | Composite          |
 *          +-------------------+        +--------------------+
 *          | int x, y          |        | Shape[] elems      |  
 *          +-------------------+        +--------------------+
 *             /_\
 *              |
 *        +-----+------------+
 *        |                  |
 *  +-------------+    +-------------+
 *  | Rectangle   |    | Circle      |
 *  +-------------+    +-------------+
 *  | int w, h    |    | int r       |
 *  +-------------+    +-------------+
 * 
 */
abstract public class Shape {
	
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
     * Accept an visiting visitor
     * @param v the visitor
     */
	public abstract void accept(ShapeVisitor v); 

    
}
