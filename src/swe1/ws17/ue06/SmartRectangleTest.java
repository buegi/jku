package swe1.ws17.ue06;

public class SmartRectangleTest {
    public static void main(String[] args) {

        SmartRectangle testrectangle1 = new SmartRectangle();
        testrectangle1.setX(7);
        testrectangle1.setY(-5);
        testrectangle1.setHeight(2);
        testrectangle1.setWidth(1);
        testrectangle1.setName("Test");
        SmartRectangle testrectangle2 = new SmartRectangle();
        testrectangle2.setX(7);
        testrectangle2.setY(-5);
        testrectangle2.setHeight(2);
        testrectangle2.setWidth(1);
        testrectangle2.setName("Test");

        System.out.println("CompareWith: " + testrectangle1.compareWith(testrectangle2));
        System.out.println("OverlapWith: " + testrectangle1.overlapWith(testrectangle2));

        System.out.println("Rectangle Data:");
        System.out.println(
                "Name: " + testrectangle1.getName() + ", X: " + testrectangle1.getX() + ", Y: " + testrectangle1.getY()
                        + ", Height: " + testrectangle1.getHeight() + ", Width: " + testrectangle1.getWidth());

        // test quadrant
        String quadrant = testrectangle1.getQuadrant();
        System.out.println("Quadrant: " + quadrant);

        // test isQuadratic
        System.out.println("Is quadratic: " + testrectangle1.isQuadratic());

        // test move
        System.out.print("Test move: ");
        testrectangle1.move(3, 4);
        System.out.println(
                "Name: " + testrectangle1.getName() + ", X: " + testrectangle1.getX() + ", Y: " + testrectangle1.getY()
                        + ", Height: " + testrectangle1.getHeight() + ", Width: " + testrectangle1.getWidth());
        // test moveTo
        System.out.print("Test moveTo: ");
        testrectangle1.moveTo(2, 2);
        System.out.println(
                "Name: " + testrectangle1.getName() + ", X: " + testrectangle1.getX() + ", Y: " + testrectangle1.getY()
                        + ", Height: " + testrectangle1.getHeight() + ", Width: " + testrectangle1.getWidth());

        // test changeArea
        System.out.print("Test changeArea: ");
        testrectangle1.changeArea(3, 5);
        System.out.println(
                "Name: " + testrectangle1.getName() + ", X: " + testrectangle1.getX() + ", Y: " + testrectangle1.getY()
                        + ", Height: " + testrectangle1.getHeight() + ", Width: " + testrectangle1.getWidth());

        // test getArea
        System.out.print("Test Area: " + testrectangle1.getArea());

    }

}