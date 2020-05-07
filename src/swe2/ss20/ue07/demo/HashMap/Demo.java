package swe2.ss20.ue07.demo.HashMap;

import java.util.HashMap;
import java.util.Map;

public class Demo {

  public static void main(String[] args) {
    System.out.println("----- test hash map -----");

    Map<Long, Person> pmap = new HashMap<>();
    pmap.put(new Long(123403121977L), new Person("Franz", "Maier", 2004));
    pmap.put(new Long(423101011987L), new Person("Alois", "Mueller", 2003));
    pmap.put(111131121978L, new Person("Gustav", "Maier", 1999));
    pmap.put(999910101980L, new Person("Berta", "Maier", 2003));

    System.out.println(pmap.get(123403121977L));
    System.out.println();

    pmap.keySet().forEach(ssn -> System.out.println(pmap.get(ssn)));
    System.out.println();

    pmap.values().forEach(p -> System.out.println(p));
    System.out.println();

    pmap.entrySet().forEach(
        entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
  }
}
