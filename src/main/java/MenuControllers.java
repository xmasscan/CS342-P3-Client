import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MenuControllers {
    @FXML private Button signOut;
    @FXML private Button startGame;
    @FXML private GridPane full;
    @FXML private Text waits;

    @FXML private Button Ai;
    @FXML private Button Player;
    @FXML private Button Game;

    @FXML static private Text currentUser;
    @FXML static private Text Gold;
    @FXML static private Text Elo;
    @FXML static private ImageView image;
    @FXML private Text statusText;

    Client clientThread = GuiClient.clientThread;

    public void initialize() {
        // If this isn't changed, then the user has returned from a match!
        System.out.println(clientThread.matched);
        if(clientThread.matched){
            // Update Status with win!
            if(clientThread.winner == 1){
                Platform.runLater(()->{
                    statusText.setText("You Won!");
                });
            }
            // Update status with loss :(
            else if (clientThread.winner == 0){
                Platform.runLater(()->{
                    statusText.setText("You Lost!");
                });
            } else {
                Platform.runLater(()->{
                    statusText.setText("Connect4 played perfectly is allways a draw!");
                });
            }
            clientThread.winner = 0;
        }
    }

    // When this is implemented later, we should have a way to store this in the client thread and pull info from here
    // clientThread should handle current info of current player, GUI should read from it with getters
    @FXML static public void updateInformation(ServerMessage msg){
        currentUser.setText("Current User: " + msg.argv.get(0));
        Gold.setText("Gold: " + msg.argv.get(1));
        Elo.setText("Elo: " + msg.argv.get(2));
        Image temp = new Image("image" + msg.argv.get(3) + ".png");
        image.setImage(temp);
    }

    @FXML protected void startGame(){
        // Update Status to reflect attempt
        statusText.setText("Attempting Connection...");
        // Attempt to sign in to the server!
        clientThread.connect();
    }

    @FXML protected void waiting(){
        clientThread.startGame();
        try {
           GuiClient.setScene(FXMLLoader.load(getClass().getResource("Game.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML protected void signOut(){
        try {
            // TODO: implement actual sign out
            Parent root = FXMLLoader.load(getClass().getResource("GuiClient.fxml"));
            GuiClient.setScene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
