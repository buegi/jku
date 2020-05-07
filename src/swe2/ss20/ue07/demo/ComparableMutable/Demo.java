package swe2.ss20.ue07.demo.ComparableMutable;

import java.util.SortedSet;
import java.util.TreeSet;

public class Demo {

  public static void main(String[] args) {
    // sorted set
    SortedSet<Person> ss = new TreeSet<>();
    ss.add(new Person("Franz", "Maier", 2020));
    ss.add(new Person("Alois", "Mueller", 2020));
    ss.add(new Person("Gustav", "Maier", 2020));
    ss.add(new Person("Berta", "Maier", 2020));
    ss.forEach(p -> System.out.println(p));
    System.out.println();

    // Problem: setting surname will destroy sorting order of TreeSet
    ss.first().surName = "Zimmermann";
    ss.forEach(p -> System.out.println(p));

  }
}
