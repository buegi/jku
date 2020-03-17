package ue02.demo.persons4;

import inout.Out;

public class Student extends Person {
	
	// overriding and overloading ---------------------------------------------
	
	public void calls(Person p) {
		Out.println("Student calls person"); 
	}

	public void calls(Professor p) {
		Out.println("Student calls professor"); 
	}
	
	public void calls(Student p) {
		Out.println("Student calls student"); 
	}

	// ------------------------------------------------------------------------
	
	private String study;
	
	public Student(String name, String study) {
		super(name); 
		this.study = study; 
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
