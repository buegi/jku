package algodat2.ws19.ue03.ex01;

import java.util.NoSuchElementException;

public class MinHeap<T> implements MyPriorityQueue<Long> {

	private Long[] heap;
	private int size;

	// create standard minHeap with size
	public MinHeap(int initCapacity) {
		this.heap = new Long[initCapacity];
		this.size = 0;
	}

	// create a MinHeap using bottom-up construction in-place
	public MinHeap(Long list[]) throws IllegalArgumentException {
		this.arrayCheck(list);
		// this.heap = this.bottomUp(list); description see bottomUp()
		this.heap = list;
		this.size = list.length;
		for (int i = (size - 1) / 2; i >= 0; i--) {
			this.downHeap(i);
		}
	}

	// sorting with HeapSort
	public static void sort(Long list[]) throws IllegalArgumentException {
		final MinHeap<Long> listHeap = new MinHeap<Long>(list);
		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = listHeap.removeMin();
		}
	}

	// return value of arrayindex
	// only public for testing purposes (see MinHeapTest) !
	public Long get(int index) {
		return this.heap[index];
	}

	// true if val == null
	private boolean inputCheck(Long val) {
		return (val == null);
	}

	private void arrayCheck(Long[] val) {
		if (val == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < val.length; i++) {
			if (val[i] == null) {
				throw new IllegalArgumentException();
			}
		}
	}

	// search of elements
	public boolean contains(Long val) throws IllegalArgumentException {
		if (inputCheck(val)) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < this.heap.length; i++) {
			if (this.heap[i] == val) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void insert(Long elem) throws IllegalArgumentException {
		if (this.inputCheck(elem)) {
			throw new IllegalArgumentException();
		}

		if (this.size() >= this.heap.length) {
			this.extend();
		}

		this.heap[size] = elem;
		this.size++;
		this.upHeap(this.size() - 1);
	}

	@Override
	public Long min() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.get(0);
	}

	@Override
	public Long removeMin() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		Long min = this.get(0);
		this.heap[0] = this.get(this.size - 1);
		this.heap[size - 1] = null;
		size--;
		this.downHeap(0);
		return min;
	}

	private void upHeap(int index) {
		if (index > 0 && this.get(this.parent(index)).compareTo(this.get(index)) > 0) {
			this.swap(index, this.parent(index));
			this.upHeap(this.parent(index));
		}
	}

	private void downHeap(int index) {
		int leftChildIndex = this.leftChild(index);
		int rightChildIndex = this.rightChild(index);

		int small = index;

		if (leftChildIndex < this.size() && this.get(leftChildIndex).compareTo(this.get(index)) < 0) {
			small = leftChildIndex;
		}
		if (rightChildIndex < this.size() && this.get(rightChildIndex).compareTo(this.get(small)) < 0) {
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

	private void extend() {
		Long[] ex = new Long[this.heap.length * 2];
		System.arraycopy(this.heap, 0, ex, 0, this.size());
		this.heap = ex;
	}

	@Override
	public Object[] toArray() {
		final Object[] obj = new Object[size()];
		System.arraycopy(this.heap, 0, obj, 0, size());
		return obj;
	}

	@Override
	public String toString() {
		if (!this.isEmpty()) {
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < this.heap.length - 1; i++) {
				temp.append("[" + this.heap[i] + "], ");
			}
			temp.append("[" + this.heap[this.heap.length - 1] + "]");
			return temp.toString();
		}
		return null;
	}

	// complete implementation of bottomUpConstruction for testing, please ignore
	// for grading
	private Long[] bottomUp(Long list[]) throws IllegalArgumentException {
		this.arrayCheck(list);
		if (list == null) {
			throw new IllegalArgumentException();
		}
		for (int i = list.length / 2; i >= 0; i--) {
			int k = i;
			Long v = list[k];
			boolean heapOk = false;

			while (!heapOk && (2 * k) <= list.length - 1) {
				System.out.println("2");
				int j = 2 * k;
				if (j < list.length - 1) {
					if (list[j].compareTo(list[j + 1]) > 0) {
						j = j + 1;
					}
				}
				if (v.compareTo(list[j]) <= 0) {
					heapOk = true;
				} else {
					list[k] = list[j];
					k = j;
				}
			}
			list[k] = v;
		}
		return list;
	}
}