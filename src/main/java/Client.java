import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{
	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;

	int moveOrder;
	int lastPiece;

	Boolean[][] board;

	// User States
	// Is user logged into the server?
	boolean loggedIn = false;
	// Is user connected to a game?
	boolean connected = false;
	// Is user in a live match?
	boolean matched = false;
	int wait = 0;
	boolean winner = false;

	// User Info
	String username;

	Consumer<ServerMessage> msgClient;

	Client(Consumer<ServerMessage> call){
	
		msgClient = call;
	}

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
			
			try {
					ServerMessage message = (ServerMessage) in.readObject();
					// updateInformation handler
					msgClient.accept(message);
					
					if (message.messageType == 2) {
						int col = Integer.parseInt(message.argv.get(0));
						moveOrder = 0;
					}
					else if (message.messageType == 5) {
						MenuControllers.updateInformation(message);
					}
					else if (message.messageType == 4) {

					}
					// inMatch handling
					else if (message.messageType == 6) {
						moveOrder = Integer.parseInt(message.argv.get(0));
						this.connected = true;
						this.matched = true;
					}
					else if (message.messageType == 7)
					{
						
					}
					
					System.out.println(message);
					System.out.println(message.messageType);
					System.out.println(message.argv.get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
	

    }

	public void findSpace(int col){
		int indexOfMoved = 0;
		for (int i = 6; i >= 0; i--){
			if (!board[col][i].booleanValue()) {
				board[col][i] = true;
				indexOfMoved=i;
				lastPiece = indexOfMoved;
				break;
			}
		}
		System.out.println("prints for findSpace: col " + "" + col + " row " + "" + lastPiece);
	}

	// TODO: More Descriptive Commenting
	/* Functions to check that the move is find and everything works */
	public Boolean isMyTurn(){
		if (moveOrder == 0) {
			return true;
		}
		return false;
	}

	//returns true if the column i	s not full
	// TODO: Check if column is full
	public Boolean checkValidMove(int col){
		Boolean and = new Boolean(true);
		for (int i = 0; i < 7; i++){
			and = new Boolean(and.booleanValue() && board[col][i].booleanValue());
		}
		return new Boolean(!and.booleanValue());
	}

	public void makeMove(int col) {
		Message msg = Message.move(col);
		try {
			out.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			moveOrder = 1;
			//add the thing to the game
			//gives the position of the piece from top where the top = 0 and bottom = 6
			int indexOfMoved = 0;
			for (int i = 6; i >= 0; i--){
				if (!board[col][i].booleanValue()) {
					board[col][i] = true;
					indexOfMoved=i;
					break;
				}
			}
			lastPiece = indexOfMoved;
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	//gives the position of the peice from top where the top = 0 and bottom = 6
	public int whichColor() {
		return lastPiece;
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
				moveOrder = Integer.parseInt(response.argv.get(0));
				this.connected = true;

				// TODO: determine if handling required
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
	}

	public void connect(){
		Message msg = Message.connect();
		// Send Connection Request
		try{
			out.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a Chat Message to the Server
	 *
	 * @param username
	 * 	The name of the user who sent the message.
	 * @param message
	 * 	 The message to send in chat!
	 */
	public void send(String username, String message) {

		Message msg = Message.chat(username,message);
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
