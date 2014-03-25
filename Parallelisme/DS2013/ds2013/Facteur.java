package ds2013;

public class Facteur {

    private static final int nbThread = 10;

    private int[][] valeur; //la valeur a transmettre
    private int[][] etat; //indique si une valeur est a lire
    private boolean[][] tabReady; //indique l'etat d'attente

    public Facteur() {
	valeur = new int[nbThread][nbThread];
	etat = new int[nbThread][nbThread];
	tabReady = new boolean[nbThread][nbThread];
    }

    //envoi de la valeur value au processeur tid
    void send(int tid, int value) throws Exception {
	synchronized(this) {
	    etat[(int)Thread.currentThread().getId()][tid] = 1;
	    valeur[(int)Thread.currentThread().getId()][tid] = value;
	    this.notifyAll();
	    
	    while(etat[(int)Thread.currentThread().getId()][tid] == 1) {
		tabReady[(int)Thread.currentThread().getId()][tid] = true;
		this.wait();
		tabReady[(int)Thread.currentThread().getId()][tid] = false;
	    }
	}
    }
    
    // reception de la valeur du message envoye par le processus tid
    int recv(int tid) throws Exception {
	synchronized(this) {
	    while(etat[tid][(int)Thread.currentThread().getId()] == 0) {
		tabReady[tid][(int)Thread.currentThread().getId()] = true;
		this.wait();
		tabReady[tid][(int)Thread.currentThread().getId()] = false;
	    }
	    
	    int res = valeur[tid][(int)Thread.currentThread().getId()];
	    etat[tid][(int)Thread.currentThread().getId()] = 0;
	    this.notifyAll();
	    return res;
	}
    }

    //dit si le recepteur est pret (bloque en attente)
    //et si on peut donc emettre sans se bloquer
    boolean sendReady() {
	synchronized(this) {
	    boolean send = false;
	    for (int i = 0; i < nbThread; i++) {
		send = send || (tabReady[(int)Thread.currentThread().getId()][i] == true); //on regarde si au moins un thread est pret a recevoir
	    }
	    return send;
	}
    }
    
    //dit si l'emetteur est pret (bloque en attente)
    //et si on peut donc recevoir sans se bloquer
    boolean recvReady() {
	synchronized(this) {
	    boolean recv = false;
	    for (int i = 0; i < nbThread; i++) {
		recv = recv || (tabReady[i][(int)Thread.currentThread().getId()] == true); //on regarde si au moins un thread est pret a envoyer
	    }
	    return recv;
	}
    }

    //affiche le(s) cycles des interblocages s'il y en a
    //rend vrai s'il y a un interblocage
    /*boolean deadlockDetect() {
    //?	
    }*/
    
    
}	    
	    
	    
