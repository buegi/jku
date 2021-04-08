package prswe2.ss21.ue03.gradetable.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
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


    private TextField popUpAddStudentButtonId = new TextField();

    private TextField popUpAddStudentButtonName = new TextField();

    private TextField popUpAddStudentButtonFirst = new TextField();

    private ComboBox<Integer> popUpAddStudentButtonSnComboBox = new ComboBox<>();

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
            assignmentColumns[i].setCellFactory(TextFieldTableCell.forTableColumn());
        }

        resultsView.getColumns().addAll(assignmentColumns);

        // grade values
        sumColumn.setCellValueFactory(r -> new SimpleStringProperty(Integer.toString(r.getValue().getGradePoints())));
        gradeColumn.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getGrade()));

        addBtn.setOnAction(e -> {
            Stage popupAddStudentStage = new Stage();
            VBox vbox1 = new VBox(10);
            VBox vbox2 = new VBox(20);
            HBox hbox = new HBox(100);

            vbox1.getChildren().addAll(new Text("Student ID:"), new Text("Student Name:"),
                    new Text("Student First Name:"), new Text("Student Study Number"));
            vbox2.getChildren().addAll(popUpAddStudentButtonId, popUpAddStudentButtonName, popUpAddStudentButtonFirst,
                    popUpAddStudentButtonSnComboBox, popUpAddStudentButton);
            hbox.getChildren().addAll(vbox1, vbox2);
            popUpAddStudentButtonSnComboBox.getItems().addAll(521, 921, 822);
            popUpAddStudentButtonSnComboBox.getSelectionModel().selectFirst();
            StackPane root = new StackPane();
            root.getChildren().add(hbox);
            popupAddStudentStage.setTitle("Add Student");
            popupAddStudentStage.setScene(new Scene(root, 300, 200));
            popupAddStudentStage.initModality(Modality.APPLICATION_MODAL);
            popupAddStudentStage.show();

            popUpAddStudentButton.setOnAction(ev -> {
                model.results.add(new Results(new Student(popUpAddStudentButtonId.getText(), popUpAddStudentButtonFirst.getText(),
                        popUpAddStudentButtonName.getText(), popUpAddStudentButtonSnComboBox.getValue())));
                popupAddStudentStage.close();
            });
        });

        removeBtn.setOnAction(e -> {
            if (resultsView.getSelectionModel().getSelectedIndex() >= 0)
                model.results.remove(resultsView.getSelectionModel().getSelectedIndex());
            // check if model only contains non deleted items
            System.out.println("Items remaining after remove:");
            model.results.forEach(r -> System.out.println(r.getStudent().nameProperty()));
        });
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}