package mundo;

public class Area {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
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

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
