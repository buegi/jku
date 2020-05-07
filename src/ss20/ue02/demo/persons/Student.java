package ss20.ue02.demo.persons;

import inout.Out;

public class Student extends Person {
	
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
		return super.toString() +  " [study = " + study + "]";
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
			
	// overriding and overloading ---------------------------------------------
	
	@Override
	public void calls(Person p) throws CallException {
		if (p instanceof Professor) {
			throw new CallException(this, p); 
		}
		Out.println("Student calls person"); 
	}

	// better not overload calls method 
//	public void calls(Professor p) {
//		Out.println("Student calls professor"); 
//	}
//	
//	public void calls(Student p) {
//		Out.println("Student calls student"); 
//	}

	// ------------------------------------------------------------------------
	
}
