package ss20.ue02.demo.persons6;

@SuppressWarnings("serial")
public class CallException extends PersonException {

	private final Person callee; 
	
	public CallException(Person caller, Person callee) {
		super(String.format("Invalid call to %s", callee.getName()), caller); 
		this.callee = callee; 
	}

	public Person getCallee() {
		return callee;
	}

}
