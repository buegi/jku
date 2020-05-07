package swe2.ss20.ue07.demo.ListCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class Demo {

  public static void main(String[] args) {

    System.out.println("----- list test -----");

    List<Person> plist = new ArrayList<>();
    plist.add(new Person("Franz", "Maier", 2004));
    plist.add(new Person("Alois", "Mueller", 2003));
    plist.add(new Person("Gustav", "Maier", 1999));
    plist.add(new Person("Berta", "Maier", 2003));
    plist.forEach(p -> System.out.println(p));
    System.out.println();

    // sort according to natural order
    System.out.println("sort with java7:");
    Collections.sort(plist);
    plist.forEach(p -> System.out.println(p));
    System.out.println();

    // binary search
    System.out.println("search with java7:");
    int pos = Collections.binarySearch(plist, new Person("Alois", "Mueller",
        2003));
    System.out.println(pos);
    System.out.println();

    // sort with a comparator
    final SeniorityComparator seniorityComparator = new SeniorityComparator();
    Collections.sort(plist, seniorityComparator::compare);
    plist.forEach(p -> System.out.println(p));
    System.out.println();

    // add at position
    plist.add(3, new Person("Michael", "Schmid", 2000));
    Person schmid = plist.get(3);

    // ListIterator
    ListIterator<Person> lit = plist.listIterator();
    while (lit.hasNext()) {
      Person p = lit.next();
      System.out.println(p);
    }
    System.out.println();
    while (lit.hasPrevious()) {
      Person p = lit.previous();
      System.out.println(p);
    }
    System.out.println();

    // remove
    plist.remove(3);
    boolean schmidContained = plist.contains(schmid);
    System.out.println(schmidContained);
    int bertaIdx = plist.indexOf(new Person("Berta", "Maier", 2003));
    System.out.println(bertaIdx);
    System.out.println();

    // unmodifiable list
    List<Person> unmodifiable = Collections.unmodifiableList(plist);

    try {
      unmodifiable.add(new Person("Xaver", "Muster", 2000));
    } catch (UnsupportedOperationException excpt) {
      System.out.println("Add not allowed for unmodifiable collections");
      System.out.println();
    }

    // toArray
    Person[] persons = plist.toArray(new Person[0]);
    for (Person p : persons) {
      System.out.println(p);
    }
  }

  private static class SeniorityComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      int compare = Integer.compare(p1.hired, p2.hired);
      if (compare == 0) {
        compare = p1.compareTo(p2);
      }
      return compare;
    }
  }
}
