import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{
	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;

	int moveOrder;
	int lastPeice;

	Boolean[][] board;

	// Is user logged into the server?
	boolean loggedIn = false;
	// Is user connected to a game?
	boolean connected = false;
	// Is user in a live match?
	int matched = -1;

	public void run() {
		
		board = new Boolean[6][7];
		for (int i = 0; i < 6;i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j] = false;
			}
		}

		try {
			socketClient= new Socket("127.0.0.1",5555);
	    	out = new ObjectOutputStream(socketClient.getOutputStream());
	    	in = new ObjectInputStream(socketClient.getInputStream());
	   	 	socketClient.setTcpNoDelay(true);

		}
		catch(Exception e) {
			e.printStackTrace();
		}

		while(true) {
			// If the user logged in & connected to a match, begin waiting for messages.
			if (loggedIn && connected) {
				try {
					ServerMessage message = (ServerMessage) in.readObject();
					// updateInformation handler
					if (message.messageType == 5) {
						MenuControllers.updateInformation(message);
					} else if (message.messageType == 4) {

					}
					// inMatch handling
					else if (message.messageType == 6) {
						if(matched == -1) {
							if (message.argv.get(0).compareTo("0") == 0) {
								matched = 0;
							} else if (message.argv.get(0).compareTo("1") == 0) {
								matched = 1;
							}
						}
					}
					else if (message.messageType == 7)
					{
						
					}
					
					System.out.println(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

    }


	/*functions to check that the move is find and everything works
	 */
	public Boolean isMyTurn(){
		if (moveOrder == 0) {
			return true;
		}
		return false;
	}

	//returns true if the collumn is not full
	public Boolean checkValidMove(int collumn){
		Boolean and=new Boolean(true);
		for (int i = 0; i < 7; i++){
			and= and && board[collumn][i];
		}
		return new Boolean(!and);
	}

	public void makeMove(int collumn) {
		Message msg = Message.move(collumn);
		try {
			out.writeObject(msg);
		} catch (Exception e) {
			System.out.print("womp womp");
		}

		try{
			ServerMessage response = (ServerMessage) in.readObject();
			
				moveOrder = 1;
				//add the thing to the game

				//gives the position of the peice from top where the top = 0 and bottom = 6
				int indexOfMoved;
				for (int i = 6; i >= 0; i++){
					if (!board[collumn][i]) {
						board[collumn][i] = true;
						indexOfMoved=i;
						break;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	//gives the position of the peice from top where the top = 0 and bottom = 6
	public int whichColor() {
		return lastPeice;
	}

	/**
	 * Passes a message generated in the GUI Client to the clientThread
	 * @param msg
	 * 	The Message Object to be "passed" to the thread
	 */
	public void passOnMessage(Message msg) {
		try{
			out.writeObject(msg);
		} catch (IOException e) {
			System.err.println("Fatal Error:" + e);
			e.printStackTrace();
		}
	}

	public void startGame(){
			// Wait for response
			try{
				ServerMessage response = (ServerMessage) in.readObject();

				if (response.messageType == 6) {

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public ServerMessage retrieveResponse(){
		ServerMessage response;
		try{
			response = (ServerMessage) in.readObject();
			return response;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void signOn(String username, String password){
		Message msg = Message.signOn(username, password);
		// Send Sign On Request to Server
		try{
			out.writeObject(msg);
		} catch (IOException e) {
			System.err.println("Fatal Error:" + e);
			e.printStackTrace();
		}

		// Wait for response
		try{
			ServerMessage response = (ServerMessage) in.readObject();
			if(validate(response)){
				this.loggedIn = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect(){
		Message msg = Message.connect();
		// Send Connection Request
		try{
			out.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Wait for response
		try{
			ServerMessage response = (ServerMessage) in.readObject();
			if(validate(response)){
				this.connected = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void beginMatch(){
		try{
			ServerMessage response = (ServerMessage) in.readObject();
			if(response.messageType == 6){
				matched = 0;
			}
			else if(response.messageType == 1){
				matched = 1;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a Chat Message to the Connect4 Server
	 * @param data
	 * 	The message to send in chat!
	 */
	public void send(String data) {

		Message msg = Message.chat(data);
		try {
			out.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Server Message Standard:
	// 0 = Accept
	// 1 = Reject
	public boolean validate(ServerMessage msg){
		if(msg.messageType == 0){
			
			return true;
		}
		else if(msg.messageType == 1){
			return false;
		}
		else{
			throw new RuntimeException("Invalid Message! Message type is: " + msg.messageType);
		}
	}


}
