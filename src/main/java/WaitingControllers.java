import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class WaitingControllers implements Initializable {

    public void initialize(URL Location, ResourceBundle resources){
    
        Client clientThread = GuiClient.clientThread;
        clientThread.startGame();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
            GuiClient.setScene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    
}

