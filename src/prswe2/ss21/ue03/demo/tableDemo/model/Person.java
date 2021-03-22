package prswe2.ss21.ue03.demo.tableDemo.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	private final StringProperty name;
	private final StringProperty role;
	private final IntegerProperty age;

	public Person(String name, String role, Integer age) {
		super();
		this.name = new SimpleStringProperty(name);
		this.role = new SimpleStringProperty(role);
		this.age = new SimpleIntegerProperty(age);
		this.age.addListener((x, o, n) -> {
			System.out.format("Age of %s changed from %d to %d %n", this.name.get(), o, n);
		});
	}

	public Person() {
		this("NN", "PhD", 30);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty roleProperty() {
		return role;
	}

	public IntegerProperty ageProperty() {
		return age;
	}
	
	@Override
	public String toString() {
		return nameProperty().get();
	}
}
