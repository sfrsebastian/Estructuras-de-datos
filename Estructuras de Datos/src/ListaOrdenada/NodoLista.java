package ListaOrdenada;

import java.io.Serializable;

public class NodoLista<T> implements Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El elemento de tipo T que contiene el nodo
	 */
	private T elemento;
	
	/**
	 * El siguiente elemento de tipo T que contiene el nodo
	 */
	private NodoLista<T> siguiente;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	public NodoLista(T nElemento)
	{
		elemento = nElemento;
		siguiente = null;
	}

	/**
	 * Cambia el siguiente nodo del nodo actual al dado por parametro
	 * @param nodo El nodo a reemplazar
	 */
	public NodoLista<T> cambiarSiguiente(NodoLista<T> nodo) {
		siguiente = nodo;
		return nodo;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna el elemento que contiene el nodo
	 * @return
	 */
	public T darElemento() {
		return elemento;
	}
	
	/**
	 * Da el siguiente nodo correspondiente a la posicion actual
	 * @return
	 */
	public NodoLista<T> darSiguiente() {
		return siguiente;
	}
	/**
	 * Desconecta el siguiente nodo
	 * @return El nodo desconectado, null en caso contrario
	 */
	public NodoLista<T> desconectarSiguiente() {
		if(siguiente == null){
			return null;
		}
		else{
			NodoLista<T> sig = darSiguiente();
			siguiente = darSiguiente().darSiguiente();
			return sig;
		}
	}
}

