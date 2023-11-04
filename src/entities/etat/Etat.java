package entities.etat;

public abstract class Etat {

	private int id_etat;

	public int getId_etat() {
		return id_etat;
	}

	@Override
	public String toString() {
		return "Etat [id_etat=" + id_etat + "]";
	}

}
