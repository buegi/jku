package prswe2.ss21.ue04.gradetable.model;

import prswe2.ss21.ue04.gradetable.jdbc.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradeTableModel {
    private final ObservableList<Results> results = FXCollections.observableArrayList();
    private final DBManager dbManager = new DBManager();

    public GradeTableModel() {
        // uncomment next line to initialize empty database if wanted
        // this.dbManager.createGradeTableDB();
        // use next line to insert testdata, but also do not use the line after
        // (otherwise the data is double shown in the GUI!)
        // actual startup setting (this constructor) is to read date from db, and work
        // further
        // this.results.addAll(this.dbManager.insertTestData());
        this.results.addAll(this.dbManager.getActualDBContent());
        // according to this stackoverflow post, alle db connections are opened as late
        // and closed as soon as possible:
        // https://stackoverflow.com/questions/4111594/why-always-close-database-connection
        // I was not exactly sure, if I should open the db connection on program startup
        // and close the connection at program end
        // Thus, I decided to do exactly when it's needed. (see DBManager)
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