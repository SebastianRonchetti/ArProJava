package clases;

import java.util.ArrayList;
import java.util.List;

public class Fase {
	private List<Ronda> fase = new ArrayList<Ronda>();
	private int nroFase;

	public Fase(List<Ronda> fase) {
		this.fase = fase;
	}

	public List<Ronda> getFase() {
		return fase;
	}

	public void setFase(List<Ronda> fase) {
		this.fase = fase;
	}

	public int getNroFase() {
		return nroFase;
	}

	public void setNroFase(int nroFase) {
		this.nroFase = nroFase;
	}
}
