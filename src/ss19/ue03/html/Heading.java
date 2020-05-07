package ss19.ue03.html;

public class Heading extends TaggedElement {
	private final String text;
	private final int level;

	protected Heading(int level, String text) {
		this.level = level;
		this.text = text;
	}

	protected String getText() {
		return this.text;
	}

	@Override
	protected String getTag() {
		return "h" + this.level;
	}

	@Override
	protected String render() {
		return "<" + this.getTag() + ">" + this.getText() + "</" + this.getTag() + ">\n";
	}
}