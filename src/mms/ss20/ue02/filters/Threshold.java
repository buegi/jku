package mms.ss20.ue02.filters;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.util.Properties;

import mms.ss20.ue02.ui.FilterInterface;
import mms.ss20.ue02.pixels.Pixel;

/**
 * Filter that implements image thresholding
 */
public class Threshold implements FilterInterface {

    @Override
    public Image runFilter(BufferedImage image, Properties settings) {
        int threshold = Integer.parseInt(settings.getProperty("threshold"));
        /* 		@TODO Place your implementation here		 */
        BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // iterate image/pixels
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                // get RGB values
                int red = Pixel.getRed(image.getRGB(i, j));
                int green = Pixel.getGreen(image.getRGB(i, j));
                int blue = Pixel.getBlue(image.getRGB(i, j));
                int gScale = (red + green + blue) / 3;

                // set threshold value
                if (threshold > gScale) {
                    int alpha = Pixel.getAlpha(image.getRGB(i, j));
                    bi.setRGB(i, j, Pixel.generateRGBAPixel(0, 0, 0, alpha));
                } else {
                    int alpha = Pixel.getAlpha(image.getRGB(i, j));
                    bi.setRGB(i, j, Pixel.generateRGBAPixel(255, 255, 255, alpha));
                }
            }
        }
        return bi;
    }

    @Override
    public String[] mandatoryProperties() {
        return new String[]{"threshold:n:0-255:128"};
    }

    @Override
    public String toString() {
        return "Threshold Filter";
    }
}