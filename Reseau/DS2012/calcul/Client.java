package calcul;

import java.rmi.Naming;

public class Client {
    public static void main(String args[]) throws Exception {
        Geometrie obj = (Geometrie)Naming.lookup("//localhost/RmiServer");
        Point p1 = new PointImpl(0, 0);
        Point p2 = new PointImpl(4, 6);
        float d1 = obj.distance(p1);
        float d2 = obj.distance(p2);

        System.out.println(d1);
        System.out.println(d2);

        obj.deplace(p1, 1, 1);
        obj.deplace(p2, -4, -6);

        d1 = obj.distance(p1);
        d2 = obj.distance(p2);

        System.out.println(d1);
        System.out.println(d2);

        /* Affiche
           0.0
           7.2111025
           1.4142135
           0.0
        */
    }
}
