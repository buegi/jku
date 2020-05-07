package ss19.ue11.demo.shapes.visitor;

import java.awt.Color;

import inout.Window;
import shapes.Circle;
import shapes.Group;
import shapes.Rect;
import shapes.Shape;

public class DrawShapeVisitor implements ShapeVisitor {

	@Override
	public void visit(Circle c) {
		Window.fillCircle(c.getX(), c.getY(), c.getRadius(), Color.GRAY);
	}

	@Override
	public void visit(Rect r) {
		Window.fillRectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight(), Color.GRAY);
	}

	@Override
	public void visit(Group g) {
		for (Shape s : g.getSubshapes()) {
			s.accept(this);
		}
	}

}