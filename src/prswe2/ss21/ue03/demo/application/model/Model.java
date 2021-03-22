package prswe2.ss21.ue03.demo.application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {

	private StringProperty name = new SimpleStringProperty();

	public Model(){
		this.name.addListener((x, o, n) -> {
			System.out.format("Name of %s changed from %s to %s %n", this.name.get(), o, n);
		});
	}

	public StringProperty nameProperty() {
		return name;
	}
	
}
