package algodat2.ws19.ue02;

import java.util.NoSuchElementException;

public class MinHeap<T> implements MyPriorityQueue<T> {

	private Long[] heap;
	private int size;

	public MinHeap(int initCapacity) {
		this.heap = new Long[initCapacity];
		this.size = 0;
	}

	@Override
	public String toString() {
		if (!this.isEmpty()) {
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < heap.length - 1; i++) {
				temp.append("[" + heap[i] + "], ");
			}
			temp.append("[" + heap[heap.length - 1] + "]");
			return temp.toString();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	private boolean inputCheck(Long val) {
		return (val != null);
	}

	private Long getElem(int index) {
		return this.heap[index];
	}

	@Override
	public void insert(Long elem) throws IllegalArgumentException {
		if (this.inputCheck(elem)) {
			if (this.size() >= this.heap.length) {
				this.extend();
			}
			this.heap[size] = elem;
			this.size++;
			this.upHeap(this.size() - 1);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Long removeMin() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		Long min = this.getElem(0);
		this.heap[0] = this.getElem(this.size - 1);
		this.heap[size - 1] = null;
		size--;
		this.downHeap(0);
		return min;

	}

	@Override
	public Long min() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.getElem(0);
	}

	@Override
	public Object[] toArray() {
		final Object[] obj = new Object[size()];
		System.arraycopy(this.heap, 0, obj, 0, size());
		return obj;
	}

	private void extend() {
		Long[] ex = new Long[this.heap.length * 2];
		System.arraycopy(this.heap, 0, ex, 0, this.size());
		this.heap = ex;
	}

	private void upHeap(int index) {
		if (index > 0 && this.getElem(this.parent(index)).compareTo(this.getElem(index)) > 0) {
			this.swap(index, this.parent(index));
			this.upHeap(this.parent(index));
		}
	}

	private void downHeap(int index) {
		int leftChildIndex = this.leftChild(index);
		int rightChildIndex = this.rightChild(index);

		int small = index;

		if (leftChildIndex < this.size() && this.getElem(leftChildIndex).compareTo(this.getElem(index)) < 0) {
			small = leftChildIndex;
		}
		if (rightChildIndex < this.size() && this.getElem(rightChildIndex).compareTo(this.getElem(small)) < 0) {
			small = rightChildIndex;
		}

		if (small != index) {
			this.swap(index, small);
			this.downHeap(small);
		}
	}

	private int parent(int index) {
		return index == 0 ? 0 : (index - 1) / 2;
	}

	private int leftChild(int index) {
		return ((index * 2) + 1);
	}

	private int rightChild(int index) {
		return (index * 2) + 2;
	}

	private void swap(int index1, int index2) {
		Long temp;
		temp = this.heap[index1];
		this.heap[index1] = this.heap[index2];
		this.heap[index2] = temp;
	}
}