package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
	private List<String> cases = new ArrayList<>();

	private void readfile() {
		// Chemin du fichier de données passé en paramètre
		File file = new File("reglesCN.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				//sometimes line is empty
				if (!line.trim().isEmpty()) {
					// split by * element
					String[] elements = line.split("\\*");
					// add every element to list
					for (String element : elements) {
						// if no element 
						if (!element.trim().isEmpty())
							cases.add(element);
					}
				}
			}

			// Close br 
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DataHandler dataHandler = new DataHandler();
		dataHandler.readfile();

		for (String element : dataHandler.cases) {
			System.out.println("el");
			System.out.println(element);
		}
	}
}
