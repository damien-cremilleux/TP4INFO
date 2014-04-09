package chatNonMultithread;

import java.rmi.RemoteException;

public interface ClientDistant extends java.rmi.Remote{
	public void msg(String m) throws RemoteException;
}
