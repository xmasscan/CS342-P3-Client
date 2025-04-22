package connect4fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
