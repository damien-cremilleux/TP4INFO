public class CalculMultiplicationMatrice implements Runnable {

	private int[][] matriceResultat;
	private Matrice matrice1;
	private Matrice matrice2;
	private int thread_id;

	// private int nb_thread;

	public CalculMultiplicationMatrice(Matrice mat1, Matrice mat2, int id,
			int nbThread, int[][] res) {
		thread_id = id;
		matrice1 = mat1;
		matrice2 = mat2;
		// nb_thread = nbThread;
		matriceResultat = res;
	}

	@Override
	public void run() {
		for (int i = 0; i < matrice1.nb_ligne; i++) {
			//System.out.println("Thread "+ thread_id+" -> Calcul de ["+i+"]["+thread_id+"]");
			matriceResultat[i][thread_id] = matrice1.calculElemMultiplication(
					matrice2, i, thread_id);

		}
	}

}
