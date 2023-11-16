package metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;
import logger.Print;

public class FakeDataGenerator {

	public static void generateFakeData(List<Cas> originalCases, String outputFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

			for (Cas originalCas : originalCases) {
				// Write original case data
				StringBuilder normalLine = new StringBuilder();
				for (Triplet triplet : originalCas.getP()) {
					normalLine.append("(").append(triplet.getEr()).append(", ").append(triplet.getEc()).append(",");
					if (triplet.getIntevalle().getBi() == 0 && triplet.getIntevalle().getBs() == 999999999) {
						normalLine.append("nct)");
					} else {
						normalLine.append("[").append(triplet.getIntevalle().getBi()).append(", ").append(triplet.getIntevalle().getBs()).append("]) ");
					}
					normalLine.append("*");
				}
				// Remove " * " from the end of the line
				if (normalLine.length() > 3) {
					normalLine.setLength(normalLine.length() - 3);
				}
				writer.write(normalLine.toString());
				writer.newLine();

				// Generate and write faulty case data
				StringBuilder faultyLine = new StringBuilder();
				for (int i = 0; i < originalCas.getP().size(); i++) {
					Triplet normalTriplet = originalCas.getP().get(i);
					Triplet faultyTriplet = generateFaultyTriplet(originalCas.getP(), i);

					faultyLine.append("(").append(faultyTriplet.getEr()).append(", ").append(faultyTriplet.getEc()).append(",");
					if (faultyTriplet.getIntevalle().getBi() == 0 && faultyTriplet.getIntevalle().getBs() == 999999999) {
						faultyLine.append("nct)");
					} else {
						faultyLine.append("[").append(faultyTriplet.getIntevalle().getBi()).append(", ").append(faultyTriplet.getIntevalle().getBs()).append("]) ");
					}
					faultyLine.append("*");
				}
				// Remove " * " from the end of the line
				if (faultyLine.length() > 3) {
					faultyLine.setLength(faultyLine.length() - 3);
				}
				writer.write(faultyLine.toString());
				writer.newLine();
			}

			writer.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static Triplet generateFaultyTriplet(List<Triplet> originalTriplets, int faultyIndex) {
		Triplet faultyTriplet = new Triplet();
		// Set common values for er, ec
		faultyTriplet.setEr("In");
		faultyTriplet.setEc("S" + originalTriplets.get(faultyIndex).getEc()); // Add "S" prefix

		// Initialize Intervalle object
		faultyTriplet.setIntevalle(new Intervalle());

		// Copy values from the original triplet
		faultyTriplet.getIntevalle().setBi(originalTriplets.get(faultyIndex).getIntevalle().getBi());
		faultyTriplet.getIntevalle().setBs(originalTriplets.get(faultyIndex).getIntevalle().getBs());

		return faultyTriplet;
	}

	public static void main(String[] args) {
		List<Cas> originalCases = DataHandler.getDataFromFile("reglesCN.txt");
		FakeDataGenerator.generateFakeData(originalCases, "fakeData.txt");

		Print.Green("Done");
	}
}
