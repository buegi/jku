package swe2.ss20.ue02.demo.persons5;

import swe2.inout.Out;

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
	
	private String study;
	
	public Student(String name, String study) {
		super(name); 
		this.study = study; 
	}

	public String getInfo() {
		return study; 
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
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
