package prswe2.ss21.ue04.gradetable.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import prswe2.ss21.ue04.gradetable.model.GradeTableModel;
import prswe2.ss21.ue04.gradetable.model.Results;
import prswe2.ss21.ue04.gradetable.model.Student;

public class AddStudentController {

    private GradeTableModel model;

    @FXML
    Label idLabel = new Label("Student ID: ");

    @FXML
    private TextField idTextField = new TextField();

    @FXML
    Label nameLabel = new Label("Student Name: ");

    @FXML
    private TextField nameTextField = new TextField();

    @FXML
    Label firstLabel = new Label("Student First Name: ");

    @FXML
    private TextField firstTextField = new TextField();

    @FXML
    Label snLabel = new Label("Study Number: ");

    @FXML
    private ComboBox<Integer> snComboBox = new ComboBox<>();

    @FXML
    Label errorLabel = new Label("Empty Fields");

    @FXML
    private Button addButton = new Button("Add");

    @FXML
    private void initialize() {

        snComboBox.getItems().addAll(521, 526, 921);
        errorLabel.setVisible(false);
        errorLabel.setTextFill(Color.RED);


        addButton.setOnAction(e -> {
            if (idTextField.getText() == null || idTextField.getText().trim().isEmpty()
                    || nameTextField.getText() == null || nameTextField.getText().trim().isEmpty()
                    || firstTextField.getText() == null || firstTextField.getText().trim().isEmpty()
                    || snComboBox.getSelectionModel().isEmpty()) {
                errorLabel.setVisible(true);

            } else {
                errorLabel.setVisible(false);
                model.addResults(new Results(new Student(idTextField.getText(), nameTextField.getText(),
                        firstTextField.getText(), snComboBox.getValue())));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void initModel(GradeTableModel model) {
        this.model = model;
    }
}