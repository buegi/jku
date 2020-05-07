package ss19.ue10.memecreator.factory;

import java.awt.Color;

import memecreator.decorator.TextDecorator;
import memecreator.model.Meme;

public class WhiteTextDecoratorFactory implements TextDecoratorFactory {

	private static WhiteTextDecoratorFactory instance;

	public static WhiteTextDecoratorFactory getInstance() {
		if (instance == null) {
			instance = new WhiteTextDecoratorFactory();
		}
		return instance;
	}

	@Override
	public Meme createTextDecorator(Meme meme, String text, boolean top) {
		return new TextDecorator(meme, text, top, 0, 0, Color.WHITE);
	}

	@Override
	public Meme createShadowTextDecorator(Meme meme, String text, boolean top) {
		Meme bTextMeme = new TextDecorator(meme, text, top, 2, 2, Color.BLACK);
		Meme sTextMeme = createTextDecorator(bTextMeme, text, top);
		return sTextMeme;
	}
}