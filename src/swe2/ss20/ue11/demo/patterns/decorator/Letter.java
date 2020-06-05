package swe2.ss20.ue11.demo.patterns.decorator;

/**
 * Abstract base class for the letterComponent and the decorators
 */
public interface Letter {

    /**
     * Gets the letter text.
     *
     * @return the text of the letter.
     */
    String getText();
}