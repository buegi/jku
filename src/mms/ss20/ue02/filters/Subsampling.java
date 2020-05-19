package mms.ss20.ue02.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Properties;

import mms.ss20.ue02.ui.FilterInterface;
import mms.ss20.ue02.pixels.Pixel;

/**
 * Perform sub sampling on the image
 */
public class Subsampling implements FilterInterface {

    @Override
    public Image runFilter(BufferedImage image, Properties settings) {
        int rate = Integer.parseInt(settings.getProperty("rate"));
        BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        /* 		@TODO Place your implementation here		 */

        // iterate image/pixels
        for (int i = 0; i < image.getWidth(); i = i + rate) {
            for (int j = 0; j < image.getHeight(); j = j + rate) {

                // get RGB values
                int red = Pixel.getRed(image.getRGB(i, j));
                int green = Pixel.getGreen(image.getRGB(i, j));
                int blue = Pixel.getBlue(image.getRGB(i, j));

                // set subsampling value
                for (int r = 0; r <= rate && i + r < image.getWidth(); r++) {
                    for (int c = 0; c <= rate && j + c < image.getHeight(); c++) {
                        int alpha = Pixel.getAlpha(image.getRGB(i, j));
                        bi.setRGB(i + r, j + c, Pixel.generateRGBAPixel(red, green, blue, alpha));
                    }
                }
            }
        }
        return bi;
    }

    @Override
    public String[] mandatoryProperties() {
        return new String[]{"rate:n:1-8:2"};
    }

    @Override
    public String toString() {
        return "subsampling";
    }
}