import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class MenuControllers {
    @FXML private Button signOut;
    @FXML private Button startGame;
    @FXML private VBox full;
    @FXML private Text waits;

    @FXML private Button Ai;
    @FXML private Button Player;
    @FXML private Button Game;

    @FXML static private Text currentUser;
    @FXML static private Text Gold;
    @FXML static private Text Elo;
    @FXML static private ImageView image;

    Integer typeOfGame;

    Client clientThread = GuiClient.clientThread;

    // When this is implemented later, we should have a way to store this in the client thread and pull info from here
    // clientThread should handle current info of current player, GUI should read from it with getters
    @FXML static public void updateInformation(ServerMessage msg){
        currentUser.setText("Current User: " + msg.argv.get(0));
        Gold.setText("Gold: " + msg.argv.get(1));
        Elo.setText("Elo: " + msg.argv.get(2));
        Image temp = new Image("image" + msg.argv.get(3) + ".png");
        image.setImage(temp);
    }

    @FXML protected void startsGame(){
        // Attempt to sign in to the server!
        clientThread.connect();

        // If Login was successful, change screens
        if(clientThread.connected) {
            /*try {
                // Local Board Screen
                Parent root = FXMLLoader.load(getClass().getResource("Waiting.fxml"));
                GuiClient.setScene(root);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }

        full.getChildren().get(0).setVisible(false);
        waits.setText("Waiting");
        waiting();
    }

    @FXML protected void waiting(){
        

        clientThread.startGame();

        
        try {
           GuiClient.setScene(FXMLLoader.load(getClass().getResource("Game.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    

    @FXML protected void signOut(){
        GuiClient.signout();
        try {
            // TODO: implement actual sign out
            Parent root = FXMLLoader.load(getClass().getResource("GuiClient.fxml"));
            GuiClient.setScene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
