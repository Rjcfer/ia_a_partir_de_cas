package entities.etat;

public abstract class Etat {

	private int id_etat;
	private String name;

	public Etat(String name) {
		this.name = name;
	}

	public int getId_etat() {
		return id_etat;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Etat [id_etat=" + id_etat + ", name=" + name + "]";
	}

}
