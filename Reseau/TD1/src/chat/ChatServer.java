package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
*   This is the remote interface that describes the methods
*   that the client can call on the server
*/

public interface ChatServer extends Remote {


	/**
	*   The client retrieves all the messages that are on the server
	*/
    public String[] getAllMessages() throws RemoteException;

	/**
	*   The client adds a new message to the list
	*/
    public void addNewMessage(String m) throws RemoteException;


}
