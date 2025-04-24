import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.text.Text;


public class WaitingControllers{

    Client clientThread = GuiClient.clientThread;
    String file = null;

    @FXML private Text WaitingText;

    public void initialize(){
        System.out.println("WaitingControllers.initialize");
        while(clientThread.matched == -1)
            clientThread.beginMatch();
        System.out.println(clientThread.matched);
        if(clientThread.matched == 0){
            try {
                GuiClient.setScene(FXMLLoader.load(getClass().getResource("Game.fxml")));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if(clientThread.matched == 1){
            try {
                GuiClient.setScene(FXMLLoader.load(getClass().getResource("GuiClient.fxml")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
}

