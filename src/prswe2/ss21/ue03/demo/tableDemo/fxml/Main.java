package prswe2.ss21.ue03.demo.tableDemo.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// load the main interface from the FXML file
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("mainUI.fxml"));
		final Parent root = loader.load();

		primaryStage.setTitle("Persons");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
