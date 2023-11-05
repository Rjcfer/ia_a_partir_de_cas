package entities.etat;

public class Normal extends Etat {

	private static Normal n = null;

	private Normal() {
		super("normal");
	}

	// singleton no need to create multiple instances a normal case steel a normal case
	public static Normal getNormalInstance() {
		if (n == null) {
			n = new Normal();
		}
		return n;
	}

}
