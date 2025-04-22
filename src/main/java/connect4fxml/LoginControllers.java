package connect4fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class LoginControllers {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML protected void togglePassword(ActionEvent event) {
        passwordField.setDisable(!passwordField.isDisabled());
    }

    @FXML protected void attemptSignIn(ActionEvent event) {
        String username = usernameField.getText();
        String password = "";
        if(!passwordField.isDisabled()) {
            password = passwordField.getText();
        }

    }

    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("fxml_login.fxml"));
    
        Scene scene = new Scene(root, 300, 275);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }


    
    
}
