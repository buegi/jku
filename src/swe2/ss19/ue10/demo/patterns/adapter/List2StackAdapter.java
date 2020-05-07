package swe2.ss19.ue10.demo.patterns.adapter;

import java.util.List;

public class List2StackAdapter<T> implements Stack<T> {
	/**
	 * the list to be adapted
	 */
	private final List<T> list;

	/**
	 * Constructor for the adapter adapting the list
	 * 
	 * @param list
	 *            the list to be adapted
	 */
	public List2StackAdapter(List<T> list) {
		this.list = list;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(T element) {
		list.add(element);
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	@Override
	public void pop() {
		list.remove(list.size() - 1);
	}
}
