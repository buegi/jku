package swe2.ss19.ue10.demo.patterns.visitor;

import java.awt.Color;

import swe2.inout.Window;

interface Visitor {
    void visitWheel(Wheel wheel);
    void visitEngine(Engine engine);
    void visitBody(Body body);
    void visitCar(Car car);
}

class PrintVisitor implements Visitor {
	
    public void visitWheel(Wheel wheel) {
        System.out.println("Visiting "+wheel.getName()
                            +" wheel");
    }
    
    public void visitEngine(Engine engine) {
        System.out.println("Visiting engine");
    }
    public void visitBody(Body body) {
        System.out.println("Visiting body");
    }
    public void visitCar(Car car) {
        System.out.println("Visiting car");
        car.body.accept( this );
        for(int i=0; i< car.wheels.length; ++i)
            car.wheels[i].accept( this );
        car.engine.accept( this );

    }
}

class DrawVisitor implements Visitor {
	
    public void visitWheel(Wheel wheel) {
    	if (wheel.getName().equals("front right") ||
    			wheel.getName().equals("front left")) {
    		Window.fillCircle(80, 140, 20, Color.LIGHT_GRAY); 
    		Window.drawCircle(80, 140, 20, Color.DARK_GRAY); 
    	} else {
      		Window.fillCircle(200, 140, 20, Color.LIGHT_GRAY); 
      		Window.drawCircle(200, 140, 20, Color.DARK_GRAY);       	    		
    	}
    }
    public void visitEngine(Engine engine) {
    	Window.fillRectangle(60, 110, 40, 20, Color.DARK_GRAY);     	
    }
    public void visitBody(Body body) {
    	Window.fillRectangle(50, 100, 200, 40, Color.LIGHT_GRAY); 
    	Window.fillRectangle(100, 60, 100, 40, Color.LIGHT_GRAY);     	
    }
    public void visitCar(Car car) {
    	Window.open(); 
        car.body.accept( this );
        for(int i=0; i< car.wheels.length; ++i)
            car.wheels[i].accept( this );
        car.engine.accept( this );    	
    }
}

public class VisitorDemo {
    static public void main(String[] args){
        Car car = new Car();
        
        Visitor printVisitor = new PrintVisitor();
        car.accept(printVisitor);
        
        Visitor drawVisitor = new DrawVisitor(); 
        car.accept(drawVisitor); 
    }
}

