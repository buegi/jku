package swe2.ss19.ue04.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<? super T>> implements List<T> {

	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	private boolean isEmpty() {
		return this.head == null;
	}

	private static class Node<T> {
		final T value;
		Node<T> prev;
		Node<T> next;

		private Node(T elem, Node<T> prev, Node<T> next) {
			this.value = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	private static class ReverseIterator<T> implements Iterator<T> {
		private Node<T> current;

		private ReverseIterator(Node<T> current) {
			this.current = current;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (current != null) {
				T value = current.value;
				current = current.prev;
				return value;
			} else {
				throw new NoSuchElementException("No more elements in list!");
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Node<T> current;

			{
				current = head;
			}

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				if (current != null) {
					T value = current.value;
					current = current.next;
					return value;
				} else {
					throw new NoSuchElementException("No more elements in list!");
				}
			}
		};
	}

	@Override
	public void add(T value) {
		Node<T> temp = new Node<T>(value, null, null);
		Node<T> cur = head;
		Node<T> pre = null;

		if (isEmpty()) {
			head = temp;
			tail = head;

		} else {

			while ((cur != null) && (value.compareTo(cur.value) >= 0)) {
				pre = cur;
				cur = cur.next;
			}

			// new tail
			if (cur == null) {
				temp.prev = pre;
				pre.next = temp;
				tail = temp;

				// cur not null
			} else {
				// head / new head
				if (cur == head) {
					temp.next = cur;
					cur.prev = temp;
					head = temp;

					// not head / insert between
				} else {
					temp.next = cur;
					temp.prev = pre;
					pre.next = temp;
					cur.prev = temp;
				}
			}
		}
		size++;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Index lower than 0 or higher than listsize-1 (indexOf(head) = 0)");
		}
		int counter = 0;
		for (T elem : this) {
			if (counter == index) {
				return elem;
			}
			counter++;
		}
		return null;
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		Node<T> cur = head;
		Node<T> pre = null;
		T temp = null;
		if (index < 0 || index > this.size() - 1) {
			throw new IndexOutOfBoundsException("Index lower than 0 or higher than listsize-1 (indexOf(head) = 0)");
		} else {
			for (int i = 1; i <= index; i++) {
				pre = cur;
				cur = cur.next;
			}
			// middle
			if (cur != head && cur != tail) {
				temp = cur.value;
				pre.next = cur.next;
				cur.next.prev = pre;
			}
			// if head == tail (
			if (head == tail) {
				temp = head.value;
				head = null;
				tail = null;
			}

			// if head
			if (cur == head) {
				temp = head.value;
				head = cur.next;
				head.prev = null;
			}
			// if tail
			if (cur == tail) {
				temp = tail.value;
				tail = pre;
				tail.next = null;
			}
		}
		size--;
		return temp;
	}

	@Override
	public boolean remove(Object obj) {
		if (this.contains(obj)) {
			this.remove(indexOf(obj));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			return null;
		} else {
			T elem = tail.value;
			remove(this.size() - 1);
			return elem;
		}
	}

	@Override
	public boolean contains(Object obj) {
		for (Object elem : this) {
			if (elem.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(Object obj) {
		int counter = -1;
		for (T elem : this) {
			counter++;
			if (elem == obj) {
				return counter;
			}
		}
		return -1;
	}

	public LinkedList<T> below(T value) {
		Node<T> cur = head;
		LinkedList<T> newList = new LinkedList<T>();
		while (cur.next != null && value.compareTo(cur.value) >= 0) {
			newList.add(cur.value);
			cur = cur.next;
		}
		return newList;
	}

	public LinkedList<T> above(T value) {
		Node<T> cur = tail;
		LinkedList<T> newList = new LinkedList<T>();
		while (cur.prev != null && value.compareTo(cur.value) <= 0) {
			newList.add(cur.value);
			cur = cur.prev;
		}
		return newList;
	}

	public Iterator<T> reverseIterator() {
		return new ReverseIterator<T>(this.tail);
	}
}