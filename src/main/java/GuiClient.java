

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class GuiClient extends Application{

	
	public static void main(String[] args) {
		Client clientThread = new Client();
		//clientThread.start();
		launch(args);
		Scanner s = new Scanner(System.in);
		// Sign on attempt, will just work rn for testing
		// TODO: implement better sign on attempt handling
		boolean loggedIn = true;
		// TODO: fix nextLine happening before printing & handle rejections
		System.out.println("Enter a username: ");
		while(!loggedIn && s.hasNextLine()){
			String username = s.nextLine();
			clientThread.signOn(username);
			loggedIn = clientThread.loggedIn;
		}

		while (s.hasNextLine()){
			String x = s.nextLine();
			clientThread.send(x);
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

		Scene scene = new Scene(root, 1024, 768);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.show();
		
	}
	



}
