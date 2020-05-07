package ss20.ue02.demo.persons3;

import inout.Out;

public class PersonApp {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Person p1, p2; 
		Student stdt; 
		Professor prof; 
		
		stdt = new Student("Hans", "Informatik"); 
		prof = new Professor("Wirth", "Software");
		
		p1 = stdt; 
		p2 = prof; 
		
		Out.println(p1.toString()); 
		p1.live(); 
		
		Out.println(); 
		Out.println(p2.toString()); 
		p2.live(); 
		
		if (p1 instanceof Student) {
			Student s = (Student) p1; 
			s.setStudy("WiWi"); 
		}
		Out.println(p1.toString()); 

	}

}
