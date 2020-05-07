package ss19.ue11.demo.shapes.visitor;

import shapes.Circle;
import shapes.Group;
import shapes.Rect;

public interface ShapeVisitor {
	
	public void visit(Circle c); 
	public void visit(Rect c); 
	public void visit(Group g); 

}
