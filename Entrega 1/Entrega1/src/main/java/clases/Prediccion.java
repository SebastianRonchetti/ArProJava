package clases;

public class Prediccion {
	private String equipo1;
	private String equipo2;
	private String ganaEmpataPierde;
	
	public Prediccion(String equipo1, String equipo2, String ganaEmpataPierde) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ganaEmpataPierde = ganaEmpataPierde;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public String getGanaEmpataPierde() {
		return ganaEmpataPierde;
	}

	public void setGanaEmpataPierde(String ganaEmpataPierde) {
		this.ganaEmpataPierde = ganaEmpataPierde;
	}
}
