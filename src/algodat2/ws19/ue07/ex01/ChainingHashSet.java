package algodat2.ws19.ue07.ex01;

public class ChainingHashSet extends AbstractChainingHashSet implements MyHashSet {

	private ChainingHashNode[] hashSet;
	private int size;

	public ChainingHashSet(int capacity) {
		this.hashSet = new ChainingHashNode[capacity];
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	private int getHashCode(final int key) {
		final int hash = this.getHashCode(key, this.hashSet.length);
		return hash < 0 ? hash + this.hashSet.length : hash;
	}

	@Override
	public boolean insert(Integer key, String data) throws IllegalArgumentException {
		if (key == null || data == null) {
			throw new IllegalArgumentException("Key and data must not be null!");
		}

		if (this.contains(key)) {
			return false;
		}
		int index = this.getHashCode(key);
		if (this.hashSet[index] == null) {
			this.hashSet[index] = new ChainingHashNode(key, data);
			size++;
			return true;
		}
		ChainingHashNode temp = this.hashSet[index];
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = new ChainingHashNode(key, data);
		size++;
		return true;
	}

	@Override
	public boolean contains(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null");
		}

		int index = this.getHashCode(key);
		if (this.hashSet[index] == null) {
			return false;
		} else {
			ChainingHashNode temp = this.hashSet[index];
			while (temp != null) {
				if (temp.key.equals(key)) {
					return true;
				}
				temp = temp.next;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}
		if (this.contains(key)) {
			int index = this.getHashCode(key);
			if (this.hashSet[index].key.equals(key)) {
				this.hashSet[index] = this.hashSet[index].next;
				size--;
				return true;
			} else {
				ChainingHashNode temp = this.hashSet[index];
				ChainingHashNode prev = temp;
				while (temp.key != null) {
					if (temp.key.equals(key)) {
						prev.next = temp.next;
						size--;
						return true;
					}
					temp = prev;
					temp = temp.next;
				}
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.hashSet.length; i++) {
			this.hashSet[i] = null;
		}
		this.size = 0;
	}

	@Override
	public ChainingHashNode[] getHashTable() {
		return this.hashSet;
	}

	@Override
	public String toString() {
		StringBuffer hashTable = new StringBuffer();
		for (int i = 0; i < this.hashSet.length; i++) {
			hashTable.append(i);
			hashTable.append(" {");
			if (this.hashSet[i] != null) {
				ChainingHashNode temp = this.hashSet[i];
				while (temp != null) {
					hashTable.append(temp.key);
					if (temp.next != null) {
						hashTable.append(", ");
					}
					temp = temp.next;
				}
			} else {
				hashTable.append("-");
			}
			hashTable.append("}");
			if (i < this.hashSet.length - 1) {
				hashTable.append(", ");
			}
		}
		return hashTable.toString();
	}
}