package ss20.ue02.demo.persons4;

import inout.Out;

public class Professor extends Person {
	
	// overriding and overloading ---------------------------------------------
	
	public void calls(Person p) {
		Out.println("Professor calls person"); 
	}
	
	public void calls(Professor p) {
		Out.println("Professor calls professor"); 
	}
	
	public void calls(Student p) {
		Out.println("Professor calls student"); 
	}	
	
	// ------------------------------------------------------------------------
	
	String area;

	public Professor(String name, String area) {
		super(name);
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	} 
	
	@Override
	protected void work() {
		Out.println("Do research in " + area); 
	}

	@Override
	protected void haveFun() {
		Out.println("No fun"); 
	}

	@Override
	protected void sleep() {
		Out.println("No sleep"); 
	}

	@Override
	public String toString() {
		return super.toString()+  " [area = " + area + "]";
	}

	@Override
	protected String getPersonType() {
		return "Professor";
	} 

}
