import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class WaitingControllers {
    
    public void startGame () {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
            GuiClient.setScene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

