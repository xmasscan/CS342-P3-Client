

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
import javafx.stage.Stage;




public class LoginControllers {
    @FXML private TextField usernameField;
    @FXML private PasswordField password;
    @FXML private Button signOnButton;

    // So we don't have to write GuiClient.clientThread every time
    Client clientThread = GuiClient.clientThread;

    @FXML protected void attemptSignIn(ActionEvent event) {
        String user = usernameField.getText();
        String pass = password.getText();

        // Attempt to sign in to the server!
        clientThread.signOn(user, pass);

        // If Login was successful, change screens
        
    }

    @FXML static public void startGame () { 
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
