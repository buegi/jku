package swe2.ss20.ue11.demo.patterns.decorator;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Decorator class to add a date to a letter.
 */
public class DateDecorator extends LetterDecorator {

    /**
     * Constructor.
     * <p>
     * // @param letterComponent the next letter object, which is either the concrete letter or the next decorator.
     */
    public DateDecorator(Letter component) {
        super(component);
    }

    @Override
    public String getText() {
        StringBuilder b = new StringBuilder();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
        b.append("                  Linz, ");
        b.append(df.format(new Date()));
        b.append("\n");
        b.append(super.getText());

        return b.toString();
    }
}