package ss19.ue03.html;

public class List extends ContainerElement {

	private final boolean ordered;

	protected List(boolean ordered, ListItem[] elements) {
		super(elements);
		this.ordered = ordered;
	}

	@Override
	protected String getTag() {
		if (this.ordered) {
			return "ol";
		} else {
			return "ul";
		}
	}
}