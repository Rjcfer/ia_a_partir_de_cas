package metier;

import java.util.List;
import java.util.stream.Collectors;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;
import entities.etat.Defaillant;
import entities.etat.Etat;
import entities.etat.NonIdentifie;
import entities.etat.Normal;
import logger.Print;

public class SimilarityCalculator {

	List<Cas> normalCases;

	public SimilarityCalculator() {
		//load data when class starts
		loadData();

	}

	private void loadData() {
		normalCases = DataHandler.getDataFromFile("reglesCN.txt");
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
		return dev(t1.getEc(), t2.getEc()) //
				+ dev(t1.getEr(), t2.getEr())//
				+ (ipos(t1.getIntevalle(), t2.getIntevalle())//
						/ dateMax);

	}

	public Cas calculate(Cas casToTest) {
		Etat etat = NonIdentifie.getNonIdentifieInstance();
		Cas mustSimiliarCas = null;

		// get all cases with same triplets size 
		List<Cas> casWithSameSize = normalCases.stream().filter(c -> {
			return c.getP().size() == casToTest.getP().size();
		}).collect(Collectors.toList());

		double resultat = 1;
		if (!(casWithSameSize.size() == 0)) {
			int index = 0;

			while (resultat != 0 && index < casWithSameSize.size()) {
				Cas cas = casWithSameSize.get(index);

				double tempResult = 0;
				for (int i = 0; i < cas.getP().size(); i++) {
					Triplet t = cas.getP().get(i);
					tempResult += dt(t, casToTest.getP().get(i), 100);
				}
				tempResult = tempResult / (3 * cas.getP().size());

				if (tempResult < resultat) {
					mustSimiliarCas = cas;
					resultat = tempResult;
				}

				index++;
			}

			if (resultat == 0)
				etat = Normal.getNormalInstance();
			else if (resultat < 1) {
				etat = new Defaillant(" defaillant description", "localisation");
			} else {
				// non identified case
				mustSimiliarCas = casToTest;
				etat = NonIdentifie.getNonIdentifieInstance();
			}
			mustSimiliarCas.setS(etat);
		}
		System.out.println("resultat : " + resultat);
		return mustSimiliarCas;
	}

	public void testCase() {

		Cas pn = new Cas();
		Triplet t = new Triplet();
		Intervalle i = new Intervalle();

		//(In, RE_but_ext, nct) * 
		t = new Triplet();
		i = new Intervalle();
		t.setEr("In");
		t.setEc("RE_but_ext");
		i.setBi(0);
		i.setBs(999999);
		t.setIntevalle(i);
		pn.getP().add(t);

		//(In, RE_XGLISS, nct) *
		t = new Triplet();
		i = new Intervalle();
		t.setEr("In");
		t.setEc("RE_XGLISS");
		i.setBi(0);
		i.setBs(999999);
		t.setIntevalle(i);
		pn.getP().add(t);

		//(RE_but_ext, FE_x_conv, [274000, 309000]) * 
		t = new Triplet();
		i = new Intervalle();
		t.setEr("RE_but_ext");
		t.setEc("FE_x_conv");
		i.setBi(264000);
		i.setBs(319000);
		t.setIntevalle(i);
		pn.getP().add(t);

		//(RE_XGLISS, FE_x_conv, [274000, 309000])
		t = new Triplet();
		i = new Intervalle();
		t.setEr("RE_XGLISS");
		t.setEc("FE_x_conv");
		i.setBi(274000);
		i.setBs(309000);
		t.setIntevalle(i);
		pn.getP().add(t);

		Cas Result = calculate(pn);

		if (Result == null) {
			Print.Purple(NonIdentifie.getNonIdentifieInstance());
		} else
			Print.Purple(Result.getS().getName());
	}

	public static void main(String[] args) {
		SimilarityCalculator s = new SimilarityCalculator();

		List<Cas> defaultCases;
		// reference to non static method
		try {
			defaultCases = DataHandler.getDataFromFile("fakeData.txt");
			defaultCases.forEach((c) -> {
				Print.Green(s.calculate(c).getS().getName());
			});
		} catch (Exception e) {
			Print.Error(e);
		}

		//s.testCase();
	}

}
