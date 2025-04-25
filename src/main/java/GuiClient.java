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
					ServerMessage known = (ServerMessage) data;
					switch(known.messageType) {
						case 8:
							try {

								Parent root2 = FXMLLoader.load(getClass().getResource("Menu.fxml"));
							Scene scene2 = new Scene(root2, 1024, 768);
							primaryStage.setScene(scene2);
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
						case 9:
							try {

								Parent root3 = FXMLLoader.load(getClass().getResource("Waiting.fxml"));
								Scene scene3 = new Scene(root3, 1024, 768);
								primaryStage.setScene(scene3);
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
						case 6:
							try {

								Parent root4 = FXMLLoader.load(getClass().getResource("Game.fxml"));
								Scene scene4 = new Scene(root4, 1024, 768);
								primaryStage.setScene(scene4);
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;

						case 2:
							int collumn = Integer.parseInt(data.argv.get(0));
							clientThread.findSpace(collumn);
							GameControllers.updateColumn(collumn);
							
						
					}

				});
			});


			
		
		

		clientThread.start();

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
