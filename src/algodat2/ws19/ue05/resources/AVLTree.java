package algodat2.ws19.ue05.resources;

import java.util.Objects;

public class AVLTree {

	protected AVLNode root;
	protected int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public boolean insert(Integer key, String elem) throws IllegalArgumentException {
		if (key == null || elem == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			root = new AVLNode(key, elem);
			size++;
			return true;
		}
		return this.add(root, key, elem);
	}

	private boolean add(AVLNode node, Integer key, String elem) {
		if (Objects.equals(node.key, key)) {
			return false;
		}
		AVLNode parent = node;
		boolean goLeft = node.key > key;
		node = goLeft ? node.left : node.right;

		if (node == null) {
			if (goLeft) {
				parent.left = new AVLNode(key, elem);
				parent.left.parent = parent;
			} else {
				parent.right = new AVLNode(key, elem);
				parent.right.parent = parent;
			}
			restructure(parent);
			size++;
			return true;
		}

		return this.add(node, key, elem);
	}

	private void delete(AVLNode node) {
		if (node.left == null && node.right == null) {
			if (node.parent == null) {
				root = null;
			} else {
				AVLNode parent = node.parent;
				if (parent.left == node) {
					parent.left = null;
				} else {
					parent.right = null;
				}
				restructure(parent);
			}
			return;
		}

		if (node.left != null) {
			AVLNode child = node.left;
			while (child.right != null) {
				child = child.right;
			}
			node.key = child.key;
			node.elem = child.elem;
			delete(child);
		} else {
			AVLNode child = node.right;
			while (child.left != null) {
				child = child.left;
			}
			node.key = child.key;
			node.elem = child.elem;
			delete(child);
		}
	}

	public String find(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		AVLNode temp = this.root;
		while (temp != null) {
			if (key < temp.key) {
				temp = temp.left;
			} else if (key > temp.key) {
				temp = temp.right;
			} else {
				return temp.elem;
			}
		}
		return null;
	}

	public boolean remove(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		if (root == null) {
			return false;
		}
		AVLNode child = root;
		while (child != null) {
			AVLNode node = child;
			child = key >= node.key ? node.right : node.left;
			if (Objects.equals(key, node.key)) {
				delete(node);
				size--;
				return true;
			}
		}
		return false;
	}

	private void restructure(AVLNode n) {
		updateHeights(n);

		int balance = getHeight(n.right) - getHeight(n.left);

		if (balance == -2) {
			if (getHeight(n.left.left) >= getHeight(n.left.right)) {
				n = rightRotate(n);
			} else {
				n = leftRotateThenRight(n);
			}
		} else if (balance == 2) {
			if (getHeight(n.right.right) >= getHeight(n.right.left)) {
				n = leftRotate(n);
			} else {
				n = rightRotateThenLeft(n);
			}
		}
		if (n.parent != null) {
			restructure(n.parent);
		} else {
			root = n;
		}
	}

	private AVLNode leftRotate(AVLNode a) {

		AVLNode b = a.right;
		b.parent = a.parent;
		a.right = b.left;
		if (a.right != null) {
			a.right.parent = a;
		}
		b.left = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}
		updateHeights(a);
		updateHeights(b);
		return b;
	}

	private AVLNode rightRotate(AVLNode a) {

		AVLNode b = a.left;
		b.parent = a.parent;

		a.left = b.right;

		if (a.left != null) {
			a.left.parent = a;
		}

		b.right = a;
		a.parent = b;

		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}
		updateHeights(a);
		updateHeights(b);
		return b;
	}

	private AVLNode leftRotateThenRight(AVLNode n) {
		n.left = leftRotate(n.left);
		return rightRotate(n);
	}

	private AVLNode rightRotateThenLeft(AVLNode n) {
		n.right = rightRotate(n.right);
		return leftRotate(n);
	}

	private int getHeight(AVLNode n) {
		if (n == null) {
			return -1;
		}
		return n.height;
	}

	public int height() {
		if (root == null) {
			return -1;
		}
		return root.height;
	}

	private void updateHeights(AVLNode node) {
		if (node != null) {
			node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		}
	}

	public int size() {
		return size;
	}

	/**
	 * @return the root of the AVL tree
	 */
	public AVLNode getRoot() {
		return root;
	}

	int addToArray(AVLNode node, Object[] arr, int i) {
		if (node == null) {
			return i;
		}
		arr[i] = node.elem;
		i++;
		if (node.left != null) {
			i = this.addToArray(node.left, arr, i);
		}
		if (node.right != null) {
			i = this.addToArray(node.right, arr, i);
		}
		return i;
	}

	/**
	 * Yields an array representation of the tree (-order).
	 *
	 * @return Array representation of the tree.
	 */
	public Object[] toArray() {
		Object[] preOrder = new Object[this.size];
		this.addToArray(this.root, preOrder, 0);
		return preOrder;
	}
}