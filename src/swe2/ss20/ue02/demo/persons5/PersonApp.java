package swe2.ss20.ue02.demo.persons5;

public class PersonApp {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Student stdt = new Student("Hans", "Informatik");
		Professor prof = new Professor("Wirth", "Software"); 
		
		Person p1 = stdt; 
		Person p2 = prof; 
		
//		p1.calls(p2);  does not compile !!!!
		
		try {
			p2.calls(p1);
			p1.calls(p2); 

		} catch (PersonException e) {
			e.printStackTrace();
		} 

		Object info = p1.getInfo(); 
		
	}

}
