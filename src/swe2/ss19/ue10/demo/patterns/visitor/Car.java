package swe2.ss19.ue10.demo.patterns.visitor;

class Wheel {
	
    private String name;
    
    Wheel(String name) {
        this.name = name;
    }
    
    String getName() {
        return this.name;
    }
    
    void accept(Visitor visitor) {
        visitor.visitWheel(this);
    }
    
}

class Engine {
	
    void accept(Visitor visitor) {
        visitor.visitEngine(this);
    }
    
}

class Body {
	
    void accept(Visitor visitor) {
        visitor.visitBody(this);
    }
    
}

public class Car {
	
    Engine  engine = new Engine();
    Body    body   = new Body();
    Wheel[] wheels 
        = { new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left") , new Wheel("back right")  };
    
    void accept(Visitor visitor) {
        visitor.visitCar(this);
    }
}
