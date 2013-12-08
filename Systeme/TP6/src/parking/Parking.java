package parking;

public class Parking {

	private final static int NB_PLACE = 3;
	private static int placesRestantes;

	public Parking() {
		placesRestantes = NB_PLACE;
	}

	public void entreeNord() {
		synchronized (this) {
			System.out.println("Nb place : " + placesRestantes);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}// sleep for 1000 ms

			
			if (placesRestantes != 0) {
				placesRestantes--;
				System.out.println("Une voiture rentre par le nord.");
			} else {
				try {
					System.out.println("Une voiture attend à l'entrée nord.");
					this.wait();
					placesRestantes--;
					System.out.println("Une voiture rentre à l'entrée nord, après attente.");
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void entreeSud() {
		System.out.println("Nb place : " + placesRestantes);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// sleep for 1000 ms
		synchronized (this) {
			if (placesRestantes != 0) {
				placesRestantes--;
				System.out.println("Une voiture rentre par le sud.");
			} else {
				try {
					System.out.println("Une voiture attend à l'entrée sud.");
					this.wait();
					placesRestantes--;
					System.out.println("Une voiture rentre à l'entrée sud, après attente.");
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void sortieVoiture() {
		synchronized (this) {
			System.out.println("Une voiture sort.");
			placesRestantes++;
			this.notify();
		}

	}
}
