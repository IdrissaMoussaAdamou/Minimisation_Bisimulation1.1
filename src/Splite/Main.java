package Splite;

import java.util.ArrayList;

import GT1.Node;
import GT1.Transition;

public class Main {

	public static void main(String[] args) {

		// Construction des noeuds et transitions

		Node q = new Node("q");
		Node s0 = new Node("s0");
		Node s1 = new Node("s1");
		Node s2 = new Node("s2");
		Node s3 = new Node("s3");
		Node s4 = new Node("s4");
		Node t0 = new Node("t0");
		Node t1 = new Node("t1");
		Node t2 = new Node("t2");
		Node t3 = new Node("t3");
		Node t4 = new Node("t4");
		Node t5 = new Node("t5");

		Transition tq_s0 = new Transition();
		tq_s0.n2 = s0;
		tq_s0.etiquettes.add("a");
		q.t.add(tq_s0);

		Transition tq_t0 = new Transition();
		tq_t0.n2 = t0;
		tq_t0.etiquettes.add("a");
		q.t.add(tq_t0);

		Transition ts0_1 = new Transition();
		ts0_1.n2 = s1;
		ts0_1.etiquettes.add("a");
		s0.t.add(ts0_1);

		Transition ts0_2 = new Transition();
		ts0_2.n2 = s2;
		ts0_2.etiquettes.add("a");
		s0.t.add(ts0_2);

		Transition ts1_3 = new Transition();
		ts1_3.n2 = s3;
		ts1_3.etiquettes.add("a");
		s1.t.add(ts1_3);

		Transition ts1_4 = new Transition();
		ts1_4.n2 = s4;
		ts1_4.etiquettes.add("b");
		s1.t.add(ts1_4);

		Transition ts2_4 = new Transition();
		ts2_4.n2 = s4;
		ts2_4.etiquettes.add("a");
		s2.t.add(ts2_4);

		Transition ts3_0 = new Transition();
		ts3_0.n2 = s0;
		ts3_0.etiquettes.add("a");
		s3.t.add(ts3_0);

		Transition ts4_0 = new Transition();
		ts4_0.n2 = s0;
		ts4_0.etiquettes.add("a");
		s4.t.add(ts4_0);

		// TT

		Transition tt0_1 = new Transition();
		tt0_1.n2 = t1;
		tt0_1.etiquettes.add("a");
		t0.t.add(tt0_1);

		Transition tt0_3 = new Transition();
		tt0_3.n2 = t3;
		tt0_3.etiquettes.add("a");
		t0.t.add(tt0_3);

		Transition tt1_2 = new Transition();
		tt1_2.n2 = t2;
		tt1_2.etiquettes.add("a");
		tt1_2.etiquettes.add("b");
		t1.t.add(tt1_2);

		Transition tt1_5 = new Transition();
		tt1_5.n2 = t5;
		tt1_5.etiquettes.add("b");
		t1.t.add(tt1_5);

		Transition tt2_0 = new Transition();
		tt2_0.n2 = t0;
		tt2_0.etiquettes.add("a");
		t2.t.add(tt2_0);

		Transition tt3_4 = new Transition();
		tt3_4.n2 = t4;
		tt3_4.etiquettes.add("a");
		t3.t.add(tt3_4);

		Transition tt4_0 = new Transition();
		tt4_0.n2 = t0;
		tt4_0.etiquettes.add("a");
		t4.t.add(tt4_0);

		Transition tt5_0 = new Transition();
		tt5_0.n2 = t0;
		tt5_0.etiquettes.add("a");
		t5.t.add(tt5_0);

		Transition tt5_4 = new Transition();
		tt5_4.n2 = t4;
		tt5_4.etiquettes.add("a");
		t5.t.add(tt5_4);

		// test de l'algo

		// construction d'un bloc initial
		B b = new B();
		b.pi.add(q);
		b.pi.add(s0);
		b.pi.add(s1);
		b.pi.add(s2);
		b.pi.add(s3);
		b.pi.add(s4);
		b.pi.add(t0);
		b.pi.add(t1);
		b.pi.add(t2);
		b.pi.add(t3);
		b.pi.add(t4);
		b.pi.add(t5);
		
		// B initial

		ArrayList<B> block = new ArrayList<B>();
		block.add(b);

		ArrayList<String> labels = new ArrayList<String>();
		labels.add("a");
		labels.add("b");

		Pi pii = new Pi(block);
		Pi pi_ = pii.k_and_s(b, labels);

		System.out.println("--------------------");
		pii.AffichageSplitFinal(pi_);

	}

}
