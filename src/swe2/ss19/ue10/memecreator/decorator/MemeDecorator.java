package swe2.ss19.ue10.memecreator.decorator;

import java.awt.image.BufferedImage;
import swe2.ss19.ue10.memecreator.model.Meme;

public abstract class MemeDecorator implements Meme {

	private final Meme meme;

	public MemeDecorator(Meme meme) {
		this.meme = meme;
	}

	protected Meme getMeme() {
		return meme;
	}

	@Override
	public String getName() {
		return meme.getName();
	}

	@Override
	public BufferedImage getImage() {
		return meme.getImage();
	}
}