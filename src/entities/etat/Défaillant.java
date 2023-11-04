package entities.etat;

public class Défaillant {
	private int id_faute;
	private String description;
	private String localisation;

	public Défaillant() {
		super();
	}

	public int getId_faute() {
		return id_faute;
	}

	public void setId_faute(int id_faute) {
		this.id_faute = id_faute;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	@Override
	public String toString() {
		return "Défaillant [id_faute=" + id_faute + ", description=" + description + ", localisation=" + localisation + "]";
	}

}
