package clases;

import java.util.List;
import java.util.ArrayList;
import clases.Prediccion;

public class Apostador {
	private int nroMiembro;
	private String nombre;
	private int puntaje;
	
	private List<Prediccion> predicciones = new ArrayList<Prediccion>();
	
	public Apostador(int nro, String nombre) {
		this.nroMiembro = nro;
		this.nombre = nombre;
	}

	public int getNroMiembro() {
		return nroMiembro;
	}

	public void setNroMiembro(int nroMiembro) {
		this.nroMiembro = nroMiembro;
	}

	public List<Prediccion> getPredicciones() {
		return predicciones;
	}

	public void setPredicciones(List<Prediccion> predicciones) {
		this.predicciones = predicciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addToPuntaje(int suma) {
		this.puntaje += suma;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
}
