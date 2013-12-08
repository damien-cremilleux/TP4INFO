package parking;


public class Voiture extends Thread {

	private ParkingV2 parking;
	private boolean entreeNord;
	
	public Voiture(ParkingV2 p, boolean entreeN){
		parking = p;
		entreeNord = entreeN;
	}
	
	public void run(){
		if(entreeNord)
			parking.entreeNord();
		else
			parking.entreeSud();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		parking.sortieVoiture();
	}
	

	public static void main(String[] args){
		
		System.out.println("Début");
		
		ParkingV2 p = new ParkingV2();
		
		Voiture v1 = new Voiture(p, true);
		v1.start();
		
		Voiture v2 = new Voiture(p, false);
		v2.start();
		
		Voiture v3 = new Voiture(p, true);
		v3.start();
		
		Voiture v4 = new Voiture(p, false);
		v4.start();
		
		Voiture v5 = new Voiture(p, true);
		v5.start();
		
		System.out.println("Fin");
		
	}
}