package algodat2.ue02;

public class MyLinkedList<T> implements MyList<Long> {

	private int size;
	private MyListNode<Long> head;
	private MyListNode<Long> tail;

	public MyLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	@Override
	public int size() {
		return this.size;
	}

	public MyListNode<Long> getHead() {
		return this.head;
	}

	public MyListNode<Long> getTail() {
		return this.tail;
	}

	public boolean isEmpty() {
		return head == null;
	}

	private boolean inputCheck(Long val) {
		return (val != null);
	}

	private Long[] sort(Long[] array) {
		int l = array.length;
		for (int i = 0; i < l - 1; i++) {
			int min = i;
			for (int j = i + 1; j < l; j++) {
				if (array[j].compareTo(array[min]) < 0) {
					min = j;
				}
			}
			Long temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
		return array;
	}

	@Override
	public void addFirst(Long elem) throws IllegalArgumentException {
		if (this.inputCheck(elem)) {
			MyListNode<Long> newNode = new MyListNode<Long>(elem);
			if (this.isEmpty()) {
				this.head = newNode;
				this.tail = newNode;
			} else {
				newNode.setNext(head);
				this.head = newNode;
			}
			size++;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addLast(Long elem) throws IllegalArgumentException {
		if (this.inputCheck(elem)) {
			MyListNode<Long> newNode = new MyListNode<Long>(elem);
			if (this.isEmpty()) {
				this.head = newNode;
				this.tail = newNode;
			} else {
				this.tail.setNext(newNode);
				this.tail = newNode;
			}
			size++;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addSorted(Long val) throws IllegalArgumentException {
		if (this.inputCheck(val)) {
			this.addFirst(val);
			this.sortASC();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void sortASC() {
		if (this.head != null) {
			Long[] array = (Long[]) this.toArray();
			this.sort(array);
			this.clear();
			for (int i = 0; i < array.length; i++) {
				this.addLast(array[i]);
			}
		}
	}

	@Override
	public void sortDES() {
		if (this.head != null) {
			Long[] array = (Long[]) this.toArray();
			this.sort(array);
			this.clear();
			for (int i = 0; i < array.length; i++) {
				this.addFirst(array[i]);
			}
		}
	}

	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	@Override
	public Long removeFirst() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.remove(0);
		}
	}

	@Override
	public Long removeLast() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.remove(size - 1);
		}
	}

	@Override
	public Long getFirst() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.head.getElement();
		}
	}

	@Override
	public Long getLast() {
		if (this.isEmpty()) {
			return null;
		} else {
			if (this.tail == null) {
				return this.head.getElement();
			} else {
				return this.tail.getElement();
			}
		}
	}

	@Override
	public boolean contains(Long val) throws IllegalArgumentException {
		if (this.inputCheck(val)) {
			if (this.head != null) {
				MyListNode<Long> cur = this.head;
				while (cur != null) {
					if (cur.getElement().equals(val)) {
						return true;
					}
					cur = cur.getNext();
				}
			}
			return false;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Long get(int index) {
		if (index >= 0 && index < size) {
			int counter = 0;
			MyListNode<Long> cur = this.head;
			while (counter != index) {
				cur = cur.getNext();
				counter++;
			}
			return cur.getElement();
		}
		return null;
	}

	@Override
	public Long remove(int index) {
		if (index >= 0 && index < size) {
			int counter = 0;
			MyListNode<Long> cur = this.head;
			MyListNode<Long> prev = null;
			while (counter != index) {
				prev = cur;
				cur = cur.getNext();
				counter++;
			}

			if (cur == this.head && this.head == this.tail) {
				this.head = null;
				this.tail = null;
				this.size--;
				return cur.getElement();
			}
			if (cur == this.head && this.head != this.tail) {
				this.head = cur.getNext();
				this.size--;
				return cur.getElement();
			}
			if (cur == this.tail && this.head != this.tail) {
				prev.setNext(null);
				this.tail = prev;
				this.size--;
				return cur.getElement();
			}
			if (this.head != this.tail) {
				prev.setNext(cur.getNext());
				this.size--;
				return cur.getElement();
			}
		}
		return null;
	}

	@Override
	public Object[] toArray() {
		if (this.head != null) {
			Long[] array = new Long[size];
			MyListNode<Long> cur = this.head;
			for (int i = 0; i < this.size; i++) {
				array[i] = cur.getElement();
				cur = cur.getNext();
			}
			return array;
		}
		return new Long[] {};
	}

	@Override
	public String toString() {
		if (this.head != null) {
			StringBuilder temp = new StringBuilder();
			MyListNode<Long> cur = this.head;
			while (cur.getNext() != null) {
				temp.append("[" + cur.getElement() + "], ");
				cur = cur.getNext();
			}
			temp.append("[" + cur.getElement() + "]");
			return temp.toString();
		}
		return null;
	}
}