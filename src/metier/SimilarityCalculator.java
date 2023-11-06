package metier;

import java.util.List;
import java.util.stream.Collectors;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;
import entities.etat.Etat;
import entities.etat.NonIdentifie;

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
			return 0;
		else
			return 1;
	}

	//([x,y], [a, b)) = min(Pos(x,[a,b]), Pos(y, [a, b]))
	private double ipos(Intervalle i1, Intervalle i2) {

		int a = i2.getBi();
		int x = i1.getBi();
		int y = i1.getBs();

		if (y > a && x > y && x < a)
			return 0;
		else {

			int pos1 = pos(x, i2);
			int pos2 = pos(y, i2);
			return Math.min(pos1, pos2);
		}
	}

	private int pos(int x, Intervalle i) {

		if (x > i.getBi() && x < i.getBs())
			return 0;
		else {
			return Math.min(Math.abs(x - i.getBi()), Math.abs(x - i.getBs()));
		}

	}

	private double dt(Triplet t1, Triplet t2, double dateMax) {
		t1.getIntevalle();

		System.out.println("1 " + dev(t1.getEc(), t2.getEc())); //
		System.out.println("2 " + dev(t1.getEr(), t2.getEr()));
		System.out.println("3 " + ipos(t1.getIntevalle(), t2.getIntevalle()));
		System.out.println("4 " + t1.getIntevalle());
		System.out.println("5 " + t2.getIntevalle());

		return dev(t1.getEc(), t2.getEc()) //
				+ dev(t1.getEr(), t2.getEr())//
				+ (ipos(t1.getIntevalle(), t2.getIntevalle())//
						/ dateMax);

	}

	public Cas calculate(Cas casToTest) {
		Etat etat = null;
		Cas mustSimiliarCas = null;

		List<Cas> casWithSameSize = normalCases.stream().filter(c -> {
			return c.getP().size() == casToTest.getP().size();
		}).collect(Collectors.toList());

		for (Cas cas : casWithSameSize) {
			System.out.println(cas.getP().size() == casToTest.getP().size());
		}
		if (casWithSameSize.size() == 0)
			etat = NonIdentifie.getNonIdentifieInstance();
		else {
			double resultat = 1;
			int index = 0;
			while (resultat != 0 || casWithSameSize.size() > index) {
				Cas cas = casWithSameSize.get(index);
				double tempResult = 0;
				for (Triplet t : cas.getP()) {
					//tempResult += dt(t, cas.getP().get(casToTest.getP().indexOf(t)), 100);
				}
				tempResult = tempResult / 3 * cas.getP().size();

				if (tempResult < resultat) {
					mustSimiliarCas = cas;
					resultat = tempResult;
				}

				index++;
			}
		}
		casToTest.setS(etat);

		//system.out.println(mustSimiliarCas);
		return mustSimiliarCas;
	}

	public void testCase() {
		//(In, RE_but_ext, nct) * (In, RE_XGLISS, nct) * (RE_but_ext, FE_x_conv, [274000, 309000]) * (RE_XGLISS, FE_x_conv, [274000, 309000])

		Cas pn = new Cas();
		Triplet t = new Triplet();
		Intervalle i = new Intervalle();
		// first case
		t.setEr("In");
		t.setEc("RE_but_ext");
		i.setBi(0);
		i.setBs(999999999);
		t.setIntevalle(i);
		pn.getP().add(t);

		// second case
		t = new Triplet();
		i = new Intervalle();
		t.setEr("In");
		t.setEc("RE_XGLISS");
		i.setBi(0);
		i.setBs(999999999);
		t.setIntevalle(i);
		pn.getP().add(t);

		// third case
		t = new Triplet();
		i = new Intervalle();
		t.setEr("RE_but_ext");
		t.setEc("FE_x_conv");
		i.setBi(274000);
		i.setBs(309000);
		t.setIntevalle(i);
		pn.getP().add(t);

		// four case
		t = new Triplet();
		i = new Intervalle();
		t.setEr("RE_XGLISS");
		t.setEc("FE_x_conv");
		i.setBi(274000);
		i.setBs(309000);
		t.setIntevalle(i);
		pn.getP().add(t);

		Cas Result = calculate(pn);
		System.out.println(Result);
	}

	public static void main(String[] args) {
		SimilarityCalculator s = new SimilarityCalculator();
		s.testCase();
		/*for (Cas c : s.normalCases) {
			System.out.println(c);
		}*/
	}

}
