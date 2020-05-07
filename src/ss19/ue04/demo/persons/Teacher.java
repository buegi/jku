package ss19.ue04.demo.persons;

public class Teacher extends Person {
	
	public Teacher(String name) {
		super(name); 
	}
	
	public void teach() {
		System.out.println("Teacher " + name + " is teaching"); 
	}

}
