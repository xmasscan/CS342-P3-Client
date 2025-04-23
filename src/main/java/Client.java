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

	boolean loggedIn = false;
	
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
					MessageServer message = (MessageServer) in.readObject();
					System.out.println(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

    }

	public void signOn(String username){
		Message msg = Message.signOn(username);
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
			throw new RuntimeException("Invalid Message!");
		}
	}


}
