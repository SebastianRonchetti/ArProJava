package calculo;

import clases.Partido;
import clases.Apostador;
import clases.Equipo;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Resultados {

	private static Partido part;
	private static Apostador apos;
	private static Equipo eq1;
	private static Equipo eq2;
	private static String mensaje; 
	
	private static List<Partido> torneo = new ArrayList<Partido>();
	private static List<Apostador> apostadores = new ArrayList<Apostador>();
	
	public static void main(String[] args) throws Exception {
		
		fillTorneo();
		fillApostadores();
		
		for(Apostador apostador: apostadores) {
			mensaje = "Resultado incorrecto.\n";
			for(Partido partido: torneo) {
				String titularApostador = apostador.getEquipo1();
				String competidorApostador = apostador.getEquipo2();
				String prediccionApostador = apostador.getGanaEmpataPierde();
				String e1Nombre = partido.getEquipo1().getNombre();
				String e2Nombre = partido.getEquipo2().getNombre();
				
				if(e1Nombre.equals(titularApostador) || e2Nombre.equals(titularApostador)) {
					if(e1Nombre.equals(competidorApostador) || e2Nombre.equals(competidorApostador)) {
						String resultado = partido.getResult();
						
						if(resultado != "Empate") {
							if(titularApostador.equals(resultado) && prediccionApostador.equals("G")) {
								apostador.setPuntaje(1);
								
								mensaje = apostador.getNombre() + " acertó en el partido: " + e1Nombre + " vs. " + e2Nombre + 
										". Nuevo puntaje: " + apostador.getPuntaje() + ". Resultado del partido: " + titularApostador + " Ganó.\n";
								
								System.out.print(mensaje);
							} else if (titularApostador != resultado && prediccionApostador.equals("P")){
								apostador.setPuntaje(1);
								
								mensaje = apostador.getNombre() + " acertó en el partido: " + e1Nombre + " vs. " + e2Nombre + 
										". Nuevo puntaje: " + apostador.getPuntaje() + ". Resultado del partido: " + titularApostador + " Perdió.\n";
								
								System.out.print(mensaje);
							} else {
								System.out.print(mensaje);
							}
						} else if(resultado.equals("Empate") && prediccionApostador.equals("E")) {
							apostador.setPuntaje(1);
							
							mensaje = apostador.getNombre() + " acertó en el partido: " + e1Nombre + " vs. " + e2Nombre + 
									". Nuevo puntaje: " + apostador.getPuntaje() + ". Resultado del partido: " + titularApostador + " Empató.\n";
							
							System.out.print(mensaje);
						} else {
							System.out.print(mensaje);
						}
					}
				}
			}
		}
	}
	
	private static void fillTorneo() {
		try {
			for(String linea: Files.readAllLines(Paths.get("src\\main\\java\\Archivo\\Partidos.txt"))) {
				eq1 = new Equipo(linea.split(";")[0], Integer.parseInt(linea.split(";")[1]));
				eq2 = new Equipo(linea.split(";")[2], Integer.parseInt(linea.split(";")[3]));
				
				if(eq1 != null && eq2 != null) {
					part = new Partido(eq1, eq2);
					torneo.add(part);					
				}
				eq1 = null;
				eq2 = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void fillApostadores() throws Exception {
		try {
			for(String linea: Files.readAllLines(Paths.get("src\\main\\java\\Archivo\\apostadores.txt"))) {

				String pronostico = linea.split(";")[3];
				
				if(pronostico.equals("G")|| pronostico.equals("E")|| pronostico.equals("P")) {
					apos = new Apostador(linea.split(";")[0], linea.split(";")[1], linea.split(";")[2], linea.split(";")[3]);
					
					apostadores.add(apos);
				} else {
					System.out.print(pronostico);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}