package prswe2.ss21.ue03.gradetable.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import prswe2.ss21.ue03.gradetable.model.GradeTableModel;
import prswe2.ss21.ue03.gradetable.model.Results;

import java.io.IOException;

public class GradeTableController {

    private final GradeTableModel model;
    private Stage primaryStage;

    public GradeTableController() {
        model = new GradeTableModel();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
    private TableColumn<Results, Integer> snColumn;

    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;

    @FXML
    private TableColumn<Results, Integer> sumColumn;

    @FXML
    private TableColumn<Results, String> gradeColumn;

    @FXML
    private void initialize() {
        resultsView.itemsProperty().setValue(model.getResults());
        resultsView.setEditable(true);
        resultsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        idColumn.setCellValueFactory(r -> r.getValue().getStudent().idProperty());
        nameColumn.setCellValueFactory(r -> r.getValue().getStudent().nameProperty());
        firstNameColumn.setCellValueFactory(r -> r.getValue().getStudent().firstNameProperty());
        snColumn.setCellValueFactory(r -> r.getValue().getStudent().snProperty().asObject());

        for (int i = 0; i < Results.NR_ASSIGNMENTS; i++) {
            final int a = i;
            TableColumn<Results, Integer> col = new TableColumn<>("A" + (i + 1));
            resultsView.getColumns().add(a + 4, col);
            col.setPrefWidth(40);
            col.setCellValueFactory((TableColumn.CellDataFeatures<Results, Integer> p) -> {
                Results r = p.getValue();
                if (r != null) {
                    int pts = r.getAssignment(a).getValue();
                    if (Results.MIN_POINTS <= pts && pts <= Results.MAX_POINTS) {
                        return r.getAssignment(a).asObject();
                    }
                }
                r.getAssignment(a).set(Results.UNDEFINED);
                return r.getAssignment(a).asObject();
            });

            col.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
                @Override
                public String toString(Integer integer) {
                    return integer != null ? integer.toString() : "";
                }

                @Override
                public Integer fromString(String s) {
                    try {
                        resultsView.refresh();
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }));
        }

        sumColumn.setCellValueFactory(r -> r.getValue().getGradePoints().asObject());
        gradeColumn.setCellValueFactory(r -> r.getValue().getGrade());

        addBtn.setOnAction(e -> {
            Stage addStudentUI = new Stage();
            addStudentUI.initOwner(primaryStage);
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addStudentUI.fxml"));
            addStudentUI.setTitle("Add Student");
            final Parent root;
            try {
                root = loader.load();
                addStudentUI.setScene(new Scene(root));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            AddStudentController addStudentController = loader.getController();
            addStudentController.initModel(this.model);
            addStudentUI.show();
        });

        removeBtn.setOnAction(e -> {
            if (resultsView.getSelectionModel().getSelectedIndex() >= 0)
                model.removeResults(resultsView.getSelectionModel().getSelectedIndex());
        });
    }
}