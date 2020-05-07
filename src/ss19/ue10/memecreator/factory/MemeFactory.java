package ss19.ue10.memecreator.factory;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import memecreator.model.Meme;
import memecreator.model.MemeImpl;

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