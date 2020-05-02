package ue07.demo.ComparatorSetListOk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Demo {

  public static void main(String[] args) {
    System.out.println("----- test comparator -----");

    Comparator<Person> personComparator = (p1, p2) -> {
      int compare = p2.hired - p1.hired;
      if (compare == 0) {
        compare = p1.compareTo(p2);
      }
      return compare;
    };

    SortedSet<Person> scss = new TreeSet<>(personComparator);
    scss.add(new Person("Franz", "Maier", 2004));
    scss.add(new Person("Alois", "Mueller", 2003));
    scss.add(new Person("Gustav", "Maier", 1999));
    scss.add(new Person("Berta", "Maier", 2003));
    scss.forEach(p -> System.out.println(p));

    System.out.println("----- test list sort -----");

    List<Person> plist = new ArrayList<>();
    plist.add(new Person("Franz", "Maier", 2004));
    plist.add(new Person("Alois", "Mueller", 2003));
    plist.add(new Person("Gustav", "Maier", 1999));
    plist.add(new Person("Berta", "Maier", 2003));
    Collections.sort(plist, personComparator);
    plist.forEach(p -> System.out.println(p));
  }
}
