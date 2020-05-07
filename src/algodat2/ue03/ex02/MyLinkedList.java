package algodat2.ue03.ex02;

public class MyLinkedList {
	public MyNode head;
	public MyNode tail;

	public MyLinkedList() {
		head = null;
		tail = null;
	}

	public void clear() {
		head = null;
		tail = null;
	}

	public void add(Integer val) {
		if (head == null) {
			head = new MyNode(val);
			tail = head;
		} else {
			MyNode tmp = new MyNode(val);
			tail.next = tmp;
			tail = tail.next;
			tail.next = null;
		}
	}

	public void add(MyNode node) {
		if (head == null) {
			head = node;
			tail = head;
			node.next = null;
		} else {
			tail.next = node;
			tail = tail.next;
			tail.next = null;
		}
	}

	public void link(MyLinkedList list) {
		// TODO

		if (list == null || list.head == null) {
			return;
		}

		if (head == null) {
			head = list.head;
			tail = list.tail;
		}

		if (head != null) {
			tail.next = list.head;
			tail = list.tail;
		}
	}
}