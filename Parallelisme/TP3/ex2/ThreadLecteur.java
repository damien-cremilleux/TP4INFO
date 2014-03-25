package ex2;

public class ThreadLecteur extends Thread {
    ObjPart o;

    public ThreadLecteur(ObjPart obj) {
	o = obj;
    }
	
    public void faireAutreChose()
    {
	System.out.println(Thread.currentThread()+" : je fais autre chose");
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void run(){
	while(true){
	    try {
		System.out.println(Thread.currentThread()+"Tentative de lecture");
		o.lire();
		Thread.sleep(2000);
	    } catch (AutreChoseException e) {
		// TODO Auto-generated catch block
		this.faireAutreChose();
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}
