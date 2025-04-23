import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class GuiClient extends Application{
	static Client clientThread = new Client();
	static Stage primaryStage;
	
	public static void main(String[] args) {
		clientThread.start();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GuiClient.fxml"));
		Scene scene = new Scene(root, 1024, 768);
		this.primaryStage = primaryStage;

		primaryStage.setScene(scene);
		primaryStage.setTitle("Welcome to Connect 4!");
		primaryStage.show();
	}

	/**
	 * Takes the Parent object returned by a FXMLLoader.load invocation
	 * and sets the primaryStage's scene to it!
	 * @param root
	 * 	The scene to switch the primaryStage to!
	 */
	public static void setScene(Parent root){
		primaryStage.setScene(new Scene(root, 1024, 768));
	}
}
