

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
    @FXML private TextField password;
    @FXML private Button signOnButton;

    // So we don't have to write GuiClient.clientThread every time
    Client clientThread = GuiClient.clientThread;

    @FXML protected void attemptSignIn(ActionEvent event) {
        String user = usernameField.getText();
        String pass = password.getText();

        // Attempt to sign in to the server!
        clientThread.signOn(user, pass);

        // If Login was successful, change screens
        if(clientThread.loggedIn){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                GuiClient.setScene(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        try {
//        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));}
//        catch (IOException e) {
//			System.err.println("Fatal Error:" + e);
//			e.printStackTrace();
//        }
    }

    
}
