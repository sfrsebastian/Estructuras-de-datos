package componenteSearch.mundo;

import ArbolAVl.ArbolBinarioAVLOrdenado;

public class Categoria implements Comparable <Categoria> {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El nombre de la categoria.
	 */
	private String nombre;
	
	/**
	 * La descripcion de la categoria
	 */
	private String descripcion;
	
	/**
	 * Los recursos agregados a la categoria.
	 */
	private ArbolBinarioAVLOrdenado<Recurso> recursos;

	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea una nueva categoria a partir de el nombre y la descripcion dados por parametro.
	 * @param nNombre El nombre de la categoria.
	 * @param nDescripcion La descripcion de la categoria.
	 */
	public Categoria(String nNombre, String nDescripcion){
		nombre = nNombre;
		descripcion = nDescripcion;
		recursos = new ArbolBinarioAVLOrdenado<Recurso>();
	}

	//-----------------------------------------
	// Metodos
	//-----------------------------------------
	/**
	 * Agrega el recurso dado por parametro al arbol de recursos
	 * @param nrec El recurso a agregar
	 * @return TRUE si se agrega, FALSE de lo contrario.
	 */
	public boolean agregarRecurso(Recurso nrec) {
		return recursos.agregar(nrec);
	}

	/**
	 * Elimina el recurso dado por parametro del arbol de recursos
	 * @param nrecurso El recurso a eliminar.
	 * @return TRUE si elimina, FALSE de lo contrario.
	 */
	public boolean eliminarRecurso(Recurso nrecurso) {
		return recursos.eliminar(nrecurso);
	}
	
	/**
	 * Comprime la categoria y sus recursos a partir del estandar del componente de servidor de persistencia.
	 * @return La informacion de la categoria organizada.
	 */
	public String comprimir(){
		String respuesta = "[nombre:" + nombre + "~"+ "descripcion:"+descripcion+"~"+"recursos{";
		if(recursos.darPeso() != 0){
			for (Recurso actual : recursos) {
				respuesta+= actual.getTipo()==Recurso.IMAGEN?actual.getImgUrl():actual.getDescripcion();
				respuesta+="~";
			}
			respuesta = respuesta.substring(0,respuesta.length()-1);
		}
		respuesta+="}]";
		return respuesta;
	}

	/**
	 * Cambia el nombre de la categoria por el dado de parametro
	 * @param nNombre el nuevo nombre
	 */
	public void setNombre(String nNombre){
		nombre = nNombre;
	}

	/**
	 * Cambia la descripcion de la categoria por la dada de parametro.
	 * @param nDeString la nueva descripcion
	 */
	public void setDescripcion(String nDeString){
		descripcion = nDeString;
	}

	/**
	 * Retorna el nombre de la categoria.
	 * @return el nombre de la categoria
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna la descripcion de la categoria
	 * @return la descripcion de la categoria
	 */
	public String getDescripcion(){
		return descripcion;
	}
	
	/**
	 * Retorna el arbol de recursos de la categoria
	 * @return el arbol de recursos
	 */
	public ArbolBinarioAVLOrdenado<Recurso> getRecursos(){
		return recursos;
	}

	/**
	 * Metodo que compara dos categorias
	 */
	public int compareTo(Categoria o) {
		return nombre.compareTo(o.nombre);
	}

	/**
	 * Metodo to string de la categoria.<br>
	 * Retorna el nombre de la categoria
	 */
	public String toString(){
		return nombre;
	}
}
