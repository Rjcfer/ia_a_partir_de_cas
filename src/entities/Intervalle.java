package entities;

public class Intervalle {

	private int bi;
	private int bs;

	public Intervalle() {
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
		return "Intervalle [bi=" + bi + ", bs=" + bs + "]";
	}

}
