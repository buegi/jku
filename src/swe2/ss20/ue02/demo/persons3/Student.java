package swe2.ss20.ue02.demo.persons3;

import swe2.inout.Out;

public class Student extends Person {
	
	public static String type = "Student"; 
	
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
	
	@Override
	public String toString() {
		return super.toString()+  " [study = " + study + "]";
	}

	@Override
	protected String getPersonType() {
		return "Student";
	} 	

}
