package mundo;

import java.io.Serializable;

import HashTable.TablaHashing;

public class Municipio implements Comparable <Municipio>,Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El nombre del municipio
	 */
	private String nombre;
	
	/**
	 * El codigo unico del municipio
	 */
	private int codigo;
	
	/**
	 * Tabla con los colegios del municipio
	 */
	private TablaHashing<Llave, Colegio> colegios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Se crea un nuevo municipio
	 * @param nNombre
	 * @param nCodigo
	 */
	public Municipio(String nNombre, int nCodigo){
		nombre = nNombre;
		codigo = nCodigo;
		colegios = new TablaHashing<Llave, Colegio>(1000,2);
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------

	/**
	 * El nombre del municipio
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * El codigo del municipio
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * La tabla con los colegios del municipio
	 * @return
	 */
	public TablaHashing<Llave, Colegio> getColegios() {
		return colegios;
	}

	/**
	 * Agrega un nuevo colegio a la tabla de colegios.
	 * @param llave
	 * @param colegio
	 */
	public void agregarColegio(Llave llave, Colegio colegio){
		colegios.agregar(llave, colegio);
	}
	
	/**
	 * Metodo que compara dos municipios.
	 */
	@Override
	public int compareTo(Municipio otro) {
		if(nombre.compareTo(otro.getNombre())==0){
			return 0;
		}
		else if(nombre.compareTo(otro.getNombre())>0){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * To string del municipio. Incluye su nombre.
	 */
	@Override
	public String toString(){
		return nombre;
	}
	
}
