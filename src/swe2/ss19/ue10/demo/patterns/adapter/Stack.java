package swe2.ss19.ue10.demo.patterns.adapter;

/**
 * Interface for stacks of elements of type T
 * 
 * @param <T>
 *            the type of the elements in the stack
 */
public interface Stack<T> {
	/**
	 * Tests if the stack is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Pushes a element on the stack
	 * 
	 * @param o
	 *            the element to push on the stack
	 */
	public void push(T o);

	/**
	 * Returns the top element on the stack. Does not remove the element.
	 * 
	 * @return the top element
	 */
	public T top();

	/**
	 * Removes the top element on the stack.
	 */
	public void pop();
}
