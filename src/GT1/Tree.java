package GT1;

import java.util.ArrayList;
import java.util.Scanner;

import Splite.B;
import Splite.Pi;

public class Tree {
	/*
	 * Represente le noeud root
	 */
	public Node root;

	/*
	 * Represente la liste de l'alphabetbet a remplir par l'utilisateur
	 */
	public ArrayList<String> alphabet;

	/*
	 * Represente une liste contenant tous les noeuds de l'abre genere
	 */
	public ArrayList<Node> noeuds;

	public Tree() {
		/*
		 * Initialisation des attributs
		 */
		alphabet = new ArrayList<String>();
		noeuds = new ArrayList<Node>();
		Scanner s = new Scanner(System.in);

		/*
		 * Construction de l'arbre
		 */
		this.root = ConstructionArbre(s, null, 0);
	}

	public Node ConstructionArbre(Scanner s, Node parent, int i) {

		if (parent == null) {
			/*
			 * Siginifie que nous sommes au niveau du noeud root
			 */
			System.out.println("Entrez le nom du noeud root:");
		} else {
			/*
			 * Siginifie que nous sommes au niveau d'un noeud avec parent
			 */
			System.out.println("Entrez le nom du noeud " + i + ", fils du noeud " + parent.nom);

		}
		/*
		 * Lecture du nom du noeud
		 */
		String nom = s.next();

		/*
		 * Determinera si le noeud existe deja ou non
		 */
		String exist = "";
		int kk = -1;

		for (int k = 0; k < noeuds.size(); k++) {

			/*
			 * Verifie si le nom entre appartient a un noeud existant puis recuperer
			 * l'indice
			 */
			if (noeuds.get(k).nom.equals(nom)) {
				exist = noeuds.get(k).nom;
				kk = k;
				break;
			}
		}

		/*
		 * Creation du noeud en construction
		 */
		Node no = new Node(nom);
		noeuds.add(no);

		if (!exist.isEmpty()) {

			/*
			 * Si le nom n'existe pas alors ajouter le noeud cree a la liste des noeuds
			 */
			parent.enfants.add(noeuds.get(kk));
			noeuds.remove(no);
		} else {

			/*
			 * Si le nom n'existe pas alors c'est un nouveau noeud a creer
			 */
			no = new Node(nom);
			System.out.println("Entrez le nombre d'enfants pour le noeud " + no.nom);
			int n = s.nextInt();
			ArrayList<Node> enfants = new ArrayList<Node>();
			for (int j = 0; j < n; j++) {
				/*
				 * Construire les noeuds fils recursivement
				 */
				Node enfant = ConstructionArbre(s, no, j);
				enfants.add(enfant);
			}
			no.enfants = enfants;
			Transition tt;
			for (int j = 0; j < no.enfants.size(); j++) {
				tt = new Transition();
				boolean etq = true;
				while (etq) {

					/*
					 * tant que l'utilisateur a des etiquettes a mettre alors construire
					 * l'alphabetbet
					 */
					System.out.println("Donnez une etiquette pour la transition (" + no.nom + "->"
							+ no.enfants.get(j).nom + "), sinon tapez -1 pour terminer:");
					String res = s.next();
					if (res.equals("-1")) {
						etq = false;
					} else {
						if (!alphabet.contains(res))
							alphabet.add(res);
						tt.etiquettes.add(res);
					}
				}

				/*
				 * Construire la liste des transitions pour le noeud actuel
				 */
				if (tt.etiquettes.size() > 0) {
					tt.n2 = no.enfants.get(j);
					no.t.add(tt);
				}
			}
			for (int k = 0; k < noeuds.size(); k++) {

				if (noeuds.get(k).nom.equals(no.nom)) {
					noeuds.set(k, no);
					break;
				}
			}
		}
		return no;
	}

	public void Affichage() {
		for (int j = 0; j < this.noeuds.size(); j++) {
			this.noeuds.get(j).AfficheNoeud();
		}
	}

	public void MiniBisi() {
		B b0 = new B();
		for (int i = 0; i < this.noeuds.size(); i++) {
			b0.pi.add(noeuds.get(i));
		}

		ArrayList<B> block = new ArrayList<B>();
		block.add(b0);

		Pi pii = new Pi(block);
		Pi pi_ = pii.k_and_s(b0, this.alphabet);
		System.out.println("--------------------");
		pii.AffichageSplitFinal(pi_);

		B b4 = Pi.ConstructionNoeudSplitFinal(pi_, b0);
		for (int j = 0; j < b4.pi.size(); j++) {
			b4.pi.get(j).AfficheNoeud();
		}
	}
}
