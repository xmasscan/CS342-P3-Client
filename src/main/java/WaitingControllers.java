import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class WaitingControllers {
    
    static public void startGame () { 
        runGame();
    }

    static Class hello;

    static public void runGame() {
        try {
            Parent root = FXMLLoader.load(hello.getResource("Game.fxml"));
            GuiClient.setScene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

