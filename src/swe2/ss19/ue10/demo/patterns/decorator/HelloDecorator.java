package swe2.ss19.ue10.demo.patterns.decorator;

/**
 * Decorator class to add an informal salutation to a letter.
 */
public class HelloDecorator extends LetterDecorator {

	private String name;

	/**
	 * Constructor.
	 * 
	 * @param letterComponent
	 *            the next letter object, which is either the concrete letter or next the next decorator.
	 * @param name
	 *            the name of the addressee.
	 */
	public HelloDecorator(Letter next, String name) {
		super(next);
		this.name = name;
	}

	@Override
	public String getText() {
		return "Hello " + name + "!\n\n" + super.getText();
	}
}
