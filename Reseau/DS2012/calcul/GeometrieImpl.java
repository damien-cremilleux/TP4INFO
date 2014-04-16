package calcul;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Math;
import java.rmi.registry.*;
import java.rmi.Naming;
import java.rmi.server.RemoteServer;

public class GeometrieImpl extends UnicastRemoteObject implements Geometrie {

    public float distance(Point p) throws java.rmi.RemoteException {
        return (float)Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
    }

    public void deplace(Point p, float dx, float dy) throws java.rmi.RemoteException {
        p.setX(p.getX() + dx);
        p.setY(p.getY() + dy);
    }

    public GeometrieImpl() throws RemoteException {
        super(0);
    }

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate RmiServer
        GeometrieImpl obj = new GeometrieImpl();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RmiServer", obj);
        System.out.println("PeerServer bound in registry");
    }


}
