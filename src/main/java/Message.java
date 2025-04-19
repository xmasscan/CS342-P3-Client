import java.io.Serializable;

public class Message implements Serializable {
    static final long serialVersionUID = 42L;
    boolean isStatusUpdate;
    boolean isMove;
    boolean isMessage;
    boolean isSignOn;
    boolean createGame;

    Integer currentColloum;
    Integer move;
    Integer typeOfGame;

    String message;
    String recievingPlayer;
    String userName;
    String password;


    public void chat(String input, String user, String player){
       message = input;
       userName = user;
       recievingPlayer = player;
       isSignOn = false;
    }

    public void signOn(String user, String secret){
        userName = user;
        password = secret;
        isSignOn = true;
    }

    public void statusUpdate(String user, int coordinate){
        userName = user;
        currentColloum = new Integer(coordinate);
        isStatusUpdate = true;
    }

    public void move(String user, int coordinate){
        userName = user;
        currentColloum = new Integer(coordinate);
        isMove = true;

    }

    public void createGame(String user, Integer game, String player)  {
        userName = user;
        typeOfGame = game;
        recievingPlayer = player;
    }
}
