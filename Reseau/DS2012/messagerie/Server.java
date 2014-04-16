// DS2012 - Damien Crémilleux
package messagerie;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
    public static final int PORT= 9988;

    private ServerSocket server;
    private PrintWriter out;
    private BufferedReader in;

    private static final int NB_BOITE = 10;
    private LinkedList<String>[] tabBoite = new LinkedList[NB_BOITE];
    private boolean[] tabEtat = new boolean[NB_BOITE];

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        super();

        for(int i = 0; i < tabBoite.length; i++) {
            tabBoite[i] = new LinkedList<String>();
        }

        try {
            server = new ServerSocket(PORT);

            while(true) {
                //Ouverture du socket
                Socket socket = server.accept();
                try {
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                lire_requetes();

                //Fermeture du socket
                try {
                    out.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lire_requetes() {
        boolean end = false;

        do {
            try {
                String message = in.readLine();
                if(message == null) {
                    end = true;
                } else {
                    // On ne vérifie jamais la cohérence du numéro demandé, ni le fait que la boite ne soit pas vide
                    String[] command = message.split(" ");
                    if(command.length == 3) {
                        if(command[0].equals("SEND")) {
                            int numBoite = Integer.parseInt(command[1]);
                            tabBoite[numBoite].push(command[2]);
                            out.println("OK");
                        } else {
                            out.println("ERREUR");
                        }
                    }

                    if(command.length == 2) {
                        if(command[0].equals("RECV")) {
                            int numBoite = Integer.parseInt(command[1]);
                            String msg = tabBoite[numBoite].removeLast();
                            out.println("MESG "+numBoite+" "+msg );
                        } else {
                            out.println("ERREUR");
                        }
                    }

                    if(command.length != 2 && command.length != 3)
                        out.println("ERREUR");
                }
            } catch (IOException e) {
                end = true;
                e.printStackTrace();
            }
        } while (!end);
    }

}
