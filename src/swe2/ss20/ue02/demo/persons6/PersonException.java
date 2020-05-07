package swe2.ss20.ue02.demo.persons6;

@SuppressWarnings("serial")
public class PersonException extends Exception {

	private final Person person; 
	
	public PersonException(String msg, Person person) {
		super(msg); 
		this.person = person;  
	}

	public Person getPerson() {
		return person;
	}

}
