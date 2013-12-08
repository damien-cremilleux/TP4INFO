package parking;

public class ParkingV2 {

	private final static int NB_PLACE = 3;
	private static int placesRestantesSortie;
	private static int placesRestantesNord;
	private static int placesRestantesSud;
	private static Object nord;
	private static Object sud;
	private static Object sortie;

	public ParkingV2() {
		placesRestantesSortie = 0;
		placesRestantesNord = (0 ) / 2;
		System.out.println("nombre de place nord : " + placesRestantesNord);
		placesRestantesSud = (NB_PLACE*2) / 2;
		System.out.println("nombre de place sud : " + placesRestantesSud);
		nord = new Object();
		sud = new Object();
		sortie = new Object();
	}

	public void entreeNord() {
		boolean estRentre = false;
		while (!estRentre) {
			synchronized (nord) {
				System.out.println("Nb place Nord : " + placesRestantesNord);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// sleep for 1000 ms

				if (placesRestantesNord != 0) {
					placesRestantesNord--;
					System.out.println("Une voiture rentre par le nord.");
					estRentre = true;
				}
			}
			synchronized (sortie) {
				if (placesRestantesSortie != 0 && !estRentre) {
					System.out
					.println("Une voiture entre par le nord car une voiture est déjà sortie");
					placesRestantesSortie--;
					estRentre = true;
				}
			}

			synchronized (sud) {
				if (placesRestantesSud != 0 && !estRentre) {
					System.out
					.println("Une voiture entre par le nord grace au sud");
					placesRestantesSud--;
					estRentre = true;
				}
			}
			synchronized(this){
				if (!estRentre) {
					try {
						System.out.println("Une voiture attend à l'entrée nord.");
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void entreeSud() {
		boolean estRentre = false;
		while (!estRentre) {
			synchronized (sud) {
				System.out.println("Nb place  Sud : " + placesRestantesSud);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// sleep for 1000 ms

				if (placesRestantesSud!= 0) {
					placesRestantesSud--;
					System.out.println("Une voiture rentre par le sud.");
					estRentre = true;
				}
			}
			synchronized (sortie) {
				if (placesRestantesSortie != 0 && !estRentre) {
					System.out
					.println("Une voiture entre par le sud car une voiture est déjà sortie");
					placesRestantesSortie--;
					estRentre = true;
				}
			}

			synchronized (nord) {
				if (placesRestantesNord != 0 && !estRentre) {
					System.out
					.println("Une voiture entre par le sud grace au nord");
					placesRestantesNord--;
					estRentre = true;
				}
			}
			synchronized(this){
				if (!estRentre) {
					try {
						System.out.println("Une voiture attend à l'entrée sud.");
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void sortieVoiture() {
		synchronized (this) {
			System.out.println("Une voiture sort.");
			synchronized(sortie){
				placesRestantesSortie++;
			}
			this.notify();
		}
	}
}
