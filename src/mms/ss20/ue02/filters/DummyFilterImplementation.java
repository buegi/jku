package mms.ss20.ue02.filters;

import java.awt.image.BufferedImage;
import java.util.Properties;

import mms.ss20.ue02.ui.FilterInterface;

/**
 * Just to show correct handles in ComboBox
 */
public class DummyFilterImplementation implements FilterInterface {

    @Override
    public BufferedImage runFilter(BufferedImage img, Properties settings) {
        throw new IllegalStateException("Dummy Filter not implemented");
    }

    @Override
    public String[] mandatoryProperties() {
        throw new IllegalStateException("Dummy Filter not implemented");
    }

    @Override
    public String toString() {
        return "<select a filter>";
    }
}