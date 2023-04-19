package clases;

import java.util.List;
import java.util.ArrayList;
import clases.Prediccion;

public class Apostador {
	private int nroMiembro;
	private String nombre;
	private int puntaje;
	private int aciertosRonda;
	private int aciertosFase;
	
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

	public int getAciertosFase() {
		return aciertosFase;
	}

	public int getAciertosRonda() {
		return aciertosRonda;
	}

	public void addAciertosRonda(int aciertosRonda) {
		this.aciertosRonda += aciertosRonda;
		aciertosFase += aciertosRonda;
	}
	
	public void resetRonda() {
		this.aciertosRonda = 0;
	}
	
	public void resetFase() {
		this.aciertosFase = 0;
	}
}
