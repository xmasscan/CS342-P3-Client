import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class MenuControllers {
    @FXML private Button signOut;
    @FXML private Button startGame;

    @FXML private Button Ai;
    @FXML private Button Player;
    @FXML private Button Game;

    @FXML private Text currentUser;
    @FXML private Text Gold;
    @FXML private Text Elo;
    @FXML private ImageView image;

    Integer typeOfGame;

    Client clientThread = GuiClient.clientThread;


    @FXML protected void updateInformation(ServerMessage msg){
        currentUser.setText("Current User: " + msg.argv.get(0));
        Gold.setText("Gold: " + msg.argv.get(1));
        Elo.setText("Elo: " + msg.argv.get(2));
        Image temp = new Image("image" + msg.argv.get(3) + ".png");
        image.setImage(temp);
    }

    @FXML protected void startGame(){
        // Attempt to sign in to the server!
        clientThread.connect();

        // If Login was successful, change screens
        
            try {
                // TODO: create & change to "waiting.fxml" if you want to impl this
                // we locally update board anyways tho so we dont need this
                Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
                GuiClient.setScene(root);
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
