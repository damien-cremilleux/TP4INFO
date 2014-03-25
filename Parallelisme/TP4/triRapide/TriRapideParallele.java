// Attention, TP incomplet !

package triRapide;

public class TriRapideParallele implements Runnable {

	public int[] tabAtrier;

	public TriRapideParallele(int[] tab) {
		tabAtrier = tab;
	}

	void echanger(int tableau[], int a, int b) {
		int temp = tableau[a];
		tableau[a] = tableau[b];
		tableau[b] = temp;
	}

	void quickSort(int tableau[], int debut, int fin) {
		int gauche = debut - 1;
		int droite = fin + 1;
		int pivot = tableau[debut];

		/* Si le tableau est de longueur nulle, il n'y a rien à faire. */
		if (debut >= fin)
			return;

		/*
		 * Sinon, on parcourt le tableau, une fois de droite à gauche, et une
		 * autre de gauche à droite, à la recherche d'éléments mal placés, que
		 * l'on permute. Si les deux parcours se croisent, on arrête.
		 */
		while (true) {
			while (tableau[droite] > pivot) {
				droite--;
			}
			while (tableau[gauche] < pivot) {
				gauche++;
			}

			if (gauche < droite)
				echanger(tableau, gauche, droite);
			else
				break;
		}

		/*
		 * Maintenant, tous les éléments inférieurs au pivot sont avant ceux
		 * supérieurs au pivot. On a donc deux groupes de cases à trier. On
		 * utilise pour cela... la méthode quickSort elle-même !
		 */
		quickSort(tableau, debut, droite);
		quickSort(tableau, droite + 1, fin);
	}

	public void run() {
		quickSort(this.tabAtrier, 0, 4);
	}

	public static void main(String[] args) throws InterruptedException {
		int tab[] = { 5, 3, 4, 1, 2 };

		TriRapideParallele tri = new TriRapideParallele(tab);
		Thread t = new Thread(tri);

		t.join();
		for (int i = 0; i < 5; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println();

	}

}
