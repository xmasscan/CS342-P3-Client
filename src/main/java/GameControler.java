
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




public class  GameControler  {
    @FXML private TextField usernameField;
    @FXML private TextField password;
    @FXML private Button signOnButton;

    @FXML protected void attemptSignIn(ActionEvent event) {
        String user = usernameField.getText();
        String pass = password.getText();
        ArrayList<String> signOnStrings = new ArrayList<String>();
        signOnStrings.add(user);
        signOnStrings.add(pass);
        Message signOnRequest = new Message(0,signOnStrings);
        
        GuiClient.clientThread.passOnMessage(signOnRequest);
        try {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));}
        catch (IOException e) {
			System.err.println("Fatal Error:" + e);
			e.printStackTrace();
        }
    }

    
}
