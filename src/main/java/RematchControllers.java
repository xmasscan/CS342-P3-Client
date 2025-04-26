import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class RematchControllers {

    Client clientThread = GuiClient.clientThread;
    String file = null;
    @FXML private Text write;
    @FXML private Button acceptRematch;
    @FXML private Button rejectRematch;


    public void rematchYes() {
        try {
            clientThread.out.writeObject(Message.acceptRematch(true, clientThread.lastPlayer));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void rematchNo() {
        try {
            clientThread.out.writeObject(Message.acceptRematch(false, clientThread.lastPlayer));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    
}
