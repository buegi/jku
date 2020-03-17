package ue02.demo.persons2;

import inout.Out;

public class Student extends Person {
	
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
	
//	@Override
//	public String toString() {
//		return super.toString()  + " [study = " + study + "]";
//	}	

	@Override
	public String toString() {
		return "Student: " + getName() + " [study = " + study + "]";
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
