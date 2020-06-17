package swe2.ss20.ue11.graphiceditor.visitor;

import java.util.Random;

import swe2.ss20.ue11.graphiceditor.objects.Circle;
import swe2.ss20.ue11.graphiceditor.objects.Rectangle;
import swe2.ss20.ue11.graphiceditor.objects.Star;

public class BackwardVisitor implements GraphicObjectVisitor {

    private static final Random rand = new Random();

    @Override
    public void visit(Rectangle rect) {
        rect.setWidth(rect.getWidth() - rand.nextInt(5));
        rect.setHeight(rect.getHeight() - rand.nextInt(5));
    }

    @Override
    public void visit(Circle circ) {
        circ.setColor(circ.getColor().brighter());
    }

    @Override
    public void visit(Star star) {
        star.setY(star.getY() - rand.nextInt(19));

    }
}