package swe2.ss20.ue02.demo.persons;

public enum Study {
	
	CS(521), WiWi(180), Mechatronics(281), IE(289); 
	
	private int id;

	private Study(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + id + ")";
	} 

}
