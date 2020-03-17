package ue02.demo.persons3;

import inout.Out;

public class Professor extends Person {
	
	public static String type = "Professor"; 
	
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
