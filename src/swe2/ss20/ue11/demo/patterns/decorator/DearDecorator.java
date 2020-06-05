package swe2.ss20.ue11.demo.patterns.decorator;

/**
 * Decorator class to add a formal salutation to a letter
 */
public class DearDecorator extends LetterDecorator {
    private String name;
    private String title;

    /**
     * Constructor.
     * <p>
     * // @param letterComponent the next letter object, which is either the concrete letter
     *
     * @param title the title for the addressee.
     * @param name  the name of the addressee.
     */
    public DearDecorator(Letter component, String title, String name) {
        super(component);
        this.title = title;
        this.name = name;
    }

    @Override
    public String getText() {
        return "Dear " + title + " " + name + "!\n\n" + super.getText();
    }
}