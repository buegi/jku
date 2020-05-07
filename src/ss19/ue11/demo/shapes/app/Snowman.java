package ss19.ue11.demo.shapes.app;

import inout.Out;
import shapes.Circle;
import shapes.Group;
import shapes.Rect;
import shapes.Shape;
import shapes.visitor.AreaShapeVisitor;
import shapes.visitor.DrawShapeVisitor;
import shapes.visitor.PrintShapeVisitor;

/**
 * Test class constructing a snowman as composite object
 */
public class Snowman {

	public static void main(String args[]) {

		Shape hut1 = new Rect(130, 120, 40, 10);
		Shape hut2 = new Rect(140, 90, 20, 30);
		Group hut = new Group(hut1, hut2);

		Rect beinL = new Rect(100, 300, 45, 100);
		Rect beinR = new Rect(155, 300, 45, 100);
		Rect armL = new Rect(60, 210, 40, 30);
		Rect armR = new Rect(200, 210, 40, 30);
		Circle body = new Circle(150, 250, 60);
		Circle kopf = new Circle(150, 160, 30);

		Group snowMan = new Group(hut, armL, armR, beinR, beinL, kopf, body);

		snowMan.accept(new DrawShapeVisitor());
		
		snowMan.accept(new PrintShapeVisitor());
		
		AreaShapeVisitor areaV = new AreaShapeVisitor();
		snowMan.accept(areaV);
		
		Out.println("Area of snowman = " + areaV.getArea());
		
	}
}