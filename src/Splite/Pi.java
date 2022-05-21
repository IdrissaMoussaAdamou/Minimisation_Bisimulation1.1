package Splite;

import java.util.ArrayList;

import GT1.Node;
import GT1.Transition;

public class Pi {

	ArrayList<B> pi;

	Pi() {
		pi = new ArrayList<B>();
	}

	public Pi(ArrayList<B> blocs) {
		pi = blocs;
	}

	public static boolean canReach(Node s, Node t, String etiquette, Pi pi) {
		ArrayList<B> sB = new ArrayList<B>();
		ArrayList<B> tB = new ArrayList<B>();
		for (int l = 0; l < s.t.size(); l++) {
			if (s.t.get(l).etiquettes.contains(etiquette)) {
				for (int i = 0; i < pi.pi.size(); i++) {
					for (int j = 0; j < pi.pi.get(i).pi.size(); j++) {
						if (s.t.get(l).n2.nom.equals(pi.pi.get(i).pi.get(j).nom)) {
							sB.add(pi.pi.get(i));
						}
					}
				}
			}
		}

		for (int l = 0; l < t.t.size(); l++) {
			if (t.t.get(l).etiquettes.contains(etiquette)) {
				for (int i = 0; i < pi.pi.size(); i++) {
					for (int j = 0; j < pi.pi.get(i).pi.size(); j++) {
						if (t.t.get(l).n2.nom.equals(pi.pi.get(i).pi.get(j).nom)) {
							tB.add(pi.pi.get(i));
						} else {
							// System.out.println(t.t.get(l).n2.nom);
							// System.out.println(pi.pi.get(i).pi.get(j).nom);
						}

					}
				}
			}
		}

		if (sB.size() == tB.size()) {

			if (sB.size() != 0) {
				for (int i = 0; i < sB.size(); i++) {
					if (!tB.contains(sB.get(i))) {
						return false;
					}
				}
				for (int i = 0; i < tB.size(); i++) {
					if (!sB.contains(tB.get(i))) {
						return false;
					}
				}
				return true;
			}
			return true;
		} else {
			boolean exist = true;
			for (int i = 0; i < sB.size(); i++) {
				if (!tB.isEmpty()) {
					if (!tB.contains(sB.get(i))) {
						exist = false;
					}
				} else
					exist = false;

			}
			for (int i = 0; i < tB.size(); i++) {
				if (!sB.isEmpty()) {
					if (!sB.contains(tB.get(i))) {
						exist = false;
					}
				} else
					exist = false;
			}
			return exist == true;
		}
	}

	public Pi split(B bloc, String etiquette, Pi pi) {
		// int index = (int) (Math.random() * bloc.pi.size());
		// System.out.println("randex:" + index);
		Node s = bloc.pi.get(0);
		B b1 = new B();
		B b2 = new B();

		for (int i = 0; i < bloc.pi.size(); i++) {
			if (canReach(s, bloc.pi.get(i), etiquette, pi)) {
				b1.pi.add(bloc.pi.get(i));
			} else {
				b2.pi.add(bloc.pi.get(i));
				// System.out.print(b2.pi);
			}
		}
		ArrayList<B> pt = new ArrayList<B>();
		if (b2.pi.isEmpty()) {
			pt.add(b1);
			return new Pi(pt);
		} else {
			if (!b1.pi.isEmpty())
				pt.add(b1);
			pt.add(b2);
			return new Pi(pt);
		}
	}

	public Pi refine(Pi pi, ArrayList<B> pip, int index) {

		for (int i = 0; i < pip.size(); i++) {
			pi.pi.add(pip.get(i));
		}
		pi.pi.remove(index);
		return pi;

	}

	public Pi k_and_s(B pr, ArrayList<String> etiquettes) {

		ArrayList<B> block = new ArrayList<B>();
		block.add(pr);
		Pi pi = new Pi(block);

		boolean changed = true;
		ArrayList<B> pip;
		B copy = new B();

		while (changed) {
			changed = false;
			for (int i = 0; i < pi.pi.size(); i++) {

				for (int j = 0; j < etiquettes.size(); j++) {
					copy.pi = pi.pi.get(i).pi;
					pip = pi.split(copy, etiquettes.get(j), pi).pi;

					if (pip.size() > 1) {
						pi = refine(pi, pip, i);
						changed = true;

					}

				}
			}
		}

		return new Pi(pi.pi);
	}

	public void AffichageSplitFinal(Pi pi) {
		System.out.println("//");
		if (pi.pi.size() > 1)
			System.out.print("{ ");
		for (int i = 0; i < pi.pi.size(); i++) {
			System.out.print("{ ");
			for (int j = 0; j < pi.pi.get(i).pi.size(); j++) {
				if (j != pi.pi.get(i).pi.size() - 1)
					System.out.print(pi.pi.get(i).pi.get(j).nom + ", ");
				else
					System.out.print(pi.pi.get(i).pi.get(j).nom);
			}
			if (i != pi.pi.size() - 1)
				System.out.print(" }, ");
			else
				System.out.print(" }");
		}
		if (pi.pi.size() > 1)
			System.out.print(" }");
		System.out.println(" ");
		System.out.println("//");
	}

	public static int IndexNoeud(B bloc, Node s) {
		int i = 0;
		for (int j = 0; j < bloc.pi.size(); j++) {
			if (bloc.pi.get(j).nom.contains(s.nom)) {
				i = j;
			}
		}
		return i;
	}

	public static B ConstructionNoeudSplitFinal(Pi pi, B bloc) {
		B b0 = new B();
		for (int i = 0; i < pi.pi.size(); i++) {
			String s = "";
			Node n = new Node();
			for (int j = 0; j < pi.pi.get(i).pi.size(); j++) {
				s = s + " " + pi.pi.get(i).pi.get(j).nom;
			}
			n.nom = s;
			b0.pi.add(n);
		}
		for (int i = 0; i < bloc.pi.size(); i++) {
			int ind = IndexNoeud(b0, bloc.pi.get(i));
			Node l = b0.pi.get(ind);
			for (int j = 0; j < bloc.pi.get(i).t.size(); j++) {
				Transition t = bloc.pi.get(i).t.get(j);
				int ind2 = IndexNoeud(b0, t.n2);
				Node l2 = b0.pi.get(ind2);
				if (l.t.size() == 0) {
					t.n2 = l2;
					l.t.add(t);
					l.enfants.add(l2);
				} else {
					for (int k = 0; k < l.t.size(); k++) {
						if (!(l.t.get(k).n2.nom.equals(l2.nom))) {
							t.n2 = l2;
							l.t.add(t);
							l.enfants.add(l2);
						} else {
							for (int h = 0; h < t.etiquettes.size(); h++) {
								int exist = 0;
								for (int g = 0; g < l.t.get(k).etiquettes.size(); g++) {
									if (l.t.get(k).etiquettes.get(g).equals(t.etiquettes.get(h))) {
										exist++;
									}
								}
								if (exist == 0)
									l.t.get(k).etiquettes.add(t.etiquettes.get(h));
							}
						}
					}
				}
			}
			b0.pi.set(ind, l);
		}
		return b0;
	}

}
