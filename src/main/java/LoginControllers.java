

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
    static Client clientThread = GuiClient.clientThread;

    public static void updateUsername(String username){
        clientThread.username = username;
    }

    @FXML protected void attemptSignIn(ActionEvent event) {
        String user = usernameField.getText();
        String pass = password.getText();

        // Attempt to sign in to the server!
        clientThread.signOn(user, pass);
    }

    
}
