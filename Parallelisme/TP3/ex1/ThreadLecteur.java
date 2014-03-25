package ex1;

public class ThreadLecteur extends Thread {
	IntEvt ev;
	
	public ThreadLecteur(IntEvt e) {
		ev = e;
	}
	
	public void run(){
		try {
			ev.read();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
