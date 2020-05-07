package swe2.ss19.ue05.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This linked list implementation shows the use of generics and inner classes
 * 
 * @param <E> the element type
 */
public class LinkedList<E> implements List<E> {
	private Node<E> head = null;
	private int n = 0;

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
		for (E element : this) {
			if (element.equals(obj)) {
				return true;
			}
		}
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

	@Override
	public boolean addAll(Iterable<? extends E> elems) {
		for (E e : elems) {
			add(e);
		}
		return true;
	}

	@Override
	public boolean containsAll(Iterable<?> elems) {
		for (Object e : elems) {
			if (!contains(e))
				return false;
		}
		return true;
	}

	// HOF -------------------------------------------------------------------

	// TODO: The following methods have to be implemented in HigherOrderLinkedList

	@Override
	public <R> List<R> map(Function<? super E, ? extends R> mapper) {
		// TODO // works
		List<R> newList = new LinkedList<R>();
		for (E elem : this) {
			newList.add(mapper.apply(elem));
		}
		return newList;
	}

	@Override
	public List<E> filter(Predicate<? super E> predicate) {
		// TODO
		List<E> newList = new LinkedList<E>();
		for (E elem : this) {
			if (predicate.test(elem)) {
				newList.add(elem);
			}
		}
		return newList;
	}

	@Override
	public <R> R reduce(R identity, BiFunction<R, ? super E, R> accumulator) {
		// TODO
		for (E val : this) {
			identity = accumulator.apply(identity, val);
		}
		return identity;
	}

	@Override
	public List<E> distinct() {
		// TODO
		List<E> newList = new LinkedList<E>();
		return reduce(newList, (accum, elem) -> {
			if (!accum.contains(elem)) {
				accum.add(elem);
			}
			return accum;
		});
	}

// inner class Node -------------------------------------------------------

	private static class Node<F> {
		private final F value;
		private Node<F> next;

		private Node(F elem, Node<F> next) {
			this.value = elem;
			this.next = next;
		}
	}
}