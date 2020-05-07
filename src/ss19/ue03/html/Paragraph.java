package ss19.ue03.html;

public class Paragraph extends ContainerElement {

	protected Paragraph(Element[] elements) {
		super(elements);
	}

	@Override
	protected String getTag() {
		return "p";
	}
}