package entities.etat;

public class NonIdentifie extends Etat {

	private static NonIdentifie ni = null;

	private NonIdentifie() {
		super("non identifie");
	}

	// singleton no need to create multiple instances a NonIdentifie will allways be non idenfie
	public static NonIdentifie getNonIdentifieInstance() {
		if (ni == null) {
			ni = new NonIdentifie();
		}
		return ni;
	}

}
