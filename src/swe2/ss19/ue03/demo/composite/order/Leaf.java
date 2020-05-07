package swe2.ss19.ue03.demo.composite.order;

public class Leaf extends Element {

	private final String value;

	public Leaf(String value) {
		this.value = value;
	}

	@Override
	public void preorder() {
		System.out.println(value);
	}

	@Override
	public void inorder() {
		System.out.println(value);
	}

	@Override
	public void postorder() {
		System.out.println(value);
	}
}
