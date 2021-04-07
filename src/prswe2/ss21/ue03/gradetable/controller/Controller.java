package prswe2.ss21.ue03.gradetable.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import prswe2.ss21.ue03.gradetable.model.GradeTableModel;
import prswe2.ss21.ue03.gradetable.model.Results;
import prswe2.ss21.ue03.gradetable.model.Student;

public class Controller {

    private final GradeTableModel model;
    private Stage primaryStage;

    public Controller() {
        model = new GradeTableModel();
    }

    @FXML
    private TableView<Student> studentsView;

    @FXML
    private TableColumn<Student, Integer> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, Integer> snColumn;

    @FXML
    private TableColumn<Student, Integer> a1Column;
    @FXML
    private TableColumn<Student, Integer> a2Column;
    @FXML
    private TableColumn<Student, Integer> a3Column;
    @FXML
    private TableColumn<Student, Integer> a4Column;
    @FXML
    private TableColumn<Student, Integer> a5Column;
    @FXML
    private TableColumn<Student, Integer> a6Column;
    @FXML
    private TableColumn<Student, Integer> a7Column;
    @FXML
    private TableColumn<Student, Integer> a8Column;
    @FXML
    private TableColumn<Student, Integer> a9Column;

    @FXML
    private TableColumn<Student, Integer> sumColumn;

    @FXML
    private TableColumn<Student, Integer> gradeColumn;

    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;

    @FXML
    private void initialize() {
        // studentsView.itemsProperty().setValue(model.students);
        studentsView.setEditable(true);
        studentsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("First"));
        snColumn.setCellValueFactory(new PropertyValueFactory<>("SN"));
        a1Column.setCellValueFactory(new PropertyValueFactory<>("A1"));
        a2Column.setCellValueFactory(new PropertyValueFactory<>("A2"));
        a3Column.setCellValueFactory(new PropertyValueFactory<>("A3"));
        a4Column.setCellValueFactory(new PropertyValueFactory<>("A4"));
        a5Column.setCellValueFactory(new PropertyValueFactory<>("A5"));
        a6Column.setCellValueFactory(new PropertyValueFactory<>("A6"));
        a7Column.setCellValueFactory(new PropertyValueFactory<>("A7"));
        a8Column.setCellValueFactory(new PropertyValueFactory<>("A8"));
        a9Column.setCellValueFactory(new PropertyValueFactory<>("A9"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("Sum"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("Grade"));


//        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> {
//            Student student = param.getValue();
//            return student != null ? student.nameProperty() : new ReadOnlyStringWrapper("");
//        });


//        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
//            // does not catch any invalid input, i.e. negative age. see setOnEditCommit for this
//            @Override
//            public String toString(Integer object) {
//                return object != null ? object.toString() : "";
//            }
//
//            @Override
//            public Integer fromString(String string) {
//                try {
//                    return Integer.parseInt(string);
//                } catch (NumberFormatException e) {
//                    return null;
//                }
//            }
//        }));

        addBtn.setOnAction(e -> {
            System.out.println("add");
        });

        removeBtn.setOnAction(e -> {
            System.out.println("remove");
        });

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
