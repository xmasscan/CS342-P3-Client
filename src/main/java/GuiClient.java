

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class GuiClient extends Application{
	static Client clientThread = new Client();;
	
	public static void main(String[] args) {
		clientThread.start();
		launch(args);

		Scanner s = new Scanner(System.in);
		boolean loggedIn = false;
		System.out.println("Enter a username: ");
		/*while(!loggedIn){
			String username = s.nextLine();
			clientThread.signOn(username);
			loggedIn = clientThread.loggedIn;
		}*/
		// User Logged in, attempt to connect to game
		boolean connected = false;
		/*while(!connected){
			System.out.println("Sending a game connection request...");
			clientThread.connect();
			connected = clientThread.connected;
		}*/

		while (s.hasNextLine()){
			String x = s.nextLine();
			clientThread.send(x);
		}

		s.close();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GuiClient.fxml"));

		Scene scene = new Scene(root, 1024, 768);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.show();
		
	}
	



}
