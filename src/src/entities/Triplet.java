package entities;

public class Triplet {

	private int id_triplet;
	private String er;
	private String ec;
	private Intervalle intevalle;

	public Triplet() {
	}

	public int getId_triplet() {
		return id_triplet;
	}

	public void setId_triplet(int id_triplet) {
		this.id_triplet = id_triplet;
	}

	public String getEr() {
		return er;
	}

	public void setEr(String er) {
		this.er = er;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public Intervalle getIntevalle() {
		return intevalle;
	}

	public void setIntevalle(Intervalle intevalle) {
		this.intevalle = intevalle;
	}

	@Override
	public String toString() {
		return "Triplet [id_triplet=" + id_triplet + ", er=" + er + ", ec=" + ec + ", intevalle=" + intevalle + "]";
	}

}
