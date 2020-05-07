package ss19.ue03.html;

public class LineBreak extends TaggedElement {

	protected LineBreak() {
	}

	@Override
	protected String getTag() {
		return "br";
	}

	@Override
	protected final String render() {
		return "<" + this.getTag() + "></" + this.getTag() + ">\n";
	}
}