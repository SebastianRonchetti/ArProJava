package clases;

public class Partido {
	private Equipo equipo1;
	private Equipo equipo2;
	private int ronda;
	private int fase;
	
	public Partido(Equipo equipo1, Equipo equipo2, int ronda, int fase) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ronda = ronda;
		this.fase = fase;
	}
	
	public Equipo getEquipo1() {
		return equipo1;
	}
	
	public Equipo getEquipo2() {
		return equipo2;
	}
		
	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public String getResult() {
		if(equipo1.getPuntaje() > equipo2.getPuntaje()) {
			return equipo1.getNombre();
		} else if(equipo1.getPuntaje() < equipo2.getPuntaje()) {
			return equipo2.getNombre();
		} else {
			return "Empate";
		}
	}
}
