package swe2.ss19.ue10.demo.patterns.decorator;

/**
 * Decorator class to add different kind of final wishes to a letter.
 */
public class YoursSincerelyDecorator extends LetterDecorator {
	private String firstName;
	private String lastName;

	/**
	 * Constructor.
	 * 
	 * @param letterComponent
	 *            the next letter object, which is either the concrete letter
	 * @param firstName
	 *            the firstName name of the writer
	 * @param lastName
	 *            the lastName name of the writer
	 */
	public YoursSincerelyDecorator(Letter next, String firstName, String lastName) {
		super(next);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String getText() {
		StringBuilder b = new StringBuilder();

		b.append(super.getText());
		b.append("\nYours sincerely, \n");
		b.append(firstName);
		b.append(" ");
		b.append(lastName);
		b.append(".\n");

		return b.toString();
	}
}
