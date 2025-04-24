import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class menuController {
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
                Parent root = FXMLLoader.load(getClass().getResource("Waiting.fxml"));
                GuiClient.setScene(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    @FXML protected void signOut(){
        GuiClient.signout();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GiuClient.fxml"));
            GuiClient.setScene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
