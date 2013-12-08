package tp7;

public class Tablev3 {
	int _taille;
	Object _baguettes[];

	Tablev3(int t) {
		_taille = t;
		_baguettes = new Object[_taille];
		for (int i = 0; i < _taille; i++) {
			_baguettes[i] = new Object();
		}
	}

	void prendreBaguettes(int i) throws InterruptedException { // i est le
																// philosophe

		if (i % 2 == 0) {
			synchronized (_baguettes[i % _taille]) {
				System.out.println("Le philosophe " + i + " a la baguette " + i
						% _taille);
				Thread.sleep(100);
				synchronized (_baguettes[(i + 1) % _taille]) {
					System.out.println("Le philosophe " + i + " a la baguette "
							+ (1 + i) % _taille);
					System.out.println("Je mange " + i);
					Thread.sleep(4000);
				}
			}
		} else {
			synchronized (_baguettes[(i + 1) % _taille]) {
				System.out.println("Le philosophe " + i + " a la baguette "
						+ (1 + i) % _taille);
				Thread.sleep(100);
				synchronized (_baguettes[(i) % _taille]) {
					System.out.println("Le philosophe " + i + " a la baguette "
							+ (0 + i) % _taille);
					System.out.println("Je mange " + i);
					Thread.sleep(4000);
				}
			}
		}

	}
}
