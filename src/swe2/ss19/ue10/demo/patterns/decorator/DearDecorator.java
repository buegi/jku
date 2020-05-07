package swe2.ss19.ue10.demo.patterns.decorator;

/**
 * Decorator class to add a formal salutation to a letter
 */
public class DearDecorator extends LetterDecorator {
	private String name;
	private String title;

	/**
	 * Constructor.
	 * 
	 * @param letterComponent
	 *            the next letter object, which is either the concrete letter
	 * @param title
	 *            the title for the addressee.
	 * @param name
	 *            the name of the addressee.
	 */
	public DearDecorator(Letter next, String title, String name) {
		super(next);
		this.title = title;
		this.name = name;
	}

	@Override
	public String getText() {
		return "Dear " + title + " " + name + "!\n\n" + super.getText();
	}
}
