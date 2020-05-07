package ss20.ue02.demo.persons;

import inout.Out;

public class PersonApp {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Student stdt = new Student("Hans", Study.CS);
		Professor prof = new Professor("Wirth", "Software"); 
		
		Person p1 = stdt; 
		Person p2 = prof; 
		
		Out.println(p1.toString()); 
		p1.live();

		Out.println();
		Out.println(p2.toString()); 
		p2.live();
		
	}

}
