package ss20.ue02.demo.persons6;

import inout.Out;

public class Student extends Person {
	
	// overriding and overloading ---------------------------------------------
	
	@Override
	public void calls(Person p) throws CallException {
		if (p instanceof Professor) {
			throw new CallException(this, p); 
		}
		Out.println("Student calls person"); 
	}

	// ------------------------------------------------------------------------
	
	private Study study;
	
	public Student(String name, Study study) {
		super(name); 
		this.study = study; 
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	@Override
	public String toString() {
		return super.toString()+  " [study = " + study + "]";
	}

	@Override
	protected String getPersonType() {
		return "Student";
	} 
	
	@Override
	protected void work() {
		Out.println("Learn");
	}

	@Override
	protected void haveFun() {
		Out.println("Drink beer");
	}

	@Override
	public void live() {
		Out.println("Call Mam"); 
		super.live();
	}
			
}
