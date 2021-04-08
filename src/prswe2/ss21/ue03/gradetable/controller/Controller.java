package prswe2.ss21.ue03.gradetable.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import prswe2.ss21.ue03.gradetable.model.GradeTableModel;
import prswe2.ss21.ue03.gradetable.model.Results;

import java.util.Arrays;

public class Controller {

    private final GradeTableModel model;
    private Stage primaryStage;

    public Controller() {
        model = new GradeTableModel();
    }

    @FXML
    private TableView<Results> resultsView;

    @FXML
    private TableColumn<Results, String> idColumn;

    @FXML
    private TableColumn<Results, String> nameColumn;

    @FXML
    private TableColumn<Results, String> firstNameColumn;

    @FXML
    private TableColumn<Results, String> snColumn;

    private TableColumn<Results, String>[] assignmentColumns = new TableColumn[Results.NR_ASSIGNMENTS];

    @FXML
    private TableColumn<Results, String> sumColumn;

    @FXML
    private TableColumn<Results, String> gradeColumn;

    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;

    @FXML
    private void initialize() {
        resultsView.itemsProperty().setValue(model.results);
        resultsView.setEditable(true);
        resultsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // student values
        idColumn.setCellValueFactory(r -> r.getValue().getStudent().idProperty());
        nameColumn.setCellValueFactory(r -> r.getValue().getStudent().nameProperty());
        firstNameColumn.setCellValueFactory(r -> r.getValue().getStudent().firstNameProperty());
        snColumn.setCellValueFactory(r -> new SimpleStringProperty(Integer.toString(r.getValue().getStudent().snProperty().get())));

        // assignment values
        for (int i = 0; i < assignmentColumns.length; i++) {
            assignmentColumns[i] = new TableColumn<>("A" + (i + 1));
            final int a = i;
            assignmentColumns[i].setCellValueFactory(as -> new SimpleStringProperty(Integer.toString(as.getValue().getAssignment(a))));
            assignmentColumns[i].setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<String>() {
                @Override
                public String toString(String s) {
                    return s != null ? s : "";
                }

                @Override
                public String fromString(String s) {
                    return s != null ? s : "";
                }
            }));
        }
        resultsView.getColumns().addAll(assignmentColumns);

        // grade values
        sumColumn.setCellValueFactory(r -> new SimpleStringProperty(Integer.toString(r.getValue().getGradePoints())));
        gradeColumn.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getGrade()));

        addBtn.setOnAction(e -> {
            System.out.println("add");
        });

        removeBtn.setOnAction(e -> {
            System.out.println("remove");
            if (resultsView.getSelectionModel().getSelectedIndex() >= 0)
                model.results.remove(resultsView.getSelectionModel().getSelectedIndex());
        });

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
