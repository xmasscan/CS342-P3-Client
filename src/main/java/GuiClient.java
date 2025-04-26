import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class GuiClient extends Application{
   	static Client clientThread;
	static Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		clientThread = new Client(data -> {
			Platform.runLater( () -> {
				switch(((ServerMessage) data).messageType) {
					case 10:
						try {
							Parent root2 = FXMLLoader.load(getClass().getResource("Menu.fxml"));
							Scene scene2 = new Scene(root2, 1024, 768);
							scene2.getStylesheets().add("menu.css");
							primaryStage.setScene(scene2);
							break;
							
						} catch (Exception e) {
							// TODO: handle exception
						}
					case 8:
						try {
							LoginControllers.updateUsername(data.argv.get(0));
							Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
							Scene scene = new Scene(root, 1024, 768);
							scene.getStylesheets().add("menu.css");
							primaryStage.setScene(scene);

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						break;
					case 9:
						try {
							Parent root = FXMLLoader.load(getClass().getResource("Waiting.fxml"));
							Scene scene = new Scene(root, 1024, 768);
							scene.getStylesheets().add("waiting.css");
							primaryStage.setScene(scene);

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						break;
					case 6:
						try {
							Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
							Scene scene = new Scene(root, 1024, 768);
							scene.getStylesheets().add("game.css");
							primaryStage.setScene(scene);

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						break;
					case 2:
						int column = Integer.parseInt(data.argv.get(0));
						clientThread.findSpace(column);
						GameControllers.updateColumn(column);
						break;
					// Winner Message Handling
					case 4:
						String state = data.argv.get(0);
						// If player is a winner, update the thread to reflect that
						if(state.compareTo("Winner") == 0){
							clientThread.winner = 1;
						} else if (state.compareTo("Loser") == 0){
							clientThread.winner = 0;
						} else {
							clientThread.winner = 2;
						}
						// Inform the client that the game is complete
						try {
							// TODO: Set this back to Rematch.fxml when rematch functionality is implemented
							primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Menu.fxml")), 1024, 768));
						}
						catch (Exception e){
							e.printStackTrace();
						}
						break;
					// updateChat Handler
					case 3:
						// Build Variables from message
						String username = data.argv.get(0);
						String message = data.argv.get(1);
						GameControllers.updateChat(username,message);
						break;
				}

			});
		});
		clientThread.start();

		Parent root = FXMLLoader.load(getClass().getResource("GuiClient.fxml"));
		Scene scene = new Scene(root, 1024, 768);
		scene.getStylesheets().add("login.css");
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
