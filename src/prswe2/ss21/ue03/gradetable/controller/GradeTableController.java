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
    private TableColumn<Results, String> snColumn;

    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;

    @FXML
    private TableColumn<Results, Integer> sumColumn;

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


        // grade values
        sumColumn.setCellValueFactory(r -> r.getValue().getGradePoints().asObject());
        gradeColumn.setCellValueFactory(r -> r.getValue().getGrade());

        addBtn.setOnAction(e -> {
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
            Stage popupAddStudentStage = new Stage();
            VBox vbox1 = new VBox(10);
            VBox vbox2 = new VBox(10);
            HBox hbox = new HBox(10);

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
                Results result = new Results(new Student(popUpAddStudentButtonId.getText(), popUpAddStudentButtonFirst.getText(),
                        popUpAddStudentButtonName.getText(), popUpAddStudentButtonSnComboBox.getValue()));
                // check newly added result/student
                System.out.println(result);
                model.results.add(result);
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
}