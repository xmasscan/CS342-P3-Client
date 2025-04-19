import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;

public class MessageServer implements Serializable {
    static final long serialVersionUID = 42L;
    
    boolean good;
    Integer messageType;
    String message;


    public void setUpGame(String GameID, ArrayList<ArrayList<Integer>> board, Integer numCollums, Integer numRows, ArrayList<String> chat, Integer numChats){
        messageType = new Integer(1);
        good = true;
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
            message += " " + chat.get(i);
        }   

        message += " ,6:" + numChats.toString();
    }


    //implement giving player their gold/elo/ and a active client list
    public void signInReturn(Integer elo, Integer gold, Integer visual, ArrayList<String> clients, Integer numClients) {
        good = true;
        messageType = 2;
        message = "1:" + elo.toString() + ",2:" + gold.toString() + ",3:" + visual.toString();

        message += ",4:";

        for (int i = 0; i < numClients; i++) {
            message += "_" + clients.get(i);
        }

    }

    //implement giving replies

    public void reply(String err) {
        good = false;
        messageType = 3;
        message = err;
    }

    public void winStatus(Boolean hasWon) {
        good = true;
        messageType = 4;
        message = hasWon.toString();
    }

    public Integer Type(){
        return messageType;
    }

    public ArrayList<ArrayList<Integer>> getBoard(){
        if(!messageType.equals(new Integer(1))){
            return null;
        }

        int boardBegins = message.indexOf("2:", 0) + 2;
        int boardEnds = message.indexOf("3:", boardBegins);

        String board = new String(message.substring(boardBegins, boardEnds+1));

        ArrayList<ArrayList<Integer>> realBoard = new ArrayList<ArrayList<Integer>>();

        while (board.indexOf("}") != -1) {
            String collom = new String(board.substring(board.indexOf("{")+1, board.indexOf("}")+1));
            board = new String(board.substring(board.indexOf("}") + 1));

            ArrayList<Integer> newCollum = new ArrayList<Integer>();
            while(collom.indexOf(" ") != -1) {
                int nextRow =  collom.substring(1).indexOf(" ");
                if (nextRow == -1) {
                    collom = new String("");
                    break;
                }
                String type = new String(collom.substring(1,nextRow));
                collom = collom.substring(nextRow+1);

                newCollum.add(Integer.parseInt(type));
                
            }
            realBoard.add(newCollum);
        }

        return realBoard;

    }



    




}
