package entities.etat;

public class Defaillant extends Etat {
	String localisation;
	String description;

	public Defaillant(String description, String localisation) {
		super();
		this.description = description;
		this.localisation = localisation;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String toString() {
		return "Defaillant [localisation=" + localisation + ", description=" + description + "]";
	}
	

}
