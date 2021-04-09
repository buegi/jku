package prswe2.ss21.ue03.gradetable.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;

public class Results {

    public static final int NR_ASSIGNMENTS = 9;
    public static final int NR_VALID = 8;
    public static final int POINTS_VALID = 8;
    public static final int MIN_POINTS = 0;
    public static final int MAX_POINTS = 24;
    public static final int UNDEFINED = -1;

    private final Student student;
    private final ObservableList<IntegerProperty> assignments = FXCollections.observableArrayList();

    public Results(Student student) {
        this.student = student;
        for (int i = 0; i < NR_ASSIGNMENTS; i++) {
            assignments.add(i, new SimpleIntegerProperty(Results.UNDEFINED));
        }
    }

    public void setPoints(int idx, int ps) {
        if (idx >= NR_ASSIGNMENTS || idx < 0 || ps > MAX_POINTS || ps < MIN_POINTS) {
            return;
        }
        this.assignments.set(idx, new SimpleIntegerProperty(ps));
    }

    public Student getStudent() {
        return this.student;
    }

    public ObservableList<IntegerProperty> getAssignments() {
        return this.assignments;
    }

    public IntegerProperty getAssignment(int index) {
        return this.assignments.get(index);
    }

    public IntegerProperty getGradePoints() {
        int sum = 0;
        for (int i = 0; i < NR_ASSIGNMENTS; i++) {
            if (!(assignments.get(i).get() == Results.UNDEFINED)) {
                sum += assignments.get(i).get();
            }
        }
        return new SimpleIntegerProperty(sum);
    }

    public StringProperty getGrade() {
        int resultsValid = 0;
        for (int i = 0; i < NR_ASSIGNMENTS; i++) {
            if (assignments.get(i).get() == Results.UNDEFINED) {
                return new SimpleStringProperty("-");
            }
            if (assignments.get(i).get() >= POINTS_VALID) {
                resultsValid++;
            }
        }
        if (resultsValid < NR_VALID) {
            return new SimpleStringProperty("Nicht genügend");
        }
        int maxPoints = NR_ASSIGNMENTS * MAX_POINTS;
        IntegerProperty studentResult = this.getGradePoints();
        if (studentResult.get() >= (maxPoints * 0.875)) {
            return new SimpleStringProperty("Sehr gut");
        } else if (studentResult.get() >= (maxPoints * 0.750)) {
            return new SimpleStringProperty("Gut");
        } else if (studentResult.get() >= (maxPoints * 0.625)) {
            return new SimpleStringProperty("Befriedigend");
        } else if (studentResult.get() >= (maxPoints * 0.500)) {
            return new SimpleStringProperty("Genügend");
        } else {
            return new SimpleStringProperty("Nicht genügend");
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.student.toString());
        sb.append(" Assignments: [");
        this.assignments.forEach(a -> sb.append(a.get()).append(" "));
        sb.append("]");
        return sb.toString();
    }
}