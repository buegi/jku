package swe2.ss20.ue05.demo.persons;

public class Person implements Comparable<Person> {

    public final String name;

    public Person(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }

    @Override
    public int compareTo(Person p) {
        return name.compareToIgnoreCase(p.name);
    }
}