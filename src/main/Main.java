package main;

import java.util.List;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;
import entities.etat.NonIdentifie;
import logger.Print;
import metier.DataHandler;
import metier.SimilarityCalculator;

public class Main {

	public Main() {
	}

	// java app entry point to be used later if needed
	public static void testCase() {

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

		SimilarityCalculator s = new SimilarityCalculator();
		Cas Result = s.calculate(pn);

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
			// you can add your own file path where to test or if you whant can use the method below
			// test your case 

			//testCase();

			// test your file
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
