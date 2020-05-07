package swe2.ss19.ue06.demo.coll05;

public class Person implements Comparable<Person> {

	private final String surName;
	private final String firstName;
	private final int age;

	public Person(String firstName, String surName, int age) {
		this.firstName = firstName;
		this.surName = surName;
		this.age = age;
	}

	@Override
	public String toString() {
		return firstName + " " + surName + " " + age;
	}

	public int getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurName() {
		return surName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
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
