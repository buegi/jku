package prswe2.ss21.ue03.gradetable.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradeTableModel {
    private final ObservableList<Results> results = FXCollections.observableArrayList();

    public GradeTableModel() {
        // remove following line to not insert test data
        results.addAll(TestData.createData());
    }

    public ObservableList<Results> getResults() {
        return this.results;
    }

    public void addResults(Results result) {
        this.results.add(result);
    }

    public void removeResults(int index) {
        this.results.remove(this.results.get(index));
    }
}