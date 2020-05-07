package swe2.ss19.ue06.demo.coll11;

import swe2.inout.Out;

import java.util.SortedMap;
import java.util.TreeMap;

public class Demo11 {

    public static void main(String[] args) {
        testSortedMap();
        testSortedMapWithComparator();
    }

    private static void testSortedMap() {
        Out.println("---- test sorted map --------------");

        SortedMap<Long, Person> sortedMap = new TreeMap<Long, Person>();
        sortedMap.put(Long.valueOf(123403121977L), new Person("Franz", "Maier",
                2004));
        sortedMap.put(423101011987L, new Person("Alois", "Mueller", 2003));
        sortedMap.put(111131121978L, new Person("Gustav", "Maier", 1999));
        sortedMap.put(999910101980L, new Person("Berta", "Maier", 2003));

        sortedMap.entrySet().forEach(
                entry -> Out.println(entry.getKey() + ": " + entry.getValue()));
        Out.println();
    }

    private static void testSortedMapWithComparator() {
        Out.println("---- test sorted map with SsnComparator -");

        SortedMap<Long, Person> sortedMap = new TreeMap<Long, Person>((ssn1,
                                                                       ssn2) -> {
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
                entry -> Out.println(entry.getKey() + ": " + entry.getValue()));
        Out.println();
    }
}
