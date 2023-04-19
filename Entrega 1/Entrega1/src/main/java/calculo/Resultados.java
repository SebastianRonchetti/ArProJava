package calculo;

import clases.Partido;
import clases.Apostador;
import clases.Equipo;
import clases.Prediccion;
import clases.Ronda;
import clases.Fase;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

@SuppressWarnings("unused")
public class Resultados {

	private static Partido part;
	private static Apostador apos;
	private static Prediccion pred;
	private static Equipo eq1;
	private static Equipo eq2;
	private static int ron;
	private static int fas;
	private static String mensaje;
	
	private static int aciertoPartido;
	private static int aciertoRonda;
	private static int aciertoFase;
	
	private static Ronda ronda;
	private static Fase fase;
	private static List<Fase> torneo = new ArrayList<Fase>();
	private static List<Apostador> apostadores = new ArrayList<Apostador>();
	
	public static void main(String[] args) throws Exception {
		
		assignPuntajes(1,2,3);
		fillTorneo();
		fillApostadores();
		
		for(Apostador apostador: apostadores) {
			for(Prediccion pre: apostador.getPredicciones()) {
				mensaje = "Resultado incorrecto.\n";
				for(Fase fasee: torneo) {
					for(Ronda rondaa: fasee.getFase()) {
						for(Partido partido: rondaa.getRonda()) {
							String titularApostador = pre.getEquipo1();
							String competidorApostador = pre.getEquipo2();
							String prediccionApostador = pre.getGanaEmpataPierde();
							String e1Nombre = partido.getEquipo1().getNombre();
							String e2Nombre = partido.getEquipo2().getNombre();
							
							if(e1Nombre.equals(titularApostador) || e2Nombre.equals(titularApostador)) {
								if(e1Nombre.equals(competidorApostador) || e2Nombre.equals(competidorApostador)) {
									String resultado = partido.getResult();
									if(resultado != "Empate") {
										if(titularApostador.equals(resultado) && prediccionApostador.equals("G1")) {
											apostador.addToPuntaje(aciertoPartido);
											apostador.addAciertosRonda(1);
										} else if (!titularApostador.equals(resultado) && prediccionApostador.equals("G2")){
											apostador.addToPuntaje(aciertoPartido);
											apostador.addAciertosRonda(1);
										}
									} else if(resultado.equals("Empate") && prediccionApostador.equals("E")) {
										apostador.addToPuntaje(aciertoPartido);
										apostador.addAciertosRonda(1);
									}
									
									System.out.printf("\n" + apostador.getNombre() + " " + apostador.getPuntaje() + " " + titularApostador + " " 
											+ e1Nombre + " " + competidorApostador + " " + e2Nombre + " " + prediccionApostador + " " + resultado);
								}
							}
						}
						
						if(apostador.getAciertosRonda() >= rondaa.getRonda().size()) {
							apostador.addToPuntaje(aciertoRonda);
						}
						apostador.resetRonda();
					}
					if(apostador.getAciertosFase() >= fasee.getFase().size()) {
						apostador.addToPuntaje(aciertoRonda);
					}
					apostador.resetFase();
					
				}
			}
			System.out.printf("\n"+ apostador.getNombre()+ " " + apostador.getPuntaje());
		}
	}
	
	private static void assignPuntajes(int puntPartido, int puntRonda, int puntFase ) {
		aciertoPartido = puntPartido;
		aciertoRonda = puntRonda;
		aciertoFase = puntFase;
	}
	
	private static void fillTorneo() {
		try {
			for(String linea: Files.readAllLines(Paths.get("src\\main\\java\\Archivo\\Partidos.txt"))) {
				eq1 = new Equipo(linea.split(";")[0], Integer.parseInt(linea.split(";")[1]));
				eq2 = new Equipo(linea.split(";")[2], Integer.parseInt(linea.split(";")[3]));
				ron = Integer.parseInt(linea.split(";")[4]);
				fas = Integer.parseInt(linea.split(";")[5]);
				
				if(eq1 != null && eq2 != null) {
					part = new Partido(eq1, eq2, ron, fas);
					
					if(torneo.isEmpty()) {
						fase = new Fase(new ArrayList<Ronda>());
						fase.setNroFase(fas);
						torneo.add(fase);
					} else {
						for(Fase fases: torneo) {
							if(fases.getNroFase() == fas) {
								fase = fases;
								break;
							}
						}
						
						if(fase == null) {
							fase = new Fase(new ArrayList<Ronda>());
							fase.setNroFase(fas);
						}
					}
					
					if(fase.getFase().isEmpty()) {
						ronda = new Ronda(new ArrayList<Partido>());
						ronda.setNroRonda(ron);
					} else {
						for(Ronda rondas: fase.getFase()) {
							if(rondas.getNroRonda() == ron) {
								ronda = rondas;
								break;
							}
						}
						
						if(ronda == null) {
							ronda = new Ronda(new ArrayList<Partido>());
							ronda.setNroRonda(ron);
						}
					}
					
					ronda.getRonda().add(part);
					fase.getFase().add(ronda);
					torneo.add(fase);
				}
				eq1 = null;
				eq2 = null;
				fase = null;
				ronda = null;
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

/*public static void main(String[] args) {

try{  

	Class.forName("org.postgresql.Driver");  

	Connection con=DriverManager.getConnection("jdbc:postgresql://192.168.0.104:5433/1c","postgres","inside");  

	Statement stmt=con.createStatement();  

	//USO DE LA DB  

	System.out.println("conecto");  



	//ResultSet rs=stmt.executeQuery("select * from color");  

	//while(rs.next())  

	//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  



	//int result=stmt.executeUpdate("delete from emp where id=33");  

	//System.out.println(result + " records affected");  



	con.close();  

	} catch(Exception e){ System.out.println(e);}  
	}
}

/*<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
	
	<dependency>
    	<groupId>com.mysql</groupId>
    	<artifactId>mysql-connector-j</artifactId>
    	<version>x.y.z</version>
	</dependency>
	
	Class.forName("com.mysql.jdbc.Driver");  

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname","root","password");  

Statement stmt=con.createStatement();*/