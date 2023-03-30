package clases;

public class Equipo {
	private String Nombre;
	private int Puntaje;
	
	public Equipo(String nombre, int puntaje) {
		Nombre = nombre;
		Puntaje = puntaje;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getPuntaje() {
		return Puntaje;
	}
	public void setPuntaje(int puntaje) {
		Puntaje = puntaje;
	}
}
