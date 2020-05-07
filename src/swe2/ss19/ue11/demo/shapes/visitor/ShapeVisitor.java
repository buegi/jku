package swe2.ss19.ue11.demo.shapes.visitor;

import swe2.ss19.ue11.demo.shapes.Circle;
import swe2.ss19.ue11.demo.shapes.Group;
import swe2.ss19.ue11.demo.shapes.Rect;

public interface ShapeVisitor {
	
	public void visit(Circle c); 
	public void visit(Rect c); 
	public void visit(Group g); 

}
