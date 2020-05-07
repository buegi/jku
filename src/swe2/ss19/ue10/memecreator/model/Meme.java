package swe2.ss19.ue10.memecreator.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Meme {

	String getName();

	BufferedImage getImage();

	void paint(Graphics g, int width, int height);
}