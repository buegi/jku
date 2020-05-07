package swe2.ss19.ue06.demo.coll09;

import swe2.inout.Out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Demo9 {

	public static void main(String[] args) {

		Out.println("---- list test --------------");

		// List.add
		Out.println();
		List<Person> plist = new ArrayList<Person>();
		plist.add(new Person("Franz", "Maier", 2004));
		plist.add(new Person("Alois", "Mueller", 2003));
		plist.add(new Person("Gustav", "Maier", 1999));
		plist.add(new Person("Berta", "Maier", 2003));
		plist.forEach(p -> System.out.println(p));
		Out.println();

		// sort according to natural order
		System.out.println("sort with java7:");
		Collections.sort(plist);
		plist.forEach(p -> System.out.println(p));
		Out.println();

		// binary search
		System.out.println("search with java7:");
		int pos = Collections.binarySearch(plist, new Person("Alois", "M�ller",
				2003));
		Out.println(pos);
		Out.println();

		// sort with a comparator
		final SeniorityComparator seniorityComparator = new SeniorityComparator();
		Collections.sort(plist, seniorityComparator::compare);
		plist.forEach(p -> System.out.println(p));
		Out.println();

		// add at position
		plist.add(3, new Person("Michael", "Schmid", 2000));
		Person schmid = plist.get(3);

		// ListIterator
		ListIterator<Person> lit = plist.listIterator();
		while (lit.hasNext()) {
			Person p = lit.next();
			Out.println(p);
		}
		Out.println();
		while (lit.hasPrevious()) {
			Person p = lit.previous();
			Out.println(p);
		}
		Out.println();

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
			Out.println("Add not allowed for unmodifiable collections");
			Out.println();
		}

		// toArray
		Person[] persons = plist.toArray(new Person[0]);
		for (Person p : persons) {
			Out.println(p);
		}
		Out.println();

	}
}
