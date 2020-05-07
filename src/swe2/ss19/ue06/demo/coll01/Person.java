package swe2.ss19.ue06.demo.coll01;

public class Person {

	public final String surName;
	public final String firstName;
	public final int hired;

	public Person(String firstName, String surName) {
		this(firstName, surName, 2009);
	}

	public Person(String firstName, String surName, int hired) {
		this.firstName = firstName;
		this.surName = surName;
		this.hired = hired;
	}

	@Override
	public String toString() {
		return firstName + " " + surName + " " + hired;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person other = (Person) obj;
			return this.surName.equals(other.surName)
					&& this.firstName.equals(other.firstName);
		}
		return false;
	}
	
}
