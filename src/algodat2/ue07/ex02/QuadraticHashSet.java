package algodat2.ue07.ex02;

public class QuadraticHashSet extends AbstractHashSet implements MyHashSet {

	private OpenHashNode[] hashSet;
	private int size;

	public QuadraticHashSet(int capacity) {
		this.hashSet = new OpenHashNode[capacity];
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
	public boolean insert(final Integer key, final String data) throws IllegalArgumentException {
		if (key == null || data == null) {
			throw new IllegalArgumentException("Key and data must not be null!");
		}
		if (this.contains(key)) {
			return false;
		}

		final int index = this.getHashCode(key);
		for (int i = 0; i <= hashSet.length; i++) {
			final int currIndex = this.quadraticProbing(index, i);
			if (this.hashSet[currIndex] == null) {
				this.hashSet[currIndex] = new OpenHashNode(key, data);
				this.size++;
				return true;
			}

			if (this.hashSet[currIndex] != null && this.hashSet[currIndex].removed) {
				this.insertAtExistingIndex(currIndex, key, data);
				return true;
			}
		}
		return false;
	}

	private int quadraticProbing(final int index, final int i) {
		final int mul = (i + 1) / 2;
		final int ind = (index + sign(i) * mul * mul) % this.hashSet.length;
		return ind < 0 ? ind + this.hashSet.length : ind;
	}

	private int sign(final int i) {
		return i % 2 == 1 ? 1 : -1;
	}

	private void insertAtExistingIndex(final int index, final Integer key, final String data) {
		this.hashSet[index].key = key;
		this.hashSet[index].data = data;
		this.hashSet[index].removed = false;
		this.size++;
	}

	private void removeAtExistingIndex(final int index) {
//		this.hashSet[index].key = null;
//		this.hashSet[index].data = null;
		this.hashSet[index].removed = true;
		this.size--;
	}

	@Override
	public boolean contains(final Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null");
		}
		for (final OpenHashNode openHashNode : hashSet) {
			if (openHashNode != null && key.equals(openHashNode.key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(final Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}
		if (!this.contains(key)) {
			return false;
		}
		int index = this.getHashCode(key);
		for (int i = 1; i <= hashSet.length; i++) {
			if (this.hashSet[index] != null && !this.hashSet[index].removed && this.hashSet[index].key.equals(key)) {
				this.removeAtExistingIndex(index);
				return true;
			}
			index = this.quadraticProbing(index, i);
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
	public OpenHashNode[] getHashTable() {
		return this.hashSet;
	}

	@Override
	public String toString() {
		final StringBuilder hashTable = new StringBuilder();
		for (int i = 0; i < hashSet.length; i++) {
			hashTable.append(i);
			hashTable.append(" {");
			if (this.hashSet[i] != null && !this.hashSet[i].removed) {
				hashTable.append(this.hashSet[i].key);
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