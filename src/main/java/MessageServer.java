import java.io.Serializable;
import java.util.ArrayList;

public class MessageServer implements Serializable {
    static final long serialVersionUID = 42L;
    
    String message;


    public void setUpGame(String GameID, ArrayList<ArrayList<Integer>> board, Integer numCollums, Integer numRows, ArrayList<String> chat, Integer numChats){
        message = "1:" + GameID + "," + "2:";
        for (int i = 0; i <numCollums; i++) {
            message += "{";
            for (int j = 0; j < numRows; j++) {
                message += " " + board.get(i).get(j).toString();
            }
            message += "}";
        } 
        message += ",3:" + numCollums.toString() + ",4:" + numRows.toString();

        message += ",5:";


        for (int i = 0; i < numChats; i++) {
            message += "_" + chat.get(i);
        }   

        message += ",6:" + numChats.toString();

    }



}
