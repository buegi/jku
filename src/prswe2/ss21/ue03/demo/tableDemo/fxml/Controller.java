package prswe2.ss21.ue03.demo.tableDemo.fxml;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import prswe2.ss21.ue03.demo.tableDemo.model.Model;
import prswe2.ss21.ue03.demo.tableDemo.model.Person;

public class Controller {

    private final Model model;

    public Controller() {
        model = new Model();
    }

    @FXML
    private TableView<Person> personsView;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> roleColumn;

    @FXML
    private TableColumn<Person, Integer> ageColumn;

    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> roleCombo;
    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;

    @FXML
    private void initialize() {
        personsView.itemsProperty().setValue(model.persons);
        personsView.setEditable(true);
        personsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Person, String> param) -> {
            Person person = param.getValue();
            return person != null ? person.nameProperty() : new ReadOnlyStringWrapper("");
        });
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            // does not catch any invalid input, i.e. negative age. see setOnEditCommit for this
            @Override
            public String toString(Integer object) {
                return object != null ? object.toString() : "";
            }

            @Override
            public Integer fromString(String string) {
                try {
                    return Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }));

        addBtn.setOnAction(e -> {
            model.persons.add(new Person(nameField.getText(), roleCombo.getSelectionModel().getSelectedItem(), 0));
        });

        removeBtn.setOnAction(e -> {
            if (personsView.getSelectionModel().getSelectedIndex() >= 0)
                model.persons.remove(personsView.getSelectionModel().getSelectedIndex());
        });
    }
}
