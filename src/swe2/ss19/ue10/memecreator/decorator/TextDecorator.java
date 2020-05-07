package swe2.ss19.ue10.memecreator.decorator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import swe2.ss19.ue10.memecreator.model.Meme;

public class TextDecorator extends MemeDecorator {

    private String text;
    private boolean top;
    private int xShift;
    private int yShift;
    private Color color;

    public TextDecorator(Meme meme, String text, boolean top, int xShift, int yShift, Color color) {
        super(meme);
        this.text = text;
        this.top = top;
        this.xShift = xShift;
        this.yShift = yShift;
        this.color = color;
    }

    @Override
    public void paint(Graphics g, int width, int height) {
        Font font = new Font("Arial", Font.BOLD, 20);
        int txtLen = g.getFontMetrics(font).stringWidth(text);
        int x = (width - txtLen) / 2;
        int y;
        if (top) {
            y = g.getFontMetrics(font).getHeight() + 10;
        } else {
            y = height - g.getFontMetrics(font).getHeight();
        }
        super.getMeme().paint(g, width, height);
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x + xShift, y + yShift);
    }
}