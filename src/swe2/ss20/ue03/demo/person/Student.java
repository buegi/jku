package swe2.ss20.ue03.demo.person;

import swe2.inout.Out;

public class Student extends Person {

    public Student(String name) {
        super();
        Out.println("Student constructor");
    }

    @Override
    public void live() {
        Out.println("Student life " + getName().toString());
    }
}