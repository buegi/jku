package swe2.ss20.ue11.demo.patterns.decorator;

/**
 * Decorator class to add different kind of final wishes to a letter.
 */
public class YoursSincerelyDecorator extends LetterDecorator {
    private String firstName;
    private String lastName;

    /**
     * Constructor.
     * <p>
     * // @param letterComponent the next letter object, which is either the concrete letter
     *
     * @param firstName the firstName name of the writer
     * @param lastName  the lastName name of the writer
     */
    public YoursSincerelyDecorator(Letter component, String firstName, String lastName) {
        super(component);
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