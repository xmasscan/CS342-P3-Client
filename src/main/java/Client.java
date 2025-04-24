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

	// Is user logged into the server?
	boolean loggedIn = false;
	// Is user connected to a game?
	boolean connected = false;
	
	public void run() {
		
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
			if (loggedIn) {
				try {
					ServerMessage message = (ServerMessage) in.readObject();
					if (message.messageType == 5) {
						MenuControllers.updateInformation(message);
					} else if (message.messageType == 4) {

					} else if (message.messageType == 6) {
						WaitingControllers.startGame();
					}
					else if (message.messageType == 7) {
						
					}
					
					System.out.println(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

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
		synchronized(in) {
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
	}

	public void connect(){
		Message msg = Message.connect();
		// Send Connection Request
		synchronized(in) {
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
		if(msg.messageType == 0 || msg.messageType == 6 || msg.messageType == 7){
			
			return true;
		}
		else if(msg.messageType == 1){
			return false;
		}
		else{
			throw new RuntimeException("Invalid Message!");
		}
	}


}
