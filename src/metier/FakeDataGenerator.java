package metier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;

public class FakeDataGenerator {

	public static void generateFakeData(List<Cas> originalCases, String outputFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

			for (Cas originalCas : originalCases) {
				// Write original case data
				for (Triplet triplet : originalCas.getP()) {
					String line = triplet.getEr() + "*" + triplet.getEc() + "*[" + triplet.getIntevalle().getBi() + "," + triplet.getIntevalle().getBs() + "])";
					writer.write(line);
					writer.newLine();
				}

				// Write fake data (triple the original case)
				for (int i = 0; i < 3; i++) {
					Triplet fakeTriplet = generateFakeTriplet(false);
					String fakeLine = fakeTriplet.getEr() + "*" + fakeTriplet.getEc() + "*[" + fakeTriplet.getIntevalle().getBi() + "," + fakeTriplet.getIntevalle().getBs() + "])";
					writer.write(fakeLine);
					writer.newLine();
				}
			}

			writer.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static Triplet generateFakeTriplet(boolean isNormal) {
		Triplet fakeTriplet = new Triplet();

		// Set common values for er, ec
		fakeTriplet.setEr("In");
		fakeTriplet.setEc("A");

		// Initialize Intervalle object
		fakeTriplet.setIntevalle(new Intervalle());

		if (isNormal) {
			// Set values for normal behavior
			fakeTriplet.getIntevalle().setBi(0);
			fakeTriplet.getIntevalle().setBs(999999999);
		} else {
			// Set values for faulty behavior
			fakeTriplet.getIntevalle().setBi(1); // Adjust as needed
			fakeTriplet.getIntevalle().setBs(1); // Adjust as needed
		}

		// Additional logic for setting other triplet attributes based on your scenario

		return fakeTriplet;
	}

	public static void main(String[] args) {
		List<Cas> originalCases = DataHandler.getDataFromFile("reglesCN.txt");
		FakeDataGenerator.generateFakeData(originalCases, "fakeData.txt");

	}
}
