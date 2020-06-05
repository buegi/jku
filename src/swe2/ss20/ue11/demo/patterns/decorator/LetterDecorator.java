package swe2.ss20.ue11.demo.patterns.decorator;

/**
 * Base class for all decorators of letters.
 */
abstract public class LetterDecorator implements Letter {

    private final Letter next;

    /**
     * Constructor.
     *
     * @param next the next letter object, which is either the concrete letter or the next decorator.
     */
    public LetterDecorator(Letter next) {
        this.next = next;
    }

    @Override
    public String getText() {
        return next.getText();
    }
}