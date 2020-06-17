package swe2.ss20.ue11.graphiceditor.decorator;

import java.awt.Color;

import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;

public abstract class AbstractDecorator extends GraphicObject {

    protected GraphicObject go;

    AbstractDecorator(final GraphicObject go) {
        super(go.getX(), go.getY());
        this.go = go;
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }

    @Override
    public int getX() {
        return go.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        go.setX(x);
    }

    @Override
    public int getY() {
        return go.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        go.setY(y);
    }

    @Override
    public void setColor(Color c) {
        go.setColor(c);
    }

    @Override
    public Color getColor() {
        return go.getColor();
    }
}