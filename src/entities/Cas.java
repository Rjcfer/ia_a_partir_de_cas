package entities;

public class Cas {

	private int id;
	private Triplet p;
	private Etat s;

	public Cas() {

	}

	public Triplet getP() {
		return p;
	}

	public void setP(Triplet p) {
		this.p = p;
	}

	public Etat getS() {
		return s;
	}

	public void setS(Etat s) {
		this.s = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cas [id=" + id + ", p=" + p + ", s=" + s + "]";
	}

}
