package entities;

public class Intervalle {

	private int id_intervalle;
	private int bi;
	private int bs;

	public Intervalle() {

	}

	public int getId_intervalle() {
		return id_intervalle;
	}

	public void setId_intervalle(int id_intervalle) {
		this.id_intervalle = id_intervalle;
	}

	public int getBi() {
		return bi;
	}

	public void setBi(int bi) {
		this.bi = bi;
	}

	public int getBs() {
		return bs;
	}

	public void setBs(int bs) {
		this.bs = bs;
	}

	@Override
	public String toString() {
		return "Intervalle [id_intervalle=" + id_intervalle + ", bi=" + bi + ", bs=" + bs + "]";
	}

}
