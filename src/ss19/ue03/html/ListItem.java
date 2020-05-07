package ss19.ue03.html;

public class ListItem extends ContainerElement {

	protected ListItem(Element[] elements) {
		super(elements);
	}

	@Override
	protected String getTag() {
		return "li";
	}
}