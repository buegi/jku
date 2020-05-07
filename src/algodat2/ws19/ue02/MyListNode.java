package algodat2.ws19.ue02;

public class MyListNode<T> {
	private Long element;
	private MyListNode<Long> next;

	public MyListNode() {
		this.element = null;
		this.next = null;
	}

	public MyListNode(Long element) {
		this.element = element;
		this.next = null;
	}

	public Long getElement() {
		return this.element;
	}

	public void setElement(Long element) {
		this.element = element;
	}

	public MyListNode<Long> getNext() {
		return this.next;
	}

	public void setNext(MyListNode<Long> next) {
		this.next = next;
	}
}