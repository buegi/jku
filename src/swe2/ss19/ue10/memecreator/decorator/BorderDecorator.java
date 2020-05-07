package swe2.ss19.ue10.memecreator.decorator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import swe2.ss19.ue10.memecreator.model.Meme;

public class BorderDecorator extends MemeDecorator {

	private int thickness;
	private Color color;

	public BorderDecorator(Meme next, int thickness, Color color) {
		super(next);
		this.thickness = thickness;
		this.color = color;
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		// TODO complete method
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(thickness));
		g2.drawRect(0, 0, width, height);
		g2.setColor(color);
		super.getMeme().paint(g2, width, height);
	}
}