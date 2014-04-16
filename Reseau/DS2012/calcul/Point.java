package calcul;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Point extends Remote {

    public float getX() throws RemoteException;

    public float getY() throws RemoteException;

    public void setX(float x) throws RemoteException;

    public void setY(float y) throws RemoteException;
}

