package ss19.ue06.demo.coll02;

public class Person {

	public String surName;
	public String firstName;
	public int hired;

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
}
