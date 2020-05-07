package swe2.ss20.ue04.demo.shapes1;

import swe2.inout.Window;

/**
 * A text which is drawable 
 */
public class Text implements Drawable {
	
	/** the string of the drawable text */
	private final String text; 
	
	/** 
	 * Constructor initializing the text string
	 * @param text the text stirng
	 */
	public Text(String text) {
		super();
		this.text = text;
	}

	/**
	 * Gets the text string
	 * @return the text string
	 */
	public String getText() {
		return text;
	}

	/**
	 * Draws this drawable by drawing the text on Window at 
	 * position 20/20. 
	 */
	@Override
	public void draw() {
		Window.drawText(text, 20, 20);	
	}

}
