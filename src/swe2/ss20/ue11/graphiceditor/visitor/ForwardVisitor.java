package swe2.ss20.ue11.graphiceditor.visitor;

import java.util.Random;


import swe2.ss20.ue11.graphiceditor.objects.Circle;
import swe2.ss20.ue11.graphiceditor.objects.Rectangle;
import swe2.ss20.ue11.graphiceditor.objects.Star;

public class ForwardVisitor implements GraphicObjectVisitor {

    private static final Random rand = new Random();

    @Override
    public void visit(Rectangle rectangle) {
        rectangle.setWidth(rectangle.getWidth() + rand.nextInt(5));
        rectangle.setHeight(rectangle.getHeight() + rand.nextInt(5));
    }

    @Override
    public void visit(Circle circle) {
        circle.setColor(circle.getColor().darker());
    }

    @Override
    public void visit(Star star) {
        star.setY(star.getY() + rand.nextInt(19));
    }
}