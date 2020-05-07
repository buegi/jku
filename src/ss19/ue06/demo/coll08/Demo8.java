package ss19.ue06.demo.coll08;

import inout.Out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Demo8 {

	public static void main(String[] args) {

		Out.println("---- test comparator --------------");

		SortedSet<Person> scss = new TreeSet<Person>((p1, p2) -> {
			int compare = p2.hired - p1.hired;
			if (compare == 0) {
				compare = p1.compareTo(p2);
			}
			return compare;
		});

		scss.add(new Person("Franz", "Maier", 2004));
		scss.add(new Person("Alois", "Mueller", 2003));
		scss.add(new Person("Gustav", "Maier", 1999));
		scss.add(new Person("Berta", "Maier", 2003));
		scss.forEach(p -> System.out.println(p));

		Out.println("---- test list sort --------------");

		List<Person> plist = new ArrayList<Person>();

		plist.add(new Person("Franz", "Maier", 2004));
		plist.add(new Person("Alois", "Mueller", 2003));
		plist.add(new Person("Gustav", "Maier", 1999));
		plist.add(new Person("Berta", "Maier", 2003));
		Collections.sort(plist, (p1, p2) -> {
			int compare = p2.hired - p1.hired;
			if (compare == 0) {
				compare = p1.compareTo(p2);
			}
			return compare;
		});
		plist.forEach(p -> System.out.println(p));
		Out.println();

	}
}
