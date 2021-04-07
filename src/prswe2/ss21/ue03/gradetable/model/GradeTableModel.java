package prswe2.ss21.ue03.gradetable.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradeTableModel {
    private StringProperty name = new SimpleStringProperty();

    public GradeTableModel() {
        this.name.addListener((x, o, n) -> {
            System.out.format("Name of %s changed from %s to %s %n", this.name.get(), o, n);
        });
    }

    public StringProperty nameProperty() {
        return this.name;
    }
}
