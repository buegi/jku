package swe2.ss20.ue05.demo.persons;

public class Student extends Person {

    public Student(String name) {
        super(name);
    }

    public void learn() {
        System.out.println("Student " + name + " is learning");
    }
}