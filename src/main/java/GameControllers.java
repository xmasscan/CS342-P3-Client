
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class GameControllers implements Initializable {
    @FXML  private VBox first;
    @FXML  private VBox second;
    @FXML  private VBox third;
    @FXML  private VBox fourth;
    @FXML  private VBox fifth;
    @FXML  private VBox sixth;

    @FXML private Text t1test;
    @FXML private Text t2test;
    @FXML private Text t3test;
    @FXML private Text t4test;
    @FXML private Text t5test;
    @FXML private Text t6test;
    @FXML private Text t7test;
    @FXML private Text t8test;
    @FXML private Text t9test;
    @FXML private Text t10test;
    @FXML private Text t11test;
    @FXML private Text t12test;
    @FXML private Text t13test;
    @FXML private Text t14test;
    @FXML private Text t15test;
    @FXML private Text t16test;
    @FXML private Text t17test;
    @FXML private Text t18test;
    @FXML private Text t19test;
    @FXML private Text t20test;
    @FXML private Text t21test;
    @FXML private Text t22test;
    @FXML private Text t23test;
    @FXML private Text t24test;
    @FXML private Text t25test;
    @FXML private Text t26test;
    @FXML private Text t27test;
    @FXML private Text t28test;
    @FXML private Text t29test;
    @FXML private Text t30test;

    ArrayList<Text> messages = new ArrayList<Text>();

    static Client clientThread = GuiClient.clientThread;



    static GameControllers theGameControllers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        theGameControllers = this;
        messages.add(t1test);
        messages.add(t2test);
        messages.add(t3test);
        messages.add(t4test);
        messages.add(t5test);
        messages.add(t6test);
        messages.add(t7test);
        messages.add(t8test);
        messages.add(t9test);
        messages.add(t10test);
        messages.add(t11test);
        messages.add(t12test);
        messages.add(t13test);
        messages.add(t14test);
        messages.add(t15test);
        messages.add(t16test);
        messages.add(t17test);
        messages.add(t18test);
        messages.add(t19test);
        messages.add(t20test);
        messages.add(t21test);
        messages.add(t22test);
        messages.add(t23test);
        messages.add(t24test);
        messages.add(t25test);
        messages.add(t26test);
        messages.add(t27test);
        messages.add(t28test);
        messages.add(t29test);
        messages.add(t30test);

    }

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
        } else if (col==  4){
            toChange = theGameControllers.fifth;
        } else {
            toChange = theGameControllers.sixth;
        }

        Circle piece = new Circle(42.0);
        piece.setFill(Color.YELLOW);
        toChange.getChildren().set(clientThread.whichColor(), piece);
    }

    public static void updateMessages(int numMessages, ArrayList<String> chats) {
        for (int i = 0; i < numMessages; i++) {
            theGameControllers.messages.get(i).setText(chats.get(i));
        }
        for (int i = numMessages; i < 30; i++){
            theGameControllers.messages.get(i).setText("");
        }
        
    }

}
