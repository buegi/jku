package prswe2.ss21.ue03.gradetable.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import prswe2.ss21.ue03.gradetable.model.GradeTableModel;
import prswe2.ss21.ue03.gradetable.model.Results;
import prswe2.ss21.ue03.gradetable.model.Student;

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
    private Button addBtn;
    @FXML
    private Button removeBtn;

    @FXML
    private TableColumn<Results, String> sumColumn;

    @FXML
    private TableColumn<Results, String> gradeColumn;

    @FXML
    private TextField popUpAddStudentButtonId = new TextField();
    @FXML
    private TextField popUpAddStudentButtonName = new TextField();
    @FXML
    private TextField popUpAddStudentButtonFirst = new TextField();
    @FXML
    private ComboBox<Integer> popUpAddStudentButtonSnComboBox = new ComboBox<>();
    @FXML
    private Button popUpAddStudentButton = new Button("Add");

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
            assignmentColumns[i].setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
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
            addStudentWindow();
        });

        removeBtn.setOnAction(e -> {
            System.out.println("remove");
            if (resultsView.getSelectionModel().getSelectedIndex() >= 0)
                model.results.remove(resultsView.getSelectionModel().getSelectedIndex());
            // check if model only contains non deleted items
            model.results.forEach(r -> System.out.println(r.getStudent().nameProperty()));
        });

        popUpAddStudentButton.setOnAction(e -> {
            model.results.add(new Results(new Student(popUpAddStudentButtonId.getText(), popUpAddStudentButtonFirst.getText(),
                    popUpAddStudentButtonName.getText(), popUpAddStudentButtonSnComboBox.getValue())));
        });
    }

    private void addStudentWindow() {
        VBox vbox1 = new VBox(1);
        VBox vbox2 = new VBox(1);
        HBox hbox = new HBox(1);
        vbox1.getChildren().addAll(new Text("Student ID:"), new Text("Student Name:"),
                new Text("Student First Name:"), new Text("Student Study Number"));
        vbox2.getChildren().addAll(popUpAddStudentButtonId, popUpAddStudentButtonName, popUpAddStudentButtonFirst,
                popUpAddStudentButtonSnComboBox, popUpAddStudentButton);
        hbox.getChildren().addAll(vbox1, vbox2);
        popUpAddStudentButtonSnComboBox.getItems().addAll(521, 921, 822);
        popUpAddStudentButtonSnComboBox.getSelectionModel().selectFirst();
        StackPane root = new StackPane();
        root.getChildren().add(hbox);
        /* new window with content */
        Stage popupStage = new Stage();
        popupStage.setTitle("Add Student");
        popupStage.setScene(new Scene(root, 300, 200));
        /* prevent access to main window while open */
        popupStage.initModality(Modality.APPLICATION_MODAL);
        /* show popup window */
        popupStage.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}