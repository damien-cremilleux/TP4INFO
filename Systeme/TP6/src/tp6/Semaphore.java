package tp6;

import java.util.LinkedList;

/**
 * @author lholy
 * @author dcremill
 */
public class Semaphore {

	/* value of the semaphore */
	private int cpt;

	/* List of then thread stopped by the semaphore */
	private LinkedList<Object> waitingThread;

	public void init(int i) {
		cpt = i;
		waitingThread = new LinkedList<>();
	}

	public void p() throws InterruptedException {
		boolean test = false;

		Object o = new Object(); /* Objet quelconque, inutile */

		synchronized (o) {
			synchronized (this) { /* pour protéger cpt */
				if (cpt == 0) {
					test = true;
					waitingThread.add(o);
				} else {
					cpt--;
				}
			}
			if (test) {
				System.out.println("je vais me bloquer");
				o.wait();
			}
		}
	}

	public synchronized void v() throws InterruptedException {
		if (cpt == 0 && !(waitingThread.isEmpty())) {
			Object o = waitingThread.poll(); /* On récupère le 1er objet, */
			synchronized (o) {
				o.notify(); /* réveil du thread bloqué sur o */
			}
		} else {
			cpt++;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Semaphore s = new Semaphore();
		s.init(1);

		/* création des threads */
		ThreadTP6 t1 = new ThreadTP6(s, "t1");
		ThreadTP6 t2 = new ThreadTP6(s, "t2");
		ThreadTP6 t3 = new ThreadTP6(s, "t3");

		/* on les lance */
		t1.start();
		t2.start();
		t3.start();

	}

}
