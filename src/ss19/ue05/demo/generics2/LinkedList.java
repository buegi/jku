package ss19.ue05.demo.generics2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This linked list implementation shows the use of generics and inner classes 
 * 
 * @param <E> the element type
 */
public class LinkedList<E> implements List<E> {

	@Override
	public int size() {
		return n;
	}

	public void add(E elem) {
		head = new Node<E>(elem, head);
		n++;
	}
	
	@Override
	public boolean contains(Object obj) {
		// TODO using iterator
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			
				private Node<E> current;

				{
					current = head;
				}

				@Override
				public boolean hasNext() {
					return current != null;
				}

				@Override
				public E next() {
					if (current != null) {
						E value = current.value;
						current = current.next;
						return value;
					} else {
						throw new NoSuchElementException("No more element in list");
					}
				}
		};
	}

	// private and protected --------------------------------------------------

	protected Node<E> head = null;

	protected int n = 0;

	// inner class Node -------------------------------------------------------

	protected static class Node<F> {
		final F value;
		Node<F> next;

		Node(F elem, Node<F> next) {
			this.value = elem;
			this.next = next;
		}
	}

	// additional methods -----------------------------------------------------	
	@Override
	public Object[] toArray() {
		Object[] a = new Object[size()];
		int i = 0;
		for (E e : this) {
			a[i++] = e;
		}
		return a;
	}

	// Exposing this method as E[] is a pitfall!
	// https://stackoverflow.com/questions/17831896/creating-generic-array-in-java-via-unchecked-type-cast
	public E[] toEArray() {
		// E[] objs = new E[size()]; // Cannot create generic array
		
		Object[] objs = new Object[size()];
		// Using Object[] is risky, since following line is allowed:
		// objs[0] = new Object();
		// This can lead to runtime errors.

		E[] a = (E[]) objs; // This works as unchecked cast
		int i = 0;
		for (E e : this) {
			a[i++] = e;
		}
		return a;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size()) {
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size());
		}
		Object[] objs = a;
		int i = 0;
		for (E e : this) {
			objs[i++] = e; // will result in ArrayStoreException when not T super E
		}
		return a;
	}

	// Not allowed! Why?
	// Because "super" keyword is only allowed in conjunction with "?" (i.e., at usage-time like List<? super Person>)
	// It is not allowed to restrict another generic type using "super"
//	 public <T super E> T[] toArray1(T[] a) {
//		 //...
//		 return null;
//	 }
}
