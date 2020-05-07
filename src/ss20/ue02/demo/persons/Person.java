package ss20.ue02.demo.persons;

import inout.Out; 

public abstract class Person extends Object {
	
	private final String name; 
	
	public Person(String name) {
		super(); 
		this.name = name; 
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void live() {
		work(); 
		haveFun();
		sleep(); 
	}
	
	protected abstract void work(); 	
	protected abstract void haveFun();
	
	protected void sleep() { 
		Out.println("Schnarch!"); 
	}

	// overriding and overloading ---------------------------------------------
	
	public void calls(Person p) throws PersonException {
		Out.println("Person calls person"); 
	}
	
	// ------------------------------------------------------------------------
	
	
}
