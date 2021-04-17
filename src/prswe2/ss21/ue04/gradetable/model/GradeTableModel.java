package prswe2.ss21.ue04.gradetable.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prswe2.ss21.ue04.gradetable.jdbc.DBManager;

public class GradeTableModel {
    private final ObservableList<Results> results = FXCollections.observableArrayList();
    private final DBManager dbManager = new DBManager();

    public GradeTableModel() {
        // uncomment next line to initialize empty database if wanted
        // this.dbManager.createGradeTableDB();
        // uncomment comments on next line to insert testdata if wanted
        // this.results.addAll(this.dbManager.insertTestData());
        this.results.addAll(this.dbManager.getActualDBContent());
    }

    public ObservableList<Results> getResults() {
        return this.results;
    }

    public void addResults(Results result) {
        if (this.dbManager.insertResults(result)) {
            this.results.add(result);
        }
    }

    public void removeResults(int index) {
        Results result = this.results.get(index);
        if (this.dbManager.removeResults(result)) {
            this.results.remove(result);
        }
    }

    public boolean updateAssignmentPoints(Results result, int index, int newPoints) {
        return this.dbManager.updateAssignmentPoints(result, index, newPoints);
    }

    public DBManager getDBManager() {
        return this.dbManager;
    }
}