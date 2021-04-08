package prswe2.ss21.ue03.gradetable.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradeTableModel {
    public ObservableList<Results> results = FXCollections.observableArrayList();

    public GradeTableModel() {
        // remove to not insert test data
        results.addAll(TestData.createData());
    }
}