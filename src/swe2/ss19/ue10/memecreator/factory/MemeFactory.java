package swe2.ss19.ue10.memecreator.factory;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import swe2.ss19.ue10.memecreator.model.Meme;
import swe2.ss19.ue10.memecreator.model.MemeImpl;

public class MemeFactory {

	private MemeFactory() {
	}

	public static Meme createMemeFromFile(final File file) {
		try {
			return new MemeImpl(file.getName(), ImageIO.read(file));
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}