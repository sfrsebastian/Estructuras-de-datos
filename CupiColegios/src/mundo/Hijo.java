package mundo;

import java.io.Serializable;

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
	
	/**
	 * Metodo que compara dos hijos
	 */
	@Override
	public int compareTo(Hijo otro) {
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
	 * Metodo que retorna la lista de favoritos del hijo
	 * @return
	 */
	public ListaOrdenada<Colegio> getListaColegioFav() {
		return listaColegioFav;
	}
	
	/**
	 * Retorna los colegios favoritos del hijo
	 * @return
	 */
	public Object[] darColegiosFavoritos(){
		return listaColegioFav.darArreglo();
	}

	/**
	 * El nombre del hijo
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * La edad del hijos
	 * @return
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * El genero del hijo.
	 * @return
	 */
	public int getGenero() {
		return genero;
	}
	
	/**
	 * La ciudad del hijos
	 * @return
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * El telefono del hijo
	 * @return
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * El acudiente del hijo
	 * @return
	 */
	public String getAcudiente() {
		return acudiente;
	}
	
	/**
	 * To string del hijo. Incluye su nombre y edad.
	 */
	@Override
	public String toString(){
		return nombre + " - " + edad;
	}

	/**
	 * Metodo que retorna los colegios recomendados para el hijo.
	 * @return
	 */
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
