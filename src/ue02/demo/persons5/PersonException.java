package ue02.demo.persons5;

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
