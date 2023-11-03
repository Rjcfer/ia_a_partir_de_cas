package entities;

public class Etat {

	public Etat() {
		// voir pour auto incremnet 
	}

	private int id_etat;

	public int getId_etat() {
		return id_etat;
	}

	@Override
	public String toString() {
		return "Etat [id_etat=" + id_etat + "]";
	}

}
