package algodat1.ue06;

public class List {

	protected Node head;

	protected List() {
		this.head = null;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public void add(int val) {
		Node newNode = new Node(val);
		newNode.setNext(head);
		this.head = newNode;
	}
}