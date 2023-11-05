package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Cas;
import entities.Intervalle;
import entities.Triplet;
import entities.etat.Normal;

public class DataHandler {
	private static List<Cas> cases = new ArrayList<>();

	public static List<Cas> getDataFromFile() {
		File file = new File("reglesCN.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {

				// verify empty line and add all triplet by case 
				if (!line.trim().isEmpty()) {
					Cas cas = new Cas();
					// split by separator
					String[] elements = line.split("\\*");
					for (String element : elements) {
						Triplet t = new Triplet();
						String[] tripletElements = element.split(",");
						// remove sapces and '('from the start  and  ')' from the end 
						t.setEr(tripletElements[0].trim().substring(1));
						t.setEc(tripletElements[1].trim().substring(0, tripletElements[1].trim().length() - 1));

						String intervalleElement = tripletElements[2];
						if (!intervalleElement.trim().equals("nct)")) {
							// when intervalle exists 
							Intervalle i = new Intervalle();

							// remove sapces and '['from the start  
							i.setBi(Integer.parseInt(intervalleElement.trim().substring(1)));
							// remove '])'
							i.setBs(Integer.parseInt(tripletElements[3].trim().substring(0, tripletElements[3].trim().length() - 2)));

							// add because triplet 
							t.setIntevalle(i);
						}
						// normal cases 
						cas.setS(Normal.getNormalInstance());

						// add triplet to list
						cas.getP().add(t);
					}
					cases.add(cas);
				}
			}

			// dont need bufferedReader anymore so close it 
			br.close();

		} catch (IOException e) {
			System.err.println(e);
		}
		return cases;
	}

}
