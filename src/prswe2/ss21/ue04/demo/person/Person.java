package prswe2.ss21.ue04.demo.person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Person {
	private final StringProperty firstName;
	private final StringProperty lastName;

	private final int ID;
	
	public Person(int ID, String firstName, String lastName) {
		this.ID = ID;
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	public int getID() {
		return ID;
	}
	
	public String toString() {
		return firstName.get() + " " + lastName.get();
	}
}
