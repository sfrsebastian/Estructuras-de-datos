package mundo;

import java.io.Serializable;

public class Area implements Comparable<Area>,Serializable {

	//------------------------------------------
	// Constantes
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
	 * El puntaje en el area
	 */
	private int puntaje;
	
	/**
	 * El nombre del area
	 */
	private String area;
	
	//------------------------------------------
	// Contructor
	//------------------------------------------
	/**
	 * Inicializa una nueva area
	 * @param nArea Debe ser alguna de las constantes
	 * @param nPuntaje El puntaje obtenido
	 */
	public Area(String nArea, int nPuntaje){
		puntaje = nPuntaje;
		area = nArea;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	/**
	 * Retorna el puntaje obtenido en el Area.
	 * @return
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * Retorna el nombre del area.
	 * @return
	 */
	public String getArea() {
		return area;
	}
	
	/**
	 * Metodo para comparar dos areas.
	 */
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
