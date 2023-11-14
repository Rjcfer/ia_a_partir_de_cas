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
				StringBuilder line = new StringBuilder();

				// Write original case data
				for (Triplet triplet : originalCas.getP()) {
					line.append("(").append(triplet.getEr()).append(", ").append(triplet.getEc()).append(", [").append(triplet.getIntevalle().getBi()).append(", ").append(triplet.getIntevalle().getBs()).append("]) * ");
				}

				// Remove  " * " from the end of line 
				if (line.length() > 3) {
					line.setLength(line.length() - 3);
				}

				writer.write(line.toString());
				writer.newLine();

				// Allow different scenarios
				for (int i = 0; i < 3; i++) {
					Triplet fakeTriplet = generateFakeTriplet(false, i);
					String fakeLine = "(" + fakeTriplet.getEr() + ", " + fakeTriplet.getEc() + ", [" + fakeTriplet.getIntevalle().getBi() + ", " + fakeTriplet.getIntevalle().getBs() + "])";
					writer.write(fakeLine);
					writer.newLine();
				}
			}

			writer.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static Triplet generateFakeTriplet(boolean isNormal, int faultyScenario) {
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
			switch (faultyScenario) {
				case 1:
					// Scenario 1: absence of B
					fakeTriplet.getIntevalle().setBi(1); // Adjust as needed
					fakeTriplet.getIntevalle().setBs(1); // Adjust as needed
					break;
				case 2:
					// Scenario 2: absence of C
					fakeTriplet.getIntevalle().setBi(1); // Adjust as needed
					fakeTriplet.getIntevalle().setBs(1); // Adjust as needed
					break;
				case 3:
					// Scenario 3: absence of D
					fakeTriplet.getIntevalle().setBi(1); // Adjust as needed
					fakeTriplet.getIntevalle().setBs(1); // Adjust as needed
					break;
				// Add more scenarios as needed
				default:
					// Default: absence of B
					fakeTriplet.getIntevalle().setBi(1); // Adjust as needed
					fakeTriplet.getIntevalle().setBs(1); // Adjust as needed
					break;
			}
		}
		return fakeTriplet;
	}

	public static void main(String[] args) {
		List<Cas> originalCases = DataHandler.getDataFromFile("reglesCN.txt");
		FakeDataGenerator.generateFakeData(originalCases, "fakeData.txt");

		Print.Green("Done");

	}
}
