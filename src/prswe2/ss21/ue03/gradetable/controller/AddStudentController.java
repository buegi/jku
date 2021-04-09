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

public class AddStudentController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

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
    private Button addButton = new Button("Add");

    @FXML
    private void initialize() {

        addButton.setOnAction(e -> {
//            Stage addStudentUI = new Stage();
//            addStudentUI.initOwner(primaryStage);
//            final FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addStudentUI.fxml"));
//            addStudentUI.setTitle("Add Student");
//            final Parent root;
//            try {
//                root = loader.load();
//                addStudentUI.setScene(new Scene(root));
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            addStudentUI.show();
//            Stage popupAddStudentStage = new Stage();
//            VBox vbox1 = new VBox(10);
//            VBox vbox2 = new VBox(10);
//            HBox hbox = new HBox(10);
//
//            vbox1.getChildren().addAll(new Text("Student ID:"), new Text("Student Name:"),
//                    new Text("Student First Name:"), new Text("Student Study Number"));
//            vbox2.getChildren().addAll(popUpAddStudentButtonId, popUpAddStudentButtonName, popUpAddStudentButtonFirst,
//                    popUpAddStudentButtonSnComboBox, popUpAddStudentButton);
//            hbox.getChildren().addAll(vbox1, vbox2);
//            popUpAddStudentButtonSnComboBox.getItems().addAll(521, 921, 822);
//            popUpAddStudentButtonSnComboBox.getSelectionModel().selectFirst();
//            StackPane root = new StackPane();
//            root.getChildren().add(hbox);
//            popupAddStudentStage.setTitle("Add Student");
//            popupAddStudentStage.setScene(new Scene(root, 300, 200));
//            popupAddStudentStage.initModality(Modality.APPLICATION_MODAL);
//            popupAddStudentStage.show();
//
//            popUpAddStudentButton.setOnAction(ev -> {
//                Results result = new Results(new Student(popUpAddStudentButtonId.getText(), popUpAddStudentButtonFirst.getText(),
//                        popUpAddStudentButtonName.getText(), popUpAddStudentButtonSnComboBox.getValue()));
//                // check newly added result/student
//                System.out.println(result);
//                model.results.add(result);
//                popupAddStudentStage.close();
//            });
//        });
        });
    }
}