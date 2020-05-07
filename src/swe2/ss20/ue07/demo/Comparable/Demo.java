package swe2.ss20.ue07.demo.Comparable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
    ss.forEach(System.out::println);
    System.out.println();

    List<Person> persons = new LinkedList<>();
    persons.addAll(ss);
    Collections.sort(persons,
        (p1, p2) -> -p1.firstName.compareTo(p2.firstName));
    persons.forEach(p -> System.out.println(p));

  }
}
