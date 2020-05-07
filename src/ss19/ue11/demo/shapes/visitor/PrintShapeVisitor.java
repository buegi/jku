package ss19.ue11.demo.shapes.visitor;

import inout.Out;
import shapes.Circle;
import shapes.Group;
import shapes.Rect;
import shapes.Shape;

public class PrintShapeVisitor implements ShapeVisitor {

	@Override
	public void visit(Circle c) {
		Out.println(String.format("Circle %d/%d radius %d", c.getX(), c.getY(), c.getRadius()));
	}

	@Override
	public void visit(Rect r) {
		Out.println(String.format("Rect pos=%d/%d w/h %d", r.getLeft(), r.getTop(), r.getWidth(), r.getHeight()));
	}

	@Override
	public void visit(Group g) {
		Out.println("Group with subelements -----------------");
		for (Shape s : g.getSubshapes()) {
			s.accept(this);
		}
		Out.println("----------------------------------------");
	}

}
