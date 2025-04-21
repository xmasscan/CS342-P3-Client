

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class GuiClient extends Application{

	
	public static void main(String[] args) {
		Client clientThread = new Client();
		clientThread.start();
		launch(args);
		Scanner s = new Scanner(System.in);
		// Sign on attempt, will just work rn for testing
		// TODO: implement better sign on attempt handling
		boolean loggedIn = false;
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

		primaryStage.setScene(new Scene(new TextField("I am not yet implemented")));
		primaryStage.setTitle("Client");
		primaryStage.show();
		
	}
	



}
