package algodat2.ue02;

public interface MyList<T> {
	int size(); // Returns number of elements in the List (in O(1))

	void addFirst(Long elem) // Adds element at the beginning of the list
			throws IllegalArgumentException;

	void addLast(Long elem) // Adds element as end of list
			throws IllegalArgumentException;

	void addSorted(Long val) // Adds element to the list (sorted in asc order)
			throws IllegalArgumentException;

	void sortASC(); // Sorts the list in ascending order

	void sortDES(); // Sorts the list in descending order

	void clear(); // Clears the list by removing all elements (in O(1))

	Long removeFirst(); // Returns and removes the first element from the list

	Long removeLast(); // Returns and removes the last element from the list

	Long getFirst(); // Returns the first element from the list (no removal)

	Long getLast(); // Returns the last element from the list (no removal)

	boolean contains(Long val)// Returns true if T is in list; false otherwise
			throws IllegalArgumentException;

	Long get(int index); // Returns element at index position (no removal)

	Long remove(int index); // Returns and removes element at index position

	String toString(); // Returns a string representation of the list

	Object[] toArray(); // Returns the list as array
}