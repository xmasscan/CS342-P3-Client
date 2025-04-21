import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    // Soft requirement for Serializable Interface
    static final long serialVersionUID = 42L;
    int messageType;
    ArrayList<String> arguments;

    /**
     * Message is the wrapper that stores all the data required to make the message subclasses
     *
     * Attributes:
     * int: messageType
     *  The type of message stored within this object!
     *  0 = SignOn ; Init client as a proper user with a username
     *  1 = ConnectGame ; Create or connect to a game based on arguments
     *  2 = Move ; send an attempted move to server
     *  3 = Chat ; send chat message to server
     *  4 = StatusUpdate ; TBD, mostly just ambient/bg data that may be important
     * ArrayList<String> arguments
     *  Everything in these arguments will either be a String, int, or bool.
     *  Given the type, we know the type of each value in advance, so this data can be converted again when Deserialized.
     */
    public Message(int messageType, ArrayList<String> argv){
        this.messageType = messageType;
        this.arguments = argv;
    }


    public void SignOn(String user, String secret){
        // Identify this message as a "Sign On" message.
        this.messageType = 0;

    }

    /**
     * Constructs a "Chat Message" message to send to the server.
     * @param message
     *  The chat message for the user to send to the server.
     */
    public Message Chat(String message){
        // ID Message as a "Chat Message" message
        int messageType = 3;
        // Build arguments; Only need to send chat message!
        ArrayList<String> argv = new ArrayList<>();
        argv.add(message);
        // Create Message Object wtih desired contents
        return new Message(messageType, argv);
    }

    public void StatusUpdate(String user, int coordinate){
        userName = user;
        currentColloum = new Integer(coordinate);
        isStatusUpdate = true;
    }

    public void Move(String user, int coordinate){
        userName = user;
        currentColloum = new Integer(coordinate);
        isMove = true;

    }

    public void CreateGame(String user, Integer game, String player)  {
        userName = user;
        typeOfGame = game;
        recievingPlayer = player;
    }
}
