package swe2.ss20.ue07.demo.ComparatorSetList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Demo {

  public static void main(String[] args) {
    System.out.println("----- test comparator -----");

    SortedSet<Person> scss = new TreeSet<>((p1, p2) -> p2.hired - p1.hired);
    scss.add(new Person("Franz", "Maier", 2004));
    scss.add(new Person("Alois", "Mueller", 2003));
    scss.add(new Person("Gustav", "Maier", 1999));
    scss.add(new Person("Berta", "Maier", 2003));
    scss.forEach(p -> System.out.println(p));

    // Problem: What's with Berta?

    System.out.println("----- test list sort -----");

    List<Person> plist = new ArrayList<>();
    plist.add(new Person("Franz", "Maier", 2004));
    plist.add(new Person("Alois", "Mueller", 2003));
    plist.add(new Person("Gustav", "Maier", 1999));
    plist.add(new Person("Berta", "Maier", 2003));
    System.out.println("unsorted:");
    plist.forEach(p -> System.out.println(p));
    System.out.println();

    Collections.sort(plist, (p1, p2) -> p2.hired - p1.hired);
    System.out.println("sorted:");
    plist.forEach(p -> System.out.println(p));
  }
}
