package calcul;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Geometrie extends Remote {
    //calcul de la distance à l'origine
    float distance(Point p) throws java.rmi.RemoteException;

    //déplace, c'est à dire modifie les coordonnées du point p
    void deplace(Point p, float dx, float dy) throws java.rmi.RemoteException;
}
