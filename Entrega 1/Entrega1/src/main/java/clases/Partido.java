package clases;

public class Partido {
	private Equipo equipo1;
	private Equipo equipo2;
	
	public Partido(Equipo equipo1, Equipo equipo2) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}
	
	public Equipo getEquipo1() {
		return equipo1;
	}
	
	public Equipo getEquipo2() {
		return equipo2;
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
