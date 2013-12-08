package tp7;

public class Tablev2 {
	int _taille;
	Object _baguettes[];
	int compteur;

	Tablev2(int t) {
		_taille = t;
		_baguettes = new Object[_taille];
		for (int i = 0; i < _taille; i++) {
			_baguettes[i] = new Object();
		}
		compteur = _taille - 1;
	}

	void prendreBaguettes(int i) throws InterruptedException { // i est le
		// philosophe

		boolean aTable = false;

		while (!aTable) {
			synchronized (this) {
				if (compteur != 0) {
					aTable = true;
					compteur--;
				}
			}

			if (!aTable) {
				System.out.println("J'attend : " + i);
				synchronized (this) {
					this.wait();
				}

			} else {
				synchronized (_baguettes[i % _taille]) {
					System.out.println("Le philosophe " + i + " a la baguette "
							+ i % _taille);
					Thread.sleep(100);
					synchronized (_baguettes[(i + 1) % _taille]) {
						System.out.println("Le philosophe " + i
								+ " a la baguette " + (1 + i) % _taille);
						System.out.println("Je mange " + i);
						Thread.sleep(4000);
					}
				}
				synchronized (this) {
					compteur++;
					this.notify();
				}
			}
		}
	}
}
