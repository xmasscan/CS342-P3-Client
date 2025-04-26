
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class GameControllers implements Initializable {
    @FXML private VBox first;
    @FXML private VBox second;
    @FXML private VBox third;
    @FXML private VBox fourth;
    @FXML private VBox fifth;
    @FXML private VBox sixth;
    @FXML private ScrollPane chatbox;
    @FXML private VBox chatContent;
    @FXML private TextField messageTextField;

    static Client clientThread = GuiClient.clientThread;

    static GameControllers theGameControllers;

    /**
     * updateChat
     * When the client receives a chat message, update the GUI's chat box to accommodate!
     * @param username
     *  A String containing the username of the user who sent the message
     * @param message
     *  A string containing the user's message!
     */
    public static void updateChat(String username, String message) {
        String toText = username + ": " + message;
        System.out.println("Message received: " + toText);
        // Update GUI
        VBox chatContent = theGameControllers.chatContent;
        if(chatContent != null) {
            chatContent.getChildren().add(new Text(toText));
        }
        else{
            System.out.println("Chat content is null");
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        String message = messageTextField.getText();
        System.out.println("Message sent: " + clientThread.username + ": " +  message);
        clientThread.send(clientThread.username,message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {theGameControllers = this;}

    @FXML public void firstMove(){
        // DEBUG PRINT
        // TODO: remove me!
        System.out.println("Row 1 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(0)) {
            clientThread.makeMove(0);
            Circle chip = new Circle(42.0);
            chip.setFill(Color.RED);
            first.getChildren().set(clientThread.whichColor(), chip);

        } else if (!clientThread.isMyTurn()) {
            return;
        } else {

        }
    }

    @FXML public void secondMove(){
        System.out.println("Row 2 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(1)) {
            clientThread.makeMove(1);
            Circle piece = new Circle(42.0);
            piece.setFill(Color.RED);
            second.getChildren().set(clientThread.whichColor(), piece);

        } else if (!clientThread.isMyTurn()) {
            return;
        } else {

        }
    }

    @FXML public void thirdMove(){
        System.out.println("Row 3 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(2)) {
            clientThread.makeMove(2);
            Circle piece = new Circle(42.0);
            piece.setFill(Color.RED);
            third.getChildren().set(clientThread.whichColor(), piece);

            } else if (!clientThread.isMyTurn()) {
                // if it isn't your turn, buzz off!
                return;
            }
            else {

            }
    }


    @FXML public void fourthMove(){
        System.out.println("Row 4 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(3)) {
            clientThread.makeMove(3);
            Circle piece = new Circle(42.0);
            piece.setFill(Color.RED);

            fourth.getChildren().set(clientThread.whichColor(), piece);

        } else if (!clientThread.isMyTurn()) {
            return;
        } else {

        }
    }

    @FXML public void fifthMove(){
        System.out.println("Row 5 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(4)) {
            clientThread.makeMove(4);
            Circle piece = new Circle(42.0);
            piece.setFill(Color.RED);

            fifth.getChildren().set(clientThread.whichColor(), piece);

        } else if (!clientThread.isMyTurn()) {
            return;
        } else {

        }
    }

    @FXML public void sixthMove(){
        System.out.println("Row 6 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(5)) {
            clientThread.makeMove(5);
            Circle piece = new Circle(42.0);
            piece.setFill(Color.RED);

            sixth.getChildren().set(clientThread.whichColor(), piece);

        } else if (!clientThread.isMyTurn()) {
            return;
        } else {

        }
    }

    public static void updateColumn(Integer col){
        VBox toChange;
        if (col == 0){
            toChange = theGameControllers.first;
        } else if (col == 1) {
            toChange = theGameControllers.second;
        } else if (col ==2){
            toChange = theGameControllers.third;
        } else if (col == 3){
            toChange = theGameControllers.fourth;
        } else if (col == 4){
            toChange = theGameControllers.fifth;
        } else {
            toChange = theGameControllers.sixth;
        }

        Circle piece = new Circle(42.0);
        piece.setFill(Color.YELLOW);
        toChange.getChildren().set(clientThread.whichColor(), piece);
    }
}
