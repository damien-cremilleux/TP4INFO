package tp7;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int taille = 6;
		Philosophe p;
		Tablev3 t = new Tablev3 (taille);
		for (int i = 0; i <taille; i++) {
			p = new Philosophe(i,t);
			p.start();
		}
	}

}