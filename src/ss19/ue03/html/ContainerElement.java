package ss19.ue03.html;

public abstract class ContainerElement extends TaggedElement {

	protected final Element[] elements;

	protected ContainerElement(Element[] elements) {
		this.elements = elements;
	}

	protected String render() {

		StringBuilder para = new StringBuilder();
		para.append("<" + this.getTag() + ">\n");

		for (Element e : elements) {
			para.append(Globals.indentLines(e.render()));
		}

		para.append("</" + this.getTag() + ">\n");
		return para.toString();
	}
}