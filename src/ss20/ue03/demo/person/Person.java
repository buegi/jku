package ss20.ue03.demo.person;

import inout.Out;

public class Person {

    String name;

    public Person() {
        this("");
    }

    public Person(String name) {
        super();
        Out.println("Person constructor");
        // DON'T DO THAT: live();
        // NEVER CALL DYNAMICALLY BOUND METHOD IN CONSTRUCTOR
        this.name = name;
    }

    public void live() {
        Out.println("Person life ");
    }

    public String getName() {
        return name;
    }
}