package mundo;

import java.io.Serializable;

import Lista.Lista;
import ListaOrdenada.ListaOrdenada;


public class Hijo implements Comparable<Hijo>,Serializable{

	public final static int MASCULINO = 1;
	
	public final static int FEMENINO = 2;
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La lista ordenada de los colegios favoritos
	 */
	private ListaOrdenada<Colegio> listaColegioFav;
	
	/**
	 * El nombre del hijo
	 */
	private String nombre;
	
	/**
	 * La edad del hijo
	 */
	private int edad;
	
	/**
	 * El genero del hijo
	 */
	private int genero;
	
	/**
	 * La ciudad del hijo
	 */
	private String ciudad;
	
	/**
	 * El telefono del hijo
	 */
	private int telefono;
	
	/**
	 * El nombre del acudiente del hijo
	 */
	private String acudiente;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Contruye un nuevo hijo con la informacion dada
	 * @param nNombre
	 * @param edad
	 * @param genero
	 * @param nCiudad
	 * @param nTelefono
	 * @param nAcudiente
	 */
	public Hijo(String nNombre, int nEdad, int nGenero, String nCiudad, int nTelefono, String nAcudiente){
		nombre = nNombre;
		edad = nEdad;
		genero = nGenero;
		ciudad = nCiudad;
		telefono = nTelefono;
		acudiente = nAcudiente;
		
		listaColegioFav = new ListaOrdenada<Colegio>();
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	/**
	 * Agrega un colegio a la lista de colegios favoritos del hijo
	 * @param colegio El colegio que se quiere agregar
	 * @return TRUE si se agrego, FALSE en caso contrario
	 */
	public boolean agregarFavorito(Colegio colegio){
		Colegio col = listaColegioFav.agregar(colegio);
		if(col != null)
			return true;
		else
			return false;
	}
	
	/**
	 * Elimina un colegio de la lista de colegios del hijo
	 * @param colegio El colegio que se quiere eliminar
	 * @return TRUE si se elimino, FALSE en caso contrario
	 */
	public boolean eliminarFavorito(Colegio colegio){
		Colegio col = listaColegioFav.eliminar(colegio);
		if(col != null)
			return true;
		else
			return false;
	}
	
	@Override
	public int compareTo(Hijo o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListaOrdenada<Colegio> getListaColegioFav() {
		return listaColegioFav;
	}
	
	public Object[] darColegiosFavoritos(){
		return listaColegioFav.darArreglo();
	}

	public void setListaColegioFav(ListaOrdenada<Colegio> listaColegioFav) {
		this.listaColegioFav = listaColegioFav;
	}

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

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getAcudiente() {
		return acudiente;
	}

	public void setAcudiente(String acudiente) {
		this.acudiente = acudiente;
	}
	
	@Override
	public String toString(){
		return nombre + " - " + edad;
	}

	public Object[] darColegioRecomendados() {
		ListaOrdenada<Colegio> cols = new ListaOrdenada<Colegio>();
		for (Object colegio : listaColegioFav) {
			Colegio colR = (Colegio)colegio;
			if(colR.darPromedio() >= 7){
				cols.agregar(colR);
			}
		}
		return cols.darArreglo();
	}

}
