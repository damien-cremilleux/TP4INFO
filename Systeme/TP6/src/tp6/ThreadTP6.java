package tp6;

/**
 * @author lholy
 * 
 */
public class ThreadTP6 extends Thread {

	/* semaphore commun à tout les threads */
	private Semaphore sem;
	public String id;

	public ThreadTP6(Semaphore s, String nom) {
		sem = s;
		id = nom;
	}

	public void run() {
		System.out.println("Coucou " + this.getName());
		try {
			sem.p();
			Thread.sleep(2000);
			System.out
					.println("Je suis dans la zone protégé " + this.getName());
			sem.v();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("je suis sorti ! " + this.getName());
	}

}
