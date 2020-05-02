package ue07.demo.EqualsSym;

public class Demo {

  public static void main(String[] args) {
    Person p = new Person("Ralf", "Berger", 2020);
    Developer d = new Developer("Ralf", "Berger", 2020, Developer.Expertise.DATABASE);

    boolean pEqualsD = p.equals(d);
    System.out.println(pEqualsD);

    // Problem: do not implement equals asymmetrically

    boolean dEqualsP = d.equals(p);
    System.out.println(dEqualsP);

  }
}
