
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


public class GameControllers implements Initializable {
    @FXML  private VBox first;
    @FXML  private VBox second;
    @FXML  private VBox third;
    @FXML  private VBox fourth;
    @FXML  private VBox fifth;
    @FXML  private VBox sixth;

    static Client clientThread = GuiClient.clientThread;


    static GameControllers theGameControllers;

    @Override
    public void initialize(URL location, ResourceBundle resouces) {
        theGameControllers = this;
    }

    @FXML public void firstMove(){
        // DEBUG PRINT
        // TODO: remove me!
        System.out.println("Row 1 Clicked!");
        if (clientThread.isMyTurn() && clientThread.checkValidMove(1)) {
            clientThread.makeMove(0);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);
            first.getChildren().set(clientThread.whichColor(), peice);

        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML public void secondMove(){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(2)) {
            clientThread.makeMove(1);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);

        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML public void thirdMove(){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(3)) {
            clientThread.makeMove(2);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);

            } else if (!clientThread.isMyTurn()) {

            }
            else {

            }
    }


    @FXML public void fourthMove(){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(4)) {
            clientThread.makeMove(3);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);

        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML public void fifthMove(){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(5)) {
            clientThread.makeMove(4);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);

        } else if (!clientThread.isMyTurn()) {

        } else {

        }
    }

    @FXML public void sixthMove(){
        if (clientThread.isMyTurn() && clientThread.checkValidMove(6)) {
            clientThread.makeMove(5);
            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);

            first.getChildren().set(clientThread.whichColor(), peice);

        } else if (!clientThread.isMyTurn()) {

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

        

            Circle peice = new Circle(42.0);
            peice.setFill(Color.RED);
            toChange.getChildren().set(clientThread.whichColor(), peice);
    }

}
