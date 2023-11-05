package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import entities.etat.Etat;

public class Cas {

	private int id;
	private static AtomicInteger counter = new AtomicInteger(0);
	private List<Triplet> p = new ArrayList<>();
	private Etat s;

	public Cas() {
		id = counter.incrementAndGet();
	}

	public List<Triplet> getP() {
		return p;
	}

	public void setP(List<Triplet> p) {
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
