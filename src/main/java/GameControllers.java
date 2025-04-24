
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class GameControllers {
    @FXML static private Button first;
    @FXML static private Button second;
    @FXML static private Button third;
    @FXML static private Button fourth;
    @FXML static private Button fifth;
    @FXML static private Button sixth;

    static Client clientThread = GuiClient.clientThread;

    @FXML static public void firstMove(ActionEvent jo){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(1)) {
            clientThread.makeMove(1);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);
            
        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML static public void secondMove(ActionEvent jo){
        System.out.println("no");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(2)) {
            clientThread.makeMove(2);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);
            
        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML static public void thirdMove(ActionEvent jo){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(3)) {
            clientThread.makeMove(3);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);
            
        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }


    @FXML static public void fourthMove(ActionEvent jo){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(4)) {
            clientThread.makeMove(4);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);
            
        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML static public void fifthMove(ActionEvent jo){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(5)) {
            clientThread.makeMove(5);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);
            
        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML static public void sixthMove(ActionEvent jo){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(6)) {
            clientThread.makeMove(6);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);
            
        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }
    
}
