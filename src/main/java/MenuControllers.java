import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MenuControllers {

    Client clientThread = GuiClient.clientThread;

    @FXML protected void connect(ActionEvent event) {
        clientThread.connect();
        if(clientThread.connected){
            System.out.println("Connected to Game!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
                GuiClient.setScene(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Failed to connect!");
        }
    }
}
