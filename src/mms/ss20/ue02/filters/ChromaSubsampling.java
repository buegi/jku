package mms.ss20.ue02.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Properties;

import mms.ss20.ue02.ui.FilterInterface;
import mms.ss20.ue02.pixels.Pixel;

/**
 * Apply sub sampling of color values only
 */
public class ChromaSubsampling implements FilterInterface {

    @Override
    public Image runFilter(BufferedImage image, Properties settings) {
        int horizontal = Integer.parseInt(settings.getProperty("horizontal"));
        int vertical = Integer.parseInt(settings.getProperty("vertical"));

        BufferedImage subsampled = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // iterate through image/pixels
        for (int i = 0; i < image.getWidth(); i = i + horizontal) {
            for (int j = 0; j < image.getHeight(); j = j + vertical) {
                // Read Cb and Cr values
                Pixel pix = new Pixel(image.getRGB(i, j));
                int cb = pix.getCb();
                int cr = pix.getCr();

                // set chroma subsampling values
                for (int r = 0; r <= horizontal && i + r < image.getWidth(); r++) {
                    for (int c = 0; c <= vertical && j + c < image.getHeight(); c++) {
                        Pixel subPix = new Pixel(image.getRGB(i + r, j + c));
                        int Y = subPix.getY();
                        subPix = new Pixel(Y, cb, cr);
                        subsampled.setRGB(i + r, j + c, subPix.getRawRGBA());
                    }
                }
            }
        }
        return subsampled;
    }

    @Override
    public String[] mandatoryProperties() {
        return new String[]{"horizontal:s:1-8:2", "vertical:n:1-8:2"};
    }

    @Override
    public String toString() {
        return "subsampling - chroma";
    }

}
