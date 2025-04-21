package connect4fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class LoginControllers {
    @FXML private PasswordField passwordField;

    @FXML protected void togglePassword(ActionEvent event) {
        passwordField.setDisable(!passwordField.isDisabled());
    }
}
