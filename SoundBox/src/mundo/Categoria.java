package mundo;

import java.io.Serializable;

import ListaOrdenada.ListaOrdenada;


/**
 * @author s.florez10
 * @version 1.0
 * @created 04-Feb-2014 11:09:59 PM
 */
public class Categoria implements Comparable<Categoria>,Serializable {

	/**
	 * Constante que representa sonido sin categoria
	 */
	public static final String SIN_CATEGORIA = "Sin Categoria";
	
	/**
	 * El nombre de la categoria
	 */
	private String nombre;
	
	/**
	 * La lista de sonidos que tiene la categoria
	 */
	private ListaOrdenada<Sample> sonidos;

	/**
	 * Metodo que construye una nueva categoria
	 * @param nNombre El nombre de la categoria.
	 */
	public Categoria(String nNombre){	
		nombre = nNombre;
		sonidos = new ListaOrdenada<Sample>();
	}

	/**
	 * Metodo que construye una categoria. EL nombre se establece con la constante de la clase
	 */
	public Categoria(){
		nombre = SIN_CATEGORIA;
		sonidos = new ListaOrdenada<Sample>();
	}
	
	/**
	 * Retorna el nombre de la categoria.
	 * @return EL nombre de la categoria
	 */
	public String darNombre(){
		return nombre;
	}
	
	/**
	 * Metodo que retorna los sonidos de la categoria.
	 * @return Arreglo de sonidos
	 */
	public Object[] darSonidos(){
		return sonidos.darElementos();
	}

	/**
	 * Metodo que agrega el sonido dado por paramtro a la categoria.
	 * @param nSample El sonido a agregar.
	 * @return EL sonido agregado
	 */
	public Sample agregarSonido(Sample nSample){
		return sonidos.agregar(nSample);
	}
	
	/**
	 * Metodo que elimina un sonido dado por parametro
	 * @param nSample EL sonido a eliminar
	 * @return El sonido eliminado
	 */
	public Sample eliminarSonido(Sample nSample){
		return sonidos.eliminar(nSample);
	}
	
	/**
	 * Metodo que compara dos categorias a partir de su nombre
	 */
	public int compareTo(Categoria nCategoria) {
		if(nombre.compareTo(nCategoria.darNombre())<0){
			return -1;
		}
		else if(nombre.compareTo(nCategoria.darNombre())>0){
			return 1;
		}
		else{
			return 0;
		}
	}

	/**
	 * Metodo que elimina la categoria de los sonidos a los que pertenece
	 */
	public void eliminarDeSonidos() {
		Object[] lista =  sonidos.darElementos();
		for(int i = 0; i<lista.length;i++){
			((Sample) lista[i]).eliminarCategoria(this);
		}
	}
	
	/**
	 * Metdoo to stirng de la categoria
	 * @return El nombre de la categoria
	 */
	public String toString(){
		return nombre;
	}

}
