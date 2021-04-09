package prswe2.ss21.ue03.gradetable.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private final StringProperty id;
    private final StringProperty firstName;
    private final StringProperty name;
    private final IntegerProperty sn;

    public Student(String id, String firstName, String name, int sn) {
        this.id = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.name = new SimpleStringProperty(name);
        this.sn = new SimpleIntegerProperty(sn);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty snProperty() {
        return sn;
    }

    @Override
    public String toString() {
        return "Mat: " + this.idProperty().get() + ", Name: " + this.nameProperty().get()
                + ", First: " + this.firstNameProperty().get() + ", sn: " + this.snProperty().get();
    }
}