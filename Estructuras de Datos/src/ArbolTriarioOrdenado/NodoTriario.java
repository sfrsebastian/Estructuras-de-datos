package ArbolTriarioOrdenado;

import java.io.Serializable;
import java.util.Iterator;

import Lista.ILista;

public class NodoTriario<T extends Comparable<T>> implements Serializable {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Serial identificador de la clase.
	 */
	private static final long serialVersionUID = -2765440002159464767L;

	/**
	 * El nodo izquierdo que contiene los elementos menores a los elementos del nodo.
	 */
	private NodoTriario<T> izquierda;
	
	/**
	 * El nodo de la mitad que contiene los elementos entre los elementos del nodo.
	 */
	private NodoTriario<T> mitad;
	
	/**
	 * El nodo de la mitad que contiene los elementos mayores a los elementos del nodo.
	 */
	private NodoTriario<T> derecha;
	
	/**
	 * El elemento menor del nodo
	 */
	private T elementoIzquierdo;
	
	/**
	 * El elemento mayor del nodo
	 */
	private T elementoDerecho;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo nodo triario sin elementos
	 * @pos Los elementos se inicializan en null.
	 * @pos Los nodos izquierdo,mitad y derecho se inicializan en null.
	 */
	public NodoTriario(){
		izquierda = null;
		mitad = null;
		derecha = null;
		elementoIzquierdo = null;
		elementoDerecho = null;
	}
	//--------------------
	//METODOS
	//--------------------
	/**
	 * Agrega el elemento dado por parametro.
	 * @param elemento El elemento a agregar.
	 * @return TRUE si se agrego correctamente, false de lo contario
	 */
	public boolean agregar (T elemento){
		
	}
	
	/**
	 * Busca el elemento dado por parametro
	 * @param elemento El elemento a buscar
	 * @return El elemento encontrado, null de lo contrario.
	 */
	public T buscar(T elemento){
		
	}
	
	/**
	 * Elimina el elemento dado por parametro
	 * @param elemento El elemento a eliminar
	 * @return TRUE si se elimina correctamente, FALSE de lo contrario
	 */
	public boolean eliminar(T elemento){
	
	}
	
	/**
	 * Recorre los elementos en orden ascendente.
	 * @param lista La lista donde agregar los elementos
	 */
	public void recorrerInorden(ILista<T> lista) {
		
	}
	
	/**
	 * Retorna la altura del nodo.
	 * @return La altura del nodo.
	 */
	public int darAltura() {
		
	}
}
