package ss19.ue11.demo.shapes.visitor;

import shapes.Circle;
import shapes.Group;
import shapes.Rect;
import shapes.Shape;

public class AreaShapeVisitor implements ShapeVisitor {
	
	private double area = 0.0;

	public double getArea() {
		return area;
	}

	@Override
	public void visit(Circle c) {
		area += c.getRadius() * c.getRadius() * Math.PI; 
	}

	@Override
	public void visit(Rect r) {
		area += r.getWidth() * r.getHeight(); 
	}

	@Override
	public void visit(Group g) {
		for (Shape s : g.getSubshapes()) {
			s.accept(this);
		}
	}

}
