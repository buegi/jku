package ss20.ue04.demo.shapes1;

/**
 * Base class for the following shapes with polymorphic draw method 
 *  
 *                  +-------------------+
 *                  |  Shape            +<-subShapes-----+
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
 *          | Primitive         |        | Group              |
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
    
}
