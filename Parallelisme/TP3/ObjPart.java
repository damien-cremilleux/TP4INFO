package ex2;

import java.util.concurrent.atomic.AtomicBoolean;

public class ObjPart {
	
	private AtomicBoolean occupe;
	private int val;
	
	public ObjPart(int v){
		val = v;
		occupe.set(false);
	}
	
	public void ecrire(int v) throws AutreChoseException, InterruptedException{
		
		if(occupe.getAndSet(true))
		{
			throw new AutreChoseException(); 
		}	
			System.out.println("Ecriture");
			val = v;
			Thread.sleep(2000);
			occupe.set(false);	
	}
	
	public int lire() throws InterruptedException, AutreChoseException{
		if(occupe.getAndSet(true))
		{
			throw new AutreChoseException(); 
		}	
			System.out.println(Thread.currentThread()+" Lecture : "+val);
			Thread.sleep(2000);
			occupe.set(false);	
			return val;
	}
	
	
	public static void main (String [] arg) throws InterruptedException{
		ObjPart o = new ObjPart(0);
		ThreadLecteur t1 = new ThreadLecteur(o);
		ThreadLecteur t2 = new ThreadLecteur(o);
		ThreadLecteur t3 = new ThreadLecteur(o);
		ThreadRedacteur t4 = new ThreadRedacteur(o);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

			}
}
