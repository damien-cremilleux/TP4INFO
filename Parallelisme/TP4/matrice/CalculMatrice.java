package matrice;

public class CalculMatrice implements Runnable {

    private int[] resVect;
    private Matrice matrice;
    private int thread_id;
    private int nb_thread;

    public CalculMatrice(Matrice mat, int id, int nbThread, int[] res) {
	thread_id = id;
	matrice = mat;
	nb_thread = nbThread;
	resVect = res;
    }

    @Override
    public void run() {
	int sommeColonne;

	int born_inf = thread_id * (matrice.nb_colonne / nb_thread);
	// System.out.println("Thread "+thread_id+" borne inf : "+born_inf);
	int born_sup = (thread_id + 1) * (matrice.nb_colonne / nb_thread);
	// System.out.println("Thread "+thread_id+" borne sup : "+born_sup);
	for (int i = born_inf; i < born_sup; i++) {
	    sommeColonne = matrice.calculColonne(i);
	    /*	System.out.println("          Res de la colonne " + i + " : "
		+ sommeColonne);*/
	    resVect[i] = sommeColonne;
	}
    }

}
