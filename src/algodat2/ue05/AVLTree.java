package algodat2.ue05;

public class AVLTree {

	protected AVLNode root;
	protected int size;

	/**
	 * Default constructor. Initializes the AVL tree.
	 */
	public AVLTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Inner class for toArray methods
	 */
	private static class Index {
		private int i = 0;
	}

	/**
	 * Yields number of key/value pairs in the tree.
	 * 
	 * @return Number of key/value pairs.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * @return the root of the AVL tree
	 */
	public AVLNode getRoot() {
		return this.root;
	}

	/**
	 * Retrieves tree height.
	 * 
	 * @return -1 in case of empty tree, current tree height otherwise.
	 */
	public int height() {
		return this.root == null ? -1 : this.root.height;
	}

	/**
	 * Yields an array representation of the tree (-order).
	 *
	 * @return Array representation of the tree.
	 */
	public Object[] toArray() {
		return this.toArrayPreOrder();
	}

	/**
	 * Inserts a new node into AVL tree.
	 * 
	 * @param key  Key of the new node.
	 * @param elem Data of the new node. May be null. Elements with the same key are
	 *             not allowed. In this case false is returned. Null-Keys are not
	 *             allowed. In this case an exception is thrown.
	 */
	public boolean insert(Integer key, String elem) throws IllegalArgumentException {
		if (key == null || elem == null) {
			throw new IllegalArgumentException();
		}
		if (this.root == null) {
			this.root = new AVLNode(key, elem);
			this.size++;
			return true;
		}
		return this.recursiveInsert(this.root, key, elem);
	}

	private boolean recursiveInsert(AVLNode node, Integer key, String elem) {
		if (node.key.equals(key)) {
			return false;
		}
		AVLNode par = node;
		boolean movLeft = node.key > key;
		node = (movLeft) ? node.left : node.right;

		if (node == null) {
			if (movLeft) {
				par.left = new AVLNode(key, elem);
				par.left.parent = par;
			} else {
				par.right = new AVLNode(key, elem);
				par.right.parent = par;
			}
			this.reBuild(par);
			this.size++;
			return true;
		}
		return this.recursiveInsert(node, key, elem);
	}

	/**
	 * Removes node with given key.
	 * 
	 * @param key Key of node to remove.
	 * @return true If element was found and deleted.
	 */
	public boolean remove(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			return false;
		}
		AVLNode chi = root;
		while (chi != null) {
			AVLNode node = chi;
			chi = (key >= node.key) ? node.right : node.left;
			if (node.key.equals(key)) {
				this.recursiveRemove(node);
				this.size--;
				return true;
			}
		}
		return false;
	}

	private void recursiveRemove(AVLNode node) {
		if (node.left == null && node.right == null) {
			if (this.getParent(node) == null) {
				root = null;
			} else {
				AVLNode par = this.getParent(node);
				if (par.left == node) {
					par.left = null;
				} else {
					par.right = null;
				}
				this.reBuild(par);
			}
			return;
		}
		if (node.left != null) {
			AVLNode chi = node.left;
			while (chi.right != null) {
				chi = chi.right;
			}
			node.key = chi.key;
			node.elem = chi.elem;
			this.recursiveRemove(chi);
		} else {
			AVLNode chi = node.right;
			while (chi.left != null) {
				chi = chi.left;
			}
			node.key = chi.key;
			node.elem = chi.elem;
			this.recursiveRemove(chi);
		}
	}

	/**
	 * Returns value of node with given key.
	 * 
	 * @param key Key to search.
	 * @return Corresponding value if key was found, null otherwise.
	 */

	public String find(Integer key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		String val = this.recursiveFind(this.root, key);
		return val;
	}

	private String recursiveFind(AVLNode node, Integer key) {
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

	private void reBuild(AVLNode node) {
		this.updHeight(node);

		int bal = this.getBalance(node);

		if (bal == -2) {
			if (this.getBalance(node.left) <= 0) {
				node = this.rotRight(node);
			} else {
				node = this.rotLeftRight(node);
			}
		} else if (bal == 2) {
			if (this.getBalance(node.right) >= 0) {
				node = this.rotLeft(node);
			} else {
				node = this.rotRightLeft(node);
			}
		}
		if (node.parent != null) {
			this.reBuild(this.getParent(node));
		} else {
			this.root = node;
		}
	}

	private AVLNode rotLeft(AVLNode node) {
		AVLNode tmp = node.right;
		tmp.parent = this.getParent(node);
		node.right = tmp.left;
		if (node.right != null) {
			node.right.parent = node;
		}
		tmp.left = node;
		node.parent = tmp;

		if (tmp.parent != null) {
			if (tmp.parent.right == node) {
				tmp.parent.right = tmp;
			} else {
				tmp.parent.left = tmp;
			}
		}
		this.updHeight(node);
		this.updHeight(tmp);
		return tmp;
	}

	private AVLNode rotRight(AVLNode node) {
		AVLNode tmp = node.left;
		tmp.parent = this.getParent(node);

		node.left = tmp.right;

		if (node.left != null) {
			node.left.parent = node;
		}
		tmp.right = node;
		node.parent = tmp;

		if (this.getParent(tmp) != null) {
			if (this.getParent(tmp).right == node) {
				this.getParent(tmp).right = tmp;
			} else {
				this.getParent(tmp).left = tmp;
			}
		}
		this.updHeight(node);
		this.updHeight(tmp);
		return tmp;
	}

	private AVLNode rotLeftRight(AVLNode node) {
		node.left = this.rotLeft(node.left);
		return this.rotRight(node);
	}

	private AVLNode rotRightLeft(AVLNode node) {
		node.right = this.rotRight(node.right);
		return this.rotLeft(node);
	}

	private int getHeight(AVLNode node) {
		return (node == null) ? -1 : node.height;
	}

	private void updHeight(AVLNode node) {
		node.height = (node != null) ? 1 + this.getMax(this.getHeight(node.left), this.getHeight(node.right)) : null;
	}

	private int getBalance(AVLNode node) {
		return this.getHeight(node.right) - this.getHeight(node.left);
	}

	private int getMax(int a, int b) {
		return (a > b) ? a : b;
	}

	private AVLNode getParent(AVLNode node) {
		return node.parent;
	}

	// *************************************
	// *********** BST METHODS *************
	// *************************************

	private Object[] toArrayPostOrder() {
		Object[] obj = new Object[size()];
		this.recursivePostOrder(obj, this.root, new Index());
		return obj;
	}

	private Object[] toArrayInOrder() {
		Object[] obj = new Object[size()];
		this.recursiveInOrder(obj, this.root, new Index());
		return obj;
	}

	private Object[] toArrayPreOrder() {
		Object[] obj = new Object[size()];
		this.recursivePreOrder(obj, this.root, new Index());
		return obj;
	}

	private void recursiveInOrder(Object[] obj, AVLNode node, Index index) {
		if (node != null) {
			this.recursiveInOrder(obj, node.left, index);
			obj[index.i++] = node.elem;
			this.recursiveInOrder(obj, node.right, index);
		}
	}

	private void recursivePreOrder(Object[] obj, AVLNode node, Index index) {
		if (node != null) {
			obj[index.i++] = node.elem;
			this.recursivePreOrder(obj, node.left, index);
			this.recursivePreOrder(obj, node.right, index);
		}
	}

	private void recursivePostOrder(Object[] obj, AVLNode node, Index index) {
		if (node != null) {
			this.recursivePostOrder(obj, node.left, index);
			this.recursivePostOrder(obj, node.right, index);
			obj[index.i++] = node.elem;
		}
	}
}