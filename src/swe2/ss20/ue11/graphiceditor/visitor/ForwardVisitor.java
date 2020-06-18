package swe2.ss20.ue11.graphiceditor.visitor;

import swe2.ss20.ue11.graphiceditor.objects.Circle;
import swe2.ss20.ue11.graphiceditor.objects.Rectangle;
import swe2.ss20.ue11.graphiceditor.objects.Star;

public class ForwardVisitor implements GraphicObjectVisitor {

    @Override
    public void visit(Rectangle rectangle) {
        rectangle.setY(rectangle.getY() + 4);
    }

    @Override
    public void visit(Circle circle) {
        circle.setX(circle.getX() - 4);
    }

    @Override
    public void visit(Star star) {
        star.setColor(star.getColor().darker());
        star.setX(star.getX() + 4);
    }
}