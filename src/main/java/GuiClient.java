

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class GuiClient extends Application{

	
	public static void main(String[] args) {
		Client clientThread = new Client();
		clientThread.start();
		//launch(args);
		System.out.println("Before Scanner ");
		Scanner s = new Scanner(System.in);

		// TODO: implement better sign on attempt handling
		boolean loggedIn = false;
		// TODO: fix nextLine happening before printing & handle rejections
		System.out.println("Enter a username: ");
		while(!loggedIn){
			String username = s.nextLine();
			clientThread.signOn(username, "adminTest");
			loggedIn = clientThread.loggedIn;
		}

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
