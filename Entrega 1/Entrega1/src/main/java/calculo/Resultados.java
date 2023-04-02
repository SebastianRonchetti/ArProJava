package calculo;

import clases.Partido;
import clases.Apostador;
import clases.Equipo;
import clases.Prediccion;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Resultados {

	private static Partido part;
	private static Apostador apos;
	private static Prediccion pred;
	private static Equipo eq1;
	private static Equipo eq2;
	private static String mensaje; 
	
	private static List<Partido> torneo = new ArrayList<Partido>();
	private static List<Apostador> apostadores = new ArrayList<Apostador>();
	
	public static void main(String[] args) throws Exception {
		
		fillTorneo();
		fillApostadores();
		
		for(Apostador apostador: apostadores) {
			for(Prediccion pre: apostador.getPredicciones()) {
				mensaje = "Resultado incorrecto.\n";
				for(Partido partido: torneo) {
					String titularApostador = pre.getEquipo1();
					String competidorApostador = pre.getEquipo2();
					String prediccionApostador = pre.getGanaEmpataPierde();
					String e1Nombre = partido.getEquipo1().getNombre();
					String e2Nombre = partido.getEquipo2().getNombre();
					
					if(e1Nombre.equals(titularApostador) || e2Nombre.equals(titularApostador)) {
						if(e1Nombre.equals(competidorApostador) || e2Nombre.equals(competidorApostador)) {
							String resultado = partido.getResult();
							/*System.out.printf("\n" + apostador.getNombre() + " " + apostador.getPuntaje() + " " + titularApostador + " " 
									+ e1Nombre + " " + competidorApostador + " " + e2Nombre + " " + prediccionApostador + " " + resultado);*/
							if(resultado != "Empate") {
								if(titularApostador.equals(resultado) && prediccionApostador.equals("G1")) {
									apostador.addToPuntaje(1);
									//System.out.print("\n" + apostador.getNombre() + " Gana titular");
								} else if (!titularApostador.equals(resultado) && prediccionApostador.equals("G2")){
									apostador.addToPuntaje(1);
									//System.out.print("\n" + apostador.getNombre()  + " gana competidor" + titularApostador);
								}
							} else if(resultado.equals("Empate") && prediccionApostador.equals("E")) {
								apostador.addToPuntaje(1);
								//System.out.printf("\n" + apostador.getNombre() + " Empata");
							}
							
							System.out.printf("\n" + apostador.getNombre() + " " + apostador.getPuntaje() + " " + titularApostador + " " 
									+ e1Nombre + " " + competidorApostador + " " + e2Nombre + " " + prediccionApostador + " " + resultado);
						}
					}
				}
			}
			System.out.printf("\n"+ apostador.getNombre()+ " " + apostador.getPuntaje());
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
				apos = null;
				int nroMiembro = Integer.parseInt(linea.split(";")[0]);
				String pronostico = linea.split(";")[4];
				
				for(Apostador ap: apostadores) {
					int nroActual = ap.getNroMiembro();
					if(nroActual == nroMiembro) {
						apos = ap;
						break;
					}
				}
				
				if(apos == null) {
					apos = new Apostador(Integer.parseInt(linea.split(";")[0]), linea.split(";")[1]);
					apostadores.add(apos);
				}
				
				if(pronostico.equals("G1")|| pronostico.equals("E")|| pronostico.equals("G2")) {
					pred = null;
					pred = new Prediccion(linea.split(";")[2], linea.split(";")[3], pronostico);
					apos.getPredicciones().add(pred);
					
				} else {
					System.out.print(pronostico);
				}
			}
			
			apos = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}