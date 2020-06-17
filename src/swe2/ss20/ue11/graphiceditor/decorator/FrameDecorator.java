package swe2.ss20.ue11.graphiceditor.decorator;

import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;
import swe2.ss20.ue11.graphiceditor.visitor.GraphicObjectVisitor;

import java.awt.*;


public class FrameDecorator extends AbstractDecorator {

    public FrameDecorator(GraphicObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void accept(GraphicObjectVisitor gov) {
        go.accept(gov);
    }
}