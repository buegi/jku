package prswe2.ss21.ue03.demo.tableDemo;

import java.util.concurrent.Callable;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import prswe2.ss21.ue03.demo.tableDemo.model.Model;
import prswe2.ss21.ue03.demo.tableDemo.model.Person;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = setupUI(new Model());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Parent setupUI(Model model) {
		Button addBtn = new Button("Add");
		Button removeBtn = new Button("Remove");
		TextField nameField = new TextField();
		TextField roleField = new TextField();
		Label sizeLbl = new Label();
		Label emptyLbl = new Label();

		TableView<Person> personsView = new TableView<>();
		personsView.setItems(model.persons);
		personsView.setEditable(true);

		TableColumn<Person, String> nameColumn = new TableColumn<Person, String>("Name");
		TableColumn<Person, String> roleColumn = new TableColumn<Person, String>("Role");
		TableColumn<Person, Integer> ageColumn = new TableColumn<Person, Integer>("Age");
		nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Person, String> param) -> {
			Person person = param.getValue();
			return person != null ? person.nameProperty() : new ReadOnlyStringWrapper("");
		});
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				return object != null ? object.toString() : "";
			}

			@Override
			public Integer fromString(String string) {
				try{
					return Integer.parseInt(string);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		}));
		
		ageColumn.setOnEditCommit(evt -> {
        	// would be cleaner with condition check in a custom property or its setter
        	if(evt.getNewValue() == null || evt.getNewValue() < 0) {
        		personsView.refresh();
        		return;
    		}
    		((Person) evt.getTableView().getItems().get(evt.getTablePosition().getRow()))
    			.ageProperty().set(evt.getNewValue());
        });
		
		personsView.getColumns().addAll(nameColumn, roleColumn, ageColumn);

		addBtn.setOnAction(e -> {
			model.persons.add(new Person(nameField.getText(), roleField.getText(), 0));
		});

		removeBtn.setOnAction(e -> {
			if (personsView.getSelectionModel().getSelectedIndex() >= 0)
				model.persons.remove(personsView.getSelectionModel().getSelectedIndex());
		});
		
		emptyLbl.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return model.persons.isEmpty() ? "empty" : "not empty";
			}
			
		}, model.persons));
		
		sizeLbl.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return String.valueOf(model.persons.size());
			}
			
		}, model.persons));

		VBox vBox = new VBox();
		HBox hBox = new HBox();
		hBox.getChildren().addAll(nameField, roleField, addBtn, removeBtn);
		vBox.getChildren().addAll(hBox, personsView, sizeLbl, emptyLbl);
		return vBox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
