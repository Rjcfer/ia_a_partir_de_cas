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

	private static DataHandler dh = null;

	private DataHandler() {

	}

	public static List<Cas> getDataFromFile(String path) {
		// only load data once 
		if (cases.size() == 0) {
			File file = new File(path);

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
							t.setEc(tripletElements[1].trim().substring(0, tripletElements[1].trim().length()));

							String intervalleElement = tripletElements[2];

							// when intervalle exists 
							Intervalle i = new Intervalle();
							if (!intervalleElement.trim().equals("nct)")) {
								// remove sapces and '['from the start  
								i.setBi(Integer.parseInt(intervalleElement.trim().substring(1)));
								// remove '])'
								i.setBs(Integer.parseInt(tripletElements[3].trim().substring(0, tripletElements[3].trim().length() - 2)));
							} else {
								i.setBi(0);
								i.setBs(999999999);
							}
							// add because triplet 
							t.setIntevalle(i);

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
		}
		return cases;
	}

	public static DataHandler getDataHandlerInstance() {
		if (dh == null) {
			dh = new DataHandler();
		}
		return dh;
	}

}
