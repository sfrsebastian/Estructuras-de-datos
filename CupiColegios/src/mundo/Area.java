package mundo;

import java.io.Serializable;

public class Area implements Comparable<Area>,Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	public final static int NO_APLICA = -1;
	public final static String SOCIALES = "Sociales";
	public final static String QUIMICA = "Quimica";
	public final static String FISICA = "Fisica";
	public final static String BIOLOGIA = "Biologia";
	public final static String FILOSOFIA = "Filosofia";
	public final static String MATEMATICAS = "Matematicas";
	public final static String LENGUAJE = "Lenguaje";
	public final static String INGLES = "Ingles";
	public final static String GEOGRAFIA = "Geografia";
	public final static String HISTORIA = "Historia";
	
	/**
	 * 
	 */
	private int puntaje;
	
	/**
	 * 
	 */
	private String area;
	
	//------------------------------------------
	// Contructor
	//------------------------------------------
	
	public Area(String nArea, int nPuntaje){
		puntaje = nPuntaje;
		area = nArea;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public int getPuntaje() {
		return puntaje;
	}

	public String getArea() {
		return area;
	}
	@Override
	public int compareTo(Area otra) {
		if(area.compareTo(otra.getArea())==0){
			return 0;
		}
		else if(area.compareTo(otra.getArea())<0){
			return -1;
		}
		else{
			return 1;
		}
	}
}
