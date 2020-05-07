package ss19.ue06.demo.coll10;

import inout.Out;

import java.util.HashMap;
import java.util.Map;

public class Demo10 {

	public static void main(String[] args) {
		Out.println("---- test hash map --------------");

		Map<Long, Person> pmap = new HashMap<Long, Person>();
		pmap.put(new Long(123403121977L), new Person("Franz", "Maier", 2004));
		pmap.put(new Long(423101011987L), new Person("Alois", "Mueller", 2003));
		pmap.put(111131121978L, new Person("Gustav", "Maier", 1999));
		pmap.put(999910101980L, new Person("Berta", "Maier", 2003));

		Out.println(pmap.get(123403121977L));
		Out.println();

		pmap.keySet().forEach(ssn -> Out.println(pmap.get(ssn)));
		Out.println();

		pmap.values().forEach(p -> System.out.println(p));
		Out.println();

		pmap.entrySet().forEach(
				entry -> Out.println(entry.getKey() + ": " + entry.getValue()));
		Out.println();
	}
}
