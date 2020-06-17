package swe2.ss20.ue11.graphiceditor.visitor;

import swe2.ss20.ue11.graphiceditor.objects.Circle;
import swe2.ss20.ue11.graphiceditor.objects.Rectangle;
import swe2.ss20.ue11.graphiceditor.objects.Star;

public interface GraphicObjectVisitor {

    void visit(Rectangle rect);

    void visit(Circle circ);

    void visit(Star star);
}
