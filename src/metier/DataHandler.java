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
		File file = new File("reglesCN.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				// verify empty line
				if (!line.trim().isEmpty()) {
					// split by separator
					String[] elements = line.split("\\*");

					for (String element : elements) {
						cases.add(element);
					}
				}
			}
			// dont need br anymore so close it 
			br.close();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		DataHandler dataHandler = new DataHandler();
		dataHandler.readfile();

		for (String element : dataHandler.cases) {
			System.out.println(element);
		}
	}

}
