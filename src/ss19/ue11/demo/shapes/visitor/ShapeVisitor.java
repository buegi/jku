package ss19.ue11.demo.shapes.visitor;

import ss19.ue11.demo.shapes.Circle;
import ss19.ue11.demo.shapes.Group;
import ss19.ue11.demo.shapes.Rect;

public interface ShapeVisitor {
	
	public void visit(Circle c); 
	public void visit(Rect c); 
	public void visit(Group g); 

}
