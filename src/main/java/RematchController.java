import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class RematchController {

    Client clientThread = GuiClient.clientThread;
    String file = null;
    @FXML private Text write;
    @FXML private Button acceptRematch;
    @FXML private Button rejectRematch;


    public void rematchYes() {
        try {
            clientThread.out.writeObject(Message.acceptRematch(true, clientThread.lastPlayer));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        

    }

    public void rematchNo() {
        try {
            clientThread.out.writeObject(Message.acceptRematch(false, clientThread.lastPlayer));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }


    
}
