package testLista;

public class Enfermera implements Comparable<Enfermera>{
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	private String nombre;
	
	private int edad;
	
	private String cedula;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * 
	 * @param nNombre
	 * @param nEdad
	 * @param nCedula
	 */
	public Enfermera(String nNombre, int nEdad, String nCedula){
		nombre = nNombre;
		edad = nEdad;
		cedula = nCedula;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int compareTo(Enfermera o) {
		if(cedula.equals(o.getCedula())){
			return 0;
		}else{
			return -1;
		}
	}
	
}
