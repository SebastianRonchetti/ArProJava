package clases;

import java.util.List;
import java.util.ArrayList;

public class Ronda {
	private List<Partido> ronda = new ArrayList<Partido>();
	private int nroRonda;

	public Ronda(List<Partido> ronda) {
		this.ronda = ronda;
	}

	public List<Partido> getRonda() {
		return ronda;
	}

	public void setRonda(List<Partido> ronda) {
		this.ronda = ronda;
	}

	public int getNroRonda() {
		return nroRonda;
	}

	public void setNroRonda(int nroRonda) {
		this.nroRonda = nroRonda;
	}
}
