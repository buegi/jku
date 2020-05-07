package ss19.ue06.demo.coll05;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Demo5 {

	public static void main(String[] args) {

		// sorted set
		SortedSet<Person> ss = new TreeSet<Person>();
		ss.add(new Person("Franz", "Maier", 20));
		ss.add(new Person("Alois", "Mueller", 26));
		ss.add(new Person("Gustav", "Maier", 30));
		ss.add(new Person("Berta", "Maier", 56));
		ss.forEach(p -> System.out.println(p));

		System.out.println();

		List<Person> persons = new LinkedList<>();
		persons.addAll(ss);
		Collections.sort(persons,
				(p1, p2) -> -p1.getFirstName().compareTo(p2.getFirstName()));
		persons.forEach(p -> System.out.println(p));
		
	}
}
