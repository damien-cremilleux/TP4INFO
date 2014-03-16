import java.util.Random;
import java.util.Vector;

public class Matrice {
	public int nb_ligne;
	public int nb_colonne;
	public int[][] tab;

	public Matrice() {
		nb_ligne = 10;
		nb_colonne = 10;
		tab = new int[nb_ligne][nb_colonne];

		Random generator = new Random();
		for (int i = 0; i < nb_ligne; i++) {
			for (int j = 0; j < nb_colonne; j++) {
				int r = generator.nextInt(10);
				tab[i][j] = r;
			}
		}

	}

	public int calculColonne(int col) {
		int somme = 0;
		for (int i = 0; i < nb_ligne; i++) {
			somme += tab[i][col];
		}
		return somme;
	}

	/**
	 * Calcul l'élément (ligne,colonne) résultant de la multiplication des deux
	 * matrices
	 * 
	 * @param m2
	 *            la deuxième matrice
	 * @param ligne
	 *            l'indice de la ligne pour l'élément à calculer
	 * @param colonne
	 *            l'indice de la colonne pour l'élément à calculer
	 * @return l'élément calculé
	 */
	public int calculElemMultiplication(Matrice m2, int ligne, int colonne) {
		int somme = 0;
		//System.out.println("m2.nb_ligne" +m2.nb_ligne);
		for (int i = 0; i < m2.nb_ligne; i++) {
			
			somme += (this.tab[ligne][i] * m2.tab[i][colonne]);
		}
		return somme;
	}

	public void affiche() {
		for (int i = 0; i < nb_ligne; i++) {
			for (int j = 0; j < nb_colonne; j++) {
				System.out.print(tab[i][j]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		/*
		 * int nb_thread;
		 * 
		 * nb_thread = 5; Matrice m1 = new Matrice(); m1.affiche(); int[] res =
		 * new int[m1.nb_colonne]; Vector<Thread> tabT = new Vector<Thread>();
		 * 
		 * for (int i = 0; i < nb_thread; i++) { CalculMatrice c = new
		 * CalculMatrice(m1, i, nb_thread, res); Thread t = new Thread(c);
		 * t.start(); tabT.add(t); }
		 * 
		 * for (int i = 0; i < tabT.size(); i++) {
		 * 
		 * tabT.get(i).join(); }
		 * 
		 * for (int i = 0; i < m1.nb_colonne; i++) { System.out.print(res[i] +
		 * " "); } System.out.println("");
		 */

		/* -------------------------- */
		Matrice m1 = new Matrice();
		Matrice m2 = new Matrice();
		m1.affiche();
		m2.affiche();
		int[][] matriceResultat = new int[m1.nb_ligne][m2.nb_colonne];
		int nbThreadMult = m1.nb_colonne;
		Vector<Thread> tabTMult = new Vector<Thread>();

		for (int i = 0; i < nbThreadMult; i++) {
			CalculMultiplicationMatrice c = new CalculMultiplicationMatrice(m1,
					m2, i, nbThreadMult, matriceResultat);
			Thread t = new Thread(c);
			t.start();
			tabTMult.add(t);
		}

		for (int i = 0; i < tabTMult.size(); i++) {

			tabTMult.get(i).join();
		}

		for (int i = 0; i < m1.nb_ligne; i++) {
			for (int j = 0; j < m1.nb_colonne; j++) {
				System.out.print(matriceResultat[i][j]+" ");
				
			}
			System.out.println();
		}
		System.out.println("");

	}

}