// Classe Journal pour le DS2011 de parallelisme

public class Journal {

    private int lireAct;
    private int lireAtt;
    private int ecrireAct;
    private int ecrireAtt;
    
    public synchronized void debut_lire() {
	while(ecrireAct != 0 || ecrireAtt >= 3) {
	    lireAtt++;
	    this.wait();
	    lireAtt--;
	    this.notifyAll(); //place du notify ?
	}
	lireAct++;
    }

    public String lire() {
	//effectue la lecture
	return "";
    }

    public synchronized void fin_lire() {
	lireAct--;
	this.notifyAll();
	return;
    }
        
    public synchronized void debut_ecrire {
	while(ecrireAct != 0 || lireAct != 0) {
	    ecrireAtt++;
	    this.wait();
	    ecrireAtt--;
	    this.notifyAll(); //place du notify ?
	}
	ecrireAct++;
    }

    public void ecrire(String s) {
	//ecriture
	return;
    }

    public void fin_ecrire() {
	ecrireAct--;
	this.notifyAll();
	return ;
    }
