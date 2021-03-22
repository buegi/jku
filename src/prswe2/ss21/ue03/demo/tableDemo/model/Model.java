package prswe2.ss21.ue03.demo.tableDemo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

	public ObservableList<Person> persons = FXCollections.observableArrayList(new Person("Thomas", "Head", 59),
			new Person("Anne", "Prof", 43), new Person("Raphael", "Assist", 25));

}
