package swe2.ss19.ue06.demo.coll01;

public class Demo1 {

	public static void main(String[] args) {

		Person franz1 = new Person("Franz", "Maier");
		Person franz2 = new Person("Franz", "Maier");

		boolean equals = franz1.equals(franz2);
		System.out.println(equals);

		Object obj2 = franz2;
		System.out.println(obj2.toString());
		System.out.println(obj2.equals(franz1));
		
		equals = franz1.equals(obj2);
		System.out.println(equals);

	}
}
