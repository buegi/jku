package ue03.demo.order;

public class Node extends Element {

	private final Element first, second;
	private final String value;

	public Node(Element first, Element second, String value) {
		this.first = first;
		this.second = second;
		this.value = value;
	}

	@Override
	public void preorder() {
		System.out.println(value);
		if (first != null)
			first.preorder();
		if (second != null)
			second.preorder();
	}

	@Override
	public void inorder() {
		if (first != null)
			first.inorder();
		System.out.println(value);
		if (second != null)
			second.inorder();
	}

	@Override
	public void postorder() {
		if (first != null)
			first.postorder();
		if (second != null)
			second.postorder();
		System.out.println(value);
	}

}
