package swe2.ss19.ue06.demo.coll03;

public class Demo3 {

	public static void main(String[] args) {
		
		Person p = new Person("Ralf", "Berger");
		Student s = new Student("Ralf", "Berger", 9955123);
		
		boolean pEqualsS = p.equals(s);
		System.out.println(pEqualsS);
		
		// Problem: do not implement equals asymmetrically
		
		boolean sEqualsP = s.equals(p);
		System.out.println(sEqualsP);   

	}
}
