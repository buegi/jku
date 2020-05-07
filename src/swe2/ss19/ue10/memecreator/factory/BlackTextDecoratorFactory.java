package swe2.ss19.ue10.memecreator.factory;

import java.awt.Color;

import swe2.ss19.ue10.memecreator.decorator.TextDecorator;
import swe2.ss19.ue10.memecreator.model.Meme;

public enum BlackTextDecoratorFactory implements TextDecoratorFactory {
	INSTANCE;

	@Override
	public Meme createTextDecorator(Meme meme, String text, boolean top) {
		return new TextDecorator(meme, text, top, 0, 0, Color.BLACK);
	}

	@Override
	public Meme createShadowTextDecorator(Meme meme, String text, boolean top) {
		Meme wTextMeme = new TextDecorator(meme, text, top, 2, 2, Color.WHITE);
		Meme sTextMeme = createTextDecorator(wTextMeme, text, top);
		return sTextMeme;
	}
}