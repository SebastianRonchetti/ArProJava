package clases;

public class Apostador {
	private String nombre;
	private String equipo1;
	private String equipo2;
	private String ganaEmpataPierde;
	private int puntaje;
	
	public Apostador(String nombre, String equipo1, String equipo2, String ganaEmpataPierde) {
		this.nombre = nombre;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ganaEmpataPierde = ganaEmpataPierde;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public void setPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
}
