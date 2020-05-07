package ss19.ue06.demo.coll02;

import inout.Out;

import java.util.HashSet;
import java.util.Set;

public class Demo2 {

	public static void main(String[] args) {

		// equals test
		Person franz1 = new Person("Franz", "Maier");
		Person franz2 = new Person("Franz", "Maier");
		boolean eq = franz1.equals(franz2);
		Out.println(eq);

		// HashSet
		Set<Person> hs = new HashSet<Person>();
		hs.add(franz1);
		boolean franz2Contained = hs.contains(franz2);
		Out.println(franz2Contained);
		hs.add(franz2);

		
		// hashCode
		int franz1Hash = franz1.hashCode();
		Out.println(franz1Hash);
		
		int franz2Hash = franz2.hashCode();
		Out.println(franz2Hash);

	}
}
