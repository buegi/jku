package swe2.ss19.ue05.demo.generics2;

public interface List<E> extends Iterable<E> {

	/**
	 * Returns the number of elements of this list
	 * @return the number of elements
	 */
	public int size();

	/**
	 * Adds an element to this list
	 * @param elem the element to be added 
	 */
	public void add(E elem);
	
	/** Tests if the object is contained in this list
	 * @param obj the object to be tested 
	 * @return true if obj is contained, false otherwise
	 */
	public boolean contains(Object obj);
	
	public E[] toEArray();

	public Object[] toArray();

	public <T> T[] toArray(T[] a);
}
