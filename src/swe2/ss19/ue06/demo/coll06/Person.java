package swe2.ss19.ue06.demo.coll06;

public class Person implements Comparable<Person> {

	public String surName;
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

	@Override
	public int hashCode() {
		return this.surName.hashCode() ^ firstName.hashCode();
	}

	@Override
	public int compareTo(Person other) {
		int compare = this.surName.compareTo(other.surName);
		if (compare == 0) {
			compare = this.firstName.compareTo(other.firstName);
		}
		return compare;
	}

}
