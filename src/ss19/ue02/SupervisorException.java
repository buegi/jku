package ss19.ue02;

public class SupervisorException extends Exception {

	private static final long serialVersionUID = -4431381387510816521L;

	private final Employee emp;

	public SupervisorException(String message, Employee emp) {
		super(message);
		this.emp = emp;
	}

	public Employee getEmployee() {
		return emp;
	}
}