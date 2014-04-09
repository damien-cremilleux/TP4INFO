package chatNonMultithread;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
*   This is the remote interface that describes the methods
*   that the client can call on the server
*/

public interface ChatServer extends Remote {
    public void connect(ClientDistant ref) throws RemoteException;
    public void disconnect(ClientDistant ref) throws RemoteException;
    public void sendmsg(String m) throws RemoteException;
}
