package ex1;

public class IntEvt {

	private boolean ecrit;
	private int val;
	private Thread _me;

	public IntEvt(){
		ecrit = false;
		_me = Thread.currentThread();
	}


	// rend la valeur de l'evenement ou bloque l'appelant
	public int read() throws InterruptedException{
		System.out.println("Tentative de lecture");
		synchronized (this) {
			if(!ecrit){
				System.out.println("J'attend");
				this.wait();
			}		
		}
		System.out.println("La valeur est : "+val);
		return val;
	}

	//ecrit une valeur dans l'evenement
	public synchronized void  write(int i){
		System.out.println("Tentative d'écriture");
		if( _me ==  Thread.currentThread()){

			if(!ecrit){
				val = i;
				ecrit = true;
				System.out.println("Ecriture réalisée");
				this.notifyAll();


			}else
				System.out.println("Ecriture déjà faite");
		}else
			System.out.println("Vous n'etes pas le thread qui m'a cree");
	}


public static void main (String [] arg) throws InterruptedException{
	IntEvt e = new IntEvt();
	ThreadLecteur t1 = new ThreadLecteur(e);
	ThreadLecteur t2 = new ThreadLecteur(e);
	ThreadLecteur t3 = new ThreadLecteur(e);

	t1.start();
	t2.start();

	try{
		Thread.currentThread();
		//do what you want to do before sleeping
		Thread.sleep(3000);//sleep for 1000 ms
		//do what you want to do after sleeptig
	}
	catch(InterruptedException ie){
		//If this thread was intrrupted by nother thread
	}

	//On écrit la valeur
	e.write(5);

	t3.start();

}
}


