package swe2.ss20.ue04.demo.shapes2;

/**
 * Interface for movable objects 
 * @author hp
 *
 */
public interface Moveable {
	/**
	 * Moves the object dx and dy coordinates 
	 * @param dx
	 * @param dy
	 */
	void move(int dx, int dy); 
	
	/**
	 * Moves the object dx coordinates to the right
	 * @param dx
	 */
	default void right(int dx) {
		move(dx, 0); 
	}

	/**
	 * Moves the object dx coordinates to the left
	 * @param dx
	 */
	default void left(int dx) {
		move(-dx, 0); 
	}

	/**
	 * Moves the object dy coordinates up
	 * @param dy
	 */
	default void up(int dy) {
		move(0, -dy); 
	}

	/**
	 * Moves the object dy coordinates down
	 * @param dy
	 */
	default void down(int dy) {
		move(0, dy); 
	}

}
