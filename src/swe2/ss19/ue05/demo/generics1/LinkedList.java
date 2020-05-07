package swe2.ss19.ue05.demo.generics1;

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

	// static generic methods -----------------------------------------------------
	// All of the following three methods encode the same behavior

	// Why is <T> needed here -> Because method is static (a "helper method") -> Method takes two lists -> We have to specify of which generic type these lists are
	// What is <T>? -> The type of object in the target list -> Thus, the type of the source list may extend T
	public static <T> void copyTo(List<? extends T> from, List<T> to) {
		for (T e : from) {
			to.add(e);
		}
	}
	
	// <T> is type of the source list -> Thus, type of the target list may be super type of T.
	public static <T> void copyTo2(List<T> from, List<? super T> to) {
		for (T e : from) {
			to.add(e);
		}
	}

	// Two type parameters
	public static <T, F extends T> void copyTo3(List<F> from, List<T> to) {
		for (F e : from) {
			to.add(e);
		}
	}		
}
