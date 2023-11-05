package metier;

import java.util.List;

import entities.Cas;

public class SimilarityCalculator {

	List<Cas> normalCases;

	public SimilarityCalculator() {
		//load data when class starts maybe singleton after ?
		loadData();

	}

	private void loadData() {
		normalCases = DataHandler.getDataFromFile();
	}

	// TODO calculate similarities here	

	// main to test TODO delete this when finish 
	public static void main(String[] args) {
		SimilarityCalculator s = new SimilarityCalculator();
		for (Cas c : s.normalCases) {
			System.out.println(c);
		}
	}

}
