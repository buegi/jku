package ue07.demo.TreeMap;

import java.util.SortedMap;
import java.util.TreeMap;

public class Demo {

  public static void main(String[] args) {
    testSortedMap();
    System.out.println();
    testSortedMapWithComparator();
  }

  private static void testSortedMap() {
    System.out.println("----- test sorted map -----");

    SortedMap<Long, Person> sortedMap = new TreeMap<>();
    sortedMap.put(new Long(123403121977L), new Person("Franz", "Maier", 2004));
    sortedMap.put(423101011987L, new Person("Alois", "Mueller", 2003));
    sortedMap.put(111131121978L, new Person("Gustav", "Maier", 1999));
    sortedMap.put(999910101980L, new Person("Berta", "Maier", 2003));

    sortedMap.entrySet().forEach(
        entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
  }

  private static void testSortedMapWithComparator() {
    System.out.println("----- test sorted map with SsnComparator -----");

    SortedMap<Long, Person> sortedMap = new TreeMap<>((ssn1, ssn2) -> {
      // compare years
      int compare = (int) (ssn1.longValue() % 10000 - ssn2 % 10000);
      if (compare != 0) {
        return compare;
      }
      ssn1 = ssn1 / 10000;
      ssn2 = ssn2 / 10000;
      // compare months
      compare = (int) (ssn1 % 100 - ssn2 % 100);
      if (compare != 0) {
        return compare;
      }
      ssn1 = ssn1 / 100;
      ssn2 = ssn2 / 100;
      // compare days
      compare = (int) (ssn1 % 100 - ssn2 % 100);
      if (compare != 0) {
        return compare;
      }
      ssn1 = ssn1 / 100;
      ssn2 = ssn2 / 100;
      // compare nr
      return (int) (ssn1 - ssn2);
    });
    sortedMap.put(123403121977L, new Person("Franz", "Maier", 2004));
    sortedMap.put(423101011987L, new Person("Alois", "Mueller", 2003));
    sortedMap.put(111131121978L, new Person("Gustav", "Maier", 1999));
    sortedMap.put(999910101980L, new Person("Berta", "Maier", 2003));
    sortedMap.entrySet().forEach(
        entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
  }
}
