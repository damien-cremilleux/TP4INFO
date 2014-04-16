package calcul;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PointImpl extends UnicastRemoteObject implements Point {
    private float x;
    private float y;

    public float getX() throws RemoteException {
        return x;
    }

    public float getY() throws RemoteException {
        return y;
    }

    public void setX(float x) throws RemoteException {
        this.x = x;
    }

    public void setY(float y) throws RemoteException{
        this.y = y;
    }

    public PointImpl(float x, float y) throws RemoteException {
        this.x = x;
        this.y = y;
    }

    public PointImpl() throws RemoteException {
        this.x = 0;
        this.y = 0;
    }
}
