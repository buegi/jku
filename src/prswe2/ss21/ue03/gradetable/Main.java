package prswe2.ss21.ue03.gradetable;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("gradeTableUI.fxml"));
		final Parent root = loader.load();
		// voruebergehend auskommentiert
		// Controller controller = loader.getController();
		// controller.setPrimaryStage(primaryStage);

		primaryStage.setTitle("Grade Table");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
