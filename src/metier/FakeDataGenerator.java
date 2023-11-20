package metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;
import logger.Print;

public class FakeDataGenerator {

	public static void generateFakeData(List<Cas> originalCases, String outputFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

			for (Cas originalCas : originalCases) {
				boolean faultyTripletGenerated = false;
				// Write original case data
				StringBuilder lineCase = new StringBuilder();
				for (int i = 0; i < originalCas.getP().size(); i++) {
					Triplet triplet = originalCas.getP().get(i);

					// find first triplet that can fail
					if (triplet.getIntevalle().getBi() != 0 && triplet.getIntevalle().getBs() != 999999999 && !faultyTripletGenerated) {
						generateFaultyTriplet(originalCas.getP(), i);
						// generate only one faulty by case 
						faultyTripletGenerated = true;
					}

					lineCase.append("(").append(triplet.getEr()).append(", ").append(triplet.getEc()).append(",");
					if (triplet.getIntevalle().getBi() == 0 && triplet.getIntevalle().getBs() == 999999999) {
						lineCase.append("nct)");
					} else {
						lineCase.append("[")// regler le soucis pas nct mais le temps d'avant 
								.append(triplet.getIntevalle().getBi() == 999999999 ? "nct" : triplet.getIntevalle().getBi())//
								.append(", ").append(triplet.getIntevalle().getBs()).append("]) ");
					}
					lineCase.append("*");
				}
				// Remove " * " from the end of the line
				if (lineCase.length() > 3) {
					lineCase.setLength(lineCase.length() - 3);
				}
				writer.write(lineCase.toString());
				writer.newLine();
				// add empty line
				writer.newLine();

			}

			writer.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static Triplet generateFaultyTriplet(List<Triplet> originalTriplets, int faultyIndex) {
		Triplet faultyTriplet = originalTriplets.get(faultyIndex);

		// get triplet that depends on faulty
		originalTriplets.stream().filter(t -> {
			return t.getEr().equals(faultyTriplet.getEc());
		}).collect(Collectors.toList()).forEach(t -> {
			// the triplet that faulty triplet depends on 
			Triplet dependsTriplet = originalTriplets.stream().filter(td -> {
				return td.getEc().equals(faultyTriplet.getEr());
			}).findFirst().orElse(null);
			Intervalle intervalle = new Intervalle();
			intervalle.setBi(dependsTriplet.getIntevalle().getBs());
			intervalle.setBs(t.getIntevalle().getBs());
			t.setIntevalle(intervalle);
			t.setEr(dependsTriplet.getEc());
		});

		// change to synptome
		faultyTriplet.setEc("S" + faultyTriplet.getEc());
		// create same start and end for the intervalle of faulty symptome
		Intervalle faultyIntevalle = new Intervalle();
		faultyIntevalle.setBi(faultyTriplet.getIntevalle().getBs());
		faultyIntevalle.setBs(faultyTriplet.getIntevalle().getBs());
		faultyTriplet.setIntevalle(faultyIntevalle);

		return faultyTriplet;
	}

	public static void main(String[] args) {
		List<Cas> originalCases = DataHandler.getDataFromFile("reglesCN.txt");
		FakeDataGenerator.generateFakeData(originalCases, "fakeData.txt");
		Print.Green("Done");
	}
}
