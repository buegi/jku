package ue03.demo.shapes.app;

import inout.Out;
import ue03.demo.shapes.Circle;
import ue03.demo.shapes.Group;
import ue03.demo.shapes.Rectangle;
import ue03.demo.shapes.Shape;

/**
 * Test class constructing a snowman as composite object
 */
public class Snowman {

    public static void main(String args[]) {

        Shape hat1 = new Rectangle(130, 120, 40, 10);
        Shape hat2 = new Rectangle(140, 90, 20, 30);
        Group hat = new Group(hat1, hat2);

        Rectangle legL = new Rectangle(100, 300, 45, 100);
        Rectangle legR = new Rectangle(155, 300, 45, 100);
        Rectangle armL = new Rectangle(60, 210, 40, 30);
        Rectangle armR = new Rectangle(200, 210, 40, 30);
        Circle body = new Circle(150, 250, 60);
        Circle head = new Circle(150, 160, 30);

        Group snowMan = new Group(hat, armL, armR, legR, legL, head, body);

        snowMan.draw();

        Out.println(snowMan.getLeft());
    }
}