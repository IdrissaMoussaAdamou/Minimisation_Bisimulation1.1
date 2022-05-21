package GT1;

import java.util.ArrayList;

public class Node {
	public String nom;
	public ArrayList<Node> enfants;
	public ArrayList<Transition> t;

	public Node(String nom) {
		this.nom = nom;
		this.t = new ArrayList<Transition>();
		this.enfants = new ArrayList<Node>();
	}

	public Node() {

		this.t = new ArrayList<Transition>();
		this.enfants = new ArrayList<Node>();
	}

	public void AfficheNoeud() {

		String enfants = this.nom + "=>";
		for (Node enfant : this.enfants) {
			enfants += enfant.nom + ",";

		}
		enfants += "/";
		for (Transition t : this.t) {
			String r = "";
			enfants += this.nom + " ,";
			for (String s : t.etiquettes) {
				r += s + ",";
			}
			enfants += r + t.n2.nom + "---";
		}
		enfants += "/";
		System.out.println(enfants);
	}
}
