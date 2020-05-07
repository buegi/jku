package ss19.ue10.demo.patterns.decorator;

/**
 * Decorator class to add different kind of final wishes to a letter.
 */
public class BestWishesDecorator extends LetterDecorator {

	String firstName;
	String lastName;
	boolean formal;

	/**
	 * Constructor.
	 * 
	 * @param letterComponent
	 *            the next letter object, which is either the concrete letter
	 * @param firstName
	 *            the firstName name of the writer
	 * @param lastName
	 *            the lastName name of the writer
	 * @param formal
	 *            indicator if the wishes should contain the lastName name.
	 */
	public BestWishesDecorator(Letter next, String firstName, String lastName, boolean formal) {
		super(next);
		this.firstName = firstName;
		this.lastName = lastName;
		this.formal = formal;
	}

	@Override
	public String getText() {
		StringBuilder b = new StringBuilder();

		b.append(super.getText());
		b.append("\nBest wishes, \n");
		b.append(firstName);
		if (formal) {
			b.append(" ");
			b.append(lastName);
		}
		b.append(".\n");

		return b.toString();
	}
}
