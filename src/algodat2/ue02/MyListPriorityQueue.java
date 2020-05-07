package algodat2.ue02;

import java.util.NoSuchElementException;

public class MyListPriorityQueue<T> implements MyPriorityQueue<Long> {

	private MyLinkedList<Long> queue;

	public MyListPriorityQueue() {
		this.queue = new MyLinkedList<Long>();
	}

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	@Override
	public int size() {
		return this.queue.size();
	}

	@Override
	public void insert(Long elem) throws IllegalArgumentException {
		this.queue.addSorted(elem); // exception thrown directly in addSorted of LinkedList
	}

	@Override
	public Long removeMin() throws NoSuchElementException {
		if (this.queue.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return this.queue.removeFirst();
		}
	}

	@Override
	public Long min() throws NoSuchElementException {
		if (this.queue.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return this.queue.getFirst();
		}
	}

	@Override
	public Object[] toArray() {
		return this.queue.toArray();
	}

	@Override
	public String toString() {
		return this.queue.toString();
	}
}