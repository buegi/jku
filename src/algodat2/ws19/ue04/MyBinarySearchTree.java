package algodat2.ws19.ue04;

public class MyBinarySearchTree implements BinarySearchTree {

//  no duplicates allowed
//	For the implementation of toArrayPostOrder an auxiliary function
//	private int toArrayPostOrder (Object[] ret, int offset, BinaryTreeNode n) { � }
//	is useful, that starts writing to the array at index offset and returns the last index written to the temporary array
//	ret (similar auxiliary functions could also be useful for the other toArray-traversal implementations). Consider if
//	other help functions might be useful for reusing code.
//	Note: For the assignment 05 this tree structure

	private BinaryTreeNode root;
	private int size;
	private boolean helper;

	private static class Index {
		private int i = 0;
	}

	public MyBinarySearchTree() {
		this.root = null;
		this.size = 0;
		this.helper = false;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public BinaryTreeNode getRoot() {
		return this.root;
	}

	@Override
	public boolean insert(Integer key, String elem) throws IllegalArgumentException {
		if (key == null || elem == null) {
			throw new IllegalArgumentException();
		}
		this.root = this.recursiveInsert(this.root, key, elem);
		if (this.helper == true) {
			return false;
		} else {
			this.size++;
			return true;
		}
	}

	@Override
	public String find(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		String val = this.recursiveFind(this.root, key);
		return val;
	}

	@Override
	public boolean remove(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		this.helper = true;
		this.root = this.recursiveRemove(this.root, key);
		if (this.root != null && this.helper == false) {
			this.size--;
			return true;
		}
		return false;
	}

	@Override
	public Object[] toArrayPostOrder() {
		Object[] obj = new Object[size()];
		recursivePostOrder(obj, this.root, new Index());
		return obj;
	}

	@Override
	public Object[] toArrayInOrder() {
		Object[] obj = new Object[size()];
		recursiveInOrder(obj, this.root, new Index());
		return obj;
	}

	@Override
	public Object[] toArrayPreOrder() {
		Object[] obj = new Object[size()];
		recursivePreOrder(obj, this.root, new Index());
		return obj;
	}

	@Override
	public Integer getParent(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (this.isRoot(key)) {
			return null;
		}
		BinaryTreeNode par = this.recursiveParent(this.root, key);
		return par != null ? par.key : null;
	}

	@Override
	public boolean isRoot(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return this.root.key == key;
	}

	@Override
	public boolean isInternal(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return this.recursiveInternal(this.root, key);
	}

	@Override
	public boolean isExternal(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return this.recursiveExternal(this.root, key);
	}

	private BinaryTreeNode iterateInOrderSuccessor(BinaryTreeNode node) {
		BinaryTreeNode cur = node;
		while (node.left != null) {
			cur = node.left;
			node = node.left;
		}
		return cur;
	}

	private String recursiveFind(BinaryTreeNode node, Integer key) {
		if (node == null) {
			return null;
		}
		if (node.key == key) {
			return node.elem;
		}
		if (node.key > key) {
			return this.recursiveFind(node.left, key);
		}
		return this.recursiveFind(node.right, key);
	}

	private BinaryTreeNode recursiveInsert(BinaryTreeNode node, int key, String elem) {
		if (node == null) {
			node = new BinaryTreeNode(key, elem);
			this.helper = false;
			return node;
		}
		if (node.key > key) {
			node.left = this.recursiveInsert(node.left, key, elem);
		}
		if (node.key < key) {
			node.right = this.recursiveInsert(node.right, key, elem);
		}
		if (node.key == key) {
			this.helper = true;
			return node;
		}
		return node;
	}

	private BinaryTreeNode recursiveRemove(BinaryTreeNode node, Integer key) {
		if (node == null) {
			return node;
		}
		if (node.key > key) {
			node.left = this.recursiveRemove(node.left, key);
		} else if (node.key < key) {
			node.right = this.recursiveRemove(node.right, key);
		} else {
			if (node.left == null) {
				this.helper = false;
				return node.right;
			}
			if (node.right == null) {
				this.helper = false;
				return node.left;
			}
			node.key = this.iterateInOrderSuccessor(node.right).key;
			node.elem = this.iterateInOrderSuccessor(node.right).elem;
			this.helper = false;
			node.right = this.recursiveRemove(node.right, node.key);
		}
		return node;
	}

	private void recursiveInOrder(Object[] obj, BinaryTreeNode node, Index index) {
		if (node != null) {
			recursiveInOrder(obj, node.left, index);
			obj[index.i++] = node.elem;
			recursiveInOrder(obj, node.right, index);
		}
	}

	private void recursivePreOrder(Object[] obj, BinaryTreeNode node, Index index) {
		if (node != null) {
			obj[index.i++] = node.elem;
			this.recursivePreOrder(obj, node.left, index);
			this.recursivePreOrder(obj, node.right, index);
		}
	}

	private void recursivePostOrder(Object[] obj, BinaryTreeNode node, Index index) {
		if (node != null) {
			this.recursivePostOrder(obj, node.left, index);
			this.recursivePostOrder(obj, node.right, index);
			obj[index.i++] = node.elem;
		}
	}

	private BinaryTreeNode recursiveParent(BinaryTreeNode node, Integer key) {
		if (node == null) {
			return null;
		}
		int cmp = node.key.compareTo(key);
		if ((node.left != null && node.left.key.compareTo(key) == 0)
				|| (node.right != null && node.right.key.compareTo(key) == 0)) {
			cmp = 0;
		}
		if (cmp > 0) {
			return this.recursiveParent(node.left, key);
		}
		if (cmp < 0) {
			return this.recursiveParent(node.right, key);
		}
		return node;
	}

	private boolean recursiveInternal(BinaryTreeNode node, Integer key) {
		if (node == null) {
			return false;
		}
		if (node.key == key) {
			if (node.left != null) {
				return true;
			}
			if (node.right != null) {
				return true;
			}
			return false;
		}
		if (node.key > key) {
			return this.recursiveInternal(node.left, key);
		}
		return this.recursiveInternal(node.right, key);
	}

	private boolean recursiveExternal(BinaryTreeNode node, Integer key) {
		if (node == null) {
			return false;
		}
		if (node.key == key) {
			if (node.left == null && node.right == null) {
				return true;
			}
			return false;
		}
		if (node.key > key) {
			return this.recursiveExternal(node.left, key);
		}
		return this.recursiveExternal(node.right, key);
	}
}