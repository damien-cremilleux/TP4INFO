import static choco.Choco.*;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.variables.integer.IntegerVariable;

public class Mobile {
	// longueurs
	private int l1, l2, l3, l4;
	// dernieres valeurs trouvees pour les masses
	private int m1, m2, m3;
	// masse maximum disponible
	private int masse_max;

	// variables des contraintes sur les longueurs
	private CPModel myModelL;
	private CPSolver mySolverL;

	private IntegerVariable il1, il2, il3, il4;

	// ... A completer
	private boolean coherent;// vrai si les longueurs sont coh�rentes

	// variables des contraintes sur les masses
	private CPModel myModelM;
	private CPSolver mySolverM;
	private IntegerVariable im1, im2, im3;
	// ... A completer
	private boolean equilibre;

	// constructeur : _lx : longeur de la branche x, m_max : masse maximum
	// disponible
	public Mobile(int _l1, int _l2, int _l3, int _l4, int m_max) {
		// ... A completer
		myModelL = new CPModel();
		myModelM = new CPModel();
		mySolverL = new CPSolver();
		mySolverM = new CPSolver();

		masse_max = m_max;

		l1 = _l1;
		l2 = _l2;
		l3 = _l3;
		l4 = _l4;

		coherent = false;
		equilibre = false;
		poseProblemeLongeur();
		poseProblemeMasse();
	}

	public boolean estEquilibre() {
		return equilibre;
	}

	// accesseurs
	public int getL1() {
		return l1;
	}

	public int getL2() {
		return l2;
	}

	public int getL3() {
		return l3;
	}

	public int getL4() {
		return l4;
	}

	public int getM1() {
		return m1;
	}

	public int getM2() {
		return m2;
	}

	public int getM3() {
		return m3;
	}

	// pose le probl�me des longeurs (sans le r�soudre),
	// Les longueurs sont coh�rentes si le mobile est libre
	// (remarque : un peu artificiel car faisable en java
	// sans contraintes !)
	private void poseProblemeLongeur() {
		/*il1 = makeIntVar("l1", 0, l1);
		il2 = makeIntVar("l2", 0, l2);
		il3 = makeIntVar("l3", 0, l3);
		il4 = makeIntVar("l4", 0, l4);*/
		il1 = makeConstantVar("l1", l1);
		il2 = makeConstantVar("l2", l2);
		il3 = makeConstantVar("l3", l3);
		il4 = makeConstantVar("l4", l4);
	
		myModelL.addConstraint(lt(il3, plus(il1,il2)));
		myModelL.addConstraint(lt(il4, plus(il1,il2)));
	}

	// verifie la coherence des longueurs
	public boolean longueursCoherentes() {
		mySolverL.read(myModelL);
		coherent = mySolverL.solve();
		return coherent;
	}

	// pose le probleme des masses (sans le r�soudre)
	private void poseProblemeMasse() {
		im1 = makeIntVar("m1", 1, masse_max);
		im2 = makeIntVar("m2", 1, masse_max);
		im3 = makeIntVar("m3", 1, masse_max);
		
		IntegerVariable tabIV[] = {im1,im2,im3};
		myModelM.addConstraint(allDifferent(tabIV));
		
		//équilibré au premier noeud
		myModelM.addConstraint(eq(mult(il3, im2), mult(il4, im3)));

		//globalement équilibré
		myModelM.addConstraint(eq(mult(im1, il1), mult(il2, plus(im2, im3))));

	}

	// r�soud le probl�me des masses
	// la r�solution n'est lanc�e que si l'encombrement est coh�rent
	public boolean equilibre() {
		mySolverM.read(myModelM);
		equilibre = mySolverM.solve();

		m1 = mySolverM.getVar(im1).getVal();
		m2 = mySolverM.getVar(im2).getVal();
		m3 = mySolverM.getVar(im3).getVal();

		return equilibre;
	}

	// cherche une autre solution pour les masses
	// la recherche d'une autre solution ne doit �tre lanc�e que si le mobile
	// est �quilibr�
	public boolean autreSolutionMasse() {
		if (estEquilibre()) {
			equilibre = mySolverM.nextSolution();
			if (estEquilibre()) {
				m1 = mySolverM.getVar(im1).getVal();
				m2 = mySolverM.getVar(im2).getVal();
				m3 = mySolverM.getVar(im3).getVal();
			}
			return equilibre;
		} else {
			return false;
		}
	}

	// gestion de l'affichage
	public String toString() {
		String res = "l1 = " + l1 + "\n l2 = " + l2 + "\n l3 = " + l3
				+ "\n l4 = " + l4;
		if (equilibre) {
			res += "\n m1 = " + m1 + "\n m2 = " + m2 + "\n m3 = " + m3;
		} else {
			res += "\n masses pas encore trouvees ou impossibles !";
		}
		return res;
	}

	// tests
	public static void main(String[] args) {
		//Mobile m = new Mobile(1, 3, 2, 1, 20);
		//Mobile m = new Mobile(1, 1, 2, 3, 20);
		//Mobile m = new Mobile(1, 3, 1, 1, 20);
		//Mobile m = new Mobile(1, 3, 2, 1, 20);
		// tester avec (1,1,2,3,20),(1,3,1,1,20),(1,3,2,1,20)
		System.out.println(m);
		if (m.longueursCoherentes()) {
			System.out.println("Encombrement OK");
			m.equilibre();
			System.out.println(m);
			while (m.autreSolutionMasse()) {
				System.out.println("OU");
				System.out.println(m);
			}
		} else {
			System.out.println("Encombrement pas coherent !");
		}
	}
}
