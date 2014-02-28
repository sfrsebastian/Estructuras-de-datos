package mundo;

public class AnioAcademico {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * 
	 */
	private int valor;
	
	/**
	 * 
	 */
	private Area[] areas;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public AnioAcademico(int nValor){
		valor = nValor;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Area[] getAreas() {
		return areas;
	}

	public void setAreas(Area[] areas) {
		this.areas = areas;
	}
	
	
	
}
