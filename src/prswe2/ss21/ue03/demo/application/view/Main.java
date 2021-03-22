package prswe2.ss21.ue03.demo.application.view;

import prswe2.ss21.ue03.demo.application.model.Model;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
		TextField input = new TextField();
		Label output = new Label("");
		VBox box = new VBox(10);

		model.nameProperty().bindBidirectional(input.textProperty());
		output.textProperty().bind(model.nameProperty());
//		output.textProperty().bind(Bindings.when(Bindings.isEmpty(model.nameProperty())).then("empty").otherwise("not empty"));

		model.nameProperty().setValue("ABC");

		box.getChildren().addAll(input, output);
		return box;
	}

	public static void main(String[] args) {
		launch(args);

	}
}
