package mms.ss20.ue02.filters;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Properties;

import mms.ss20.ue02.ui.FilterInterface;
import mms.ss20.ue02.pixels.Pixel;

/**
 * Just to show correct handles in ComboBox
 */
public class SepiaFilter implements FilterInterface {

    @Override
    public Image runFilter(BufferedImage image, Properties settings) {
        /* 		@TODO Place your implementation here		 */
        BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // iterate image/pixels
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                // get RGB values
                int red = Pixel.getRed(image.getRGB(i, j));
                int green = Pixel.getGreen(image.getRGB(i, j));
                int blue = Pixel.getBlue(image.getRGB(i, j));

                // sepia color values from document for exercise page 15
                int sepiaRedPixel = (int) ((red * .393) + (green * .769) + (blue * .189));
                int sepiaGreenPixel = (int) ((red * .349) + (green * .686) + (blue * .168));
                int sepiaBluePixel = (int) ((red * .272) + (green * .534) + (blue * .131));

                int alpha = Pixel.getAlpha(image.getRGB(i, j));
                bi.setRGB(i, j, Pixel.generateRGBAPixel(this.byteRange(sepiaRedPixel), this.byteRange(sepiaGreenPixel), this.byteRange(sepiaBluePixel), alpha));
            }
        }
        return bi;
    }

    @Override
    public String[] mandatoryProperties() {
        return new String[]{};
    }

    @Override
    public String toString() {
        return "Sepia Filter";
    }

    // limit byte range to 255
    private int byteRange(int input) {
        if (input > 255) {
            return 255;
        } else {
            return input;
        }
    }
}