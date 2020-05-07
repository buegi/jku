package ss20.ue02.demo.persons4;

import inout.Out;

public class PersonApp {
	
	public static void main(String[] args) {
		
		Student stdt = new Student("Hans", "Informatik");
		Professor prof = new Professor("Wirth", "Software"); 
		
		Person p1 = stdt; 
		Person p2 = prof; 
		
		// student calls professor
		stdt.calls(prof); 
		stdt.calls(p2); 
		p1.calls(prof); 
		p1.calls(p2); 
		
		Out.println();
		
		// professor calls student
		prof.calls(stdt); 
		prof.calls(p1); 
		p2.calls(prof); 
		p2.calls(p1); 
				
	}

}
