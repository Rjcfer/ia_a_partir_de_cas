package metier;

import java.util.List;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;

public class SimilarityCalculator {

	List<Cas> normalCases;

	public SimilarityCalculator() {
		//load data when class starts maybe singleton after ? to load Data once ?
		loadData();

	}

	private void loadData() {
		normalCases = DataHandler.getDataFromFile();
	}

	private int dev(String s1, String s2) {
		if (s1.equals(s2))
			return 1;
		else
			return 0;
	}

	//([x,y], [a, b)) = min(Pos(x,[a,b]), Pos(y, [a, b]))
	private int ipos(Intervalle i1, Intervalle i2) {
		if (i2.getBs() >= i1.getBi())
			return 0;
		else {

			int pos1 = pos(i1.getBi(), i2);
			int pos2 = pos(i2.getBs(), i1);

			return pos1 < pos2 ? pos1 : pos2;
		}
	}

	private int pos(int x, Intervalle i) {
		if (i.getBi() < x && i.getBs() > x)
			return 0;
		else {
			return x - i.getBi() < x - i.getBs() ? x - i.getBi() : x - i.getBs();
		}

	}

	private double dt(Triplet t1, Triplet t2, int dateMax) {
		t1.getIntevalle();

		/*	System.out.println("1 " + dev(t1.getEc(), t2.getEc())); //
			System.out.println("2 " + dev(t1.getEr(), t2.getEr()));
			System.out.println("3 " + ipos(t1.getIntevalle(), t2.getIntevalle()));
			System.out.println("4 " + t1.getIntevalle());
			System.out.println("5 " + t2.getIntevalle());*/

		return dev(t1.getEc(), t2.getEc()) //
				+ dev(t1.getEr(), t2.getEr())//
				+ (ipos(t1.getIntevalle(), t2.getIntevalle())//
						/ dateMax);

	}

	public void testCase() {

		Cas c = new Cas();
		Triplet t = new Triplet();
		Intervalle i = new Intervalle();
		// first case
		t.setEr("A");
		t.setEc("B");
		i.setBi(5);
		i.setBs(10);
		t.setIntevalle(i);
		c.getP().add(t);

		// second case
		t = new Triplet();
		i = new Intervalle();
		t.setEr("B");
		t.setEc("C");
		i.setBi(1);
		i.setBs(5);
		t.setIntevalle(i);
		c.getP().add(t);

		Cas c2 = new Cas();
		// tr case
		t = new Triplet();
		i = new Intervalle();
		t.setEr("A");
		t.setEc("B");
		i.setBi(11);
		i.setBs(12);
		t.setIntevalle(i);
		c2.getP().add(t);

		System.out.println(dt(c.getP().get(0), c.getP().get(2), 100));

		int resultat = 0;
		for (Triplet triplet : c.getP()) {

			resultat += dt(triplet, c.getP().get(c.getP().indexOf(triplet) + 1), 100);
		}
		// get all normal caseswithSameSize
		/*	List<Cas> normalCasesWithSameSize = normalCases.stream().filter(c -> {
				return c.getP().size() == cas.getP().size();
			}).collect(Collectors.toList());
		
			if (normalCasesWithSameSize.size() == 0) {
				// try to find a cas with same triple
			}*/

	}

	// TODO calculate similarities here	

	// main to test TODO delete this when finish 
	public static void main(String[] args) {
		SimilarityCalculator s = new SimilarityCalculator();
		s.testCase();
		/*for (Cas c : s.normalCases) {
			System.out.println(c);
		}*/
	}

}
