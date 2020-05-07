package ss19.ue03.html;

public class Text extends Element {
	private final String text;

	protected Text(String text) {
		this.text = text;
	}

	protected String getText() {
		return this.text;
	}

	@Override
	protected String render() {
		return this.getText() + "\n";
	}
}