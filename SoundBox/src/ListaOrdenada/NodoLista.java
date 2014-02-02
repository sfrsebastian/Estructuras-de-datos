package ListaOrdenada;

public class NodoLista<T> {

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
	
	/**
	 * El anterior elemento de tipo T que contiene el nodo
	 */
	private NodoLista<T> anterior;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	public NodoLista(T nElemento)
	{
		elemento = nElemento;
		siguiente = null;
		anterior = null;
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
	 * Da el anterior nodo correspondiente a la posicion actual
	 * @return
	 */
	public NodoLista<T> darAnterior(){
		return anterior;
	}
	
	/**
	 * Cambia el siguiente nodo del nodo actual al dado por parametro
	 * @param nodo El nodo a reemplazar
	 */
	public NodoLista<T> cambiarSiguiente(NodoLista<T> nodo) {
		siguiente = nodo;
		return nodo;
	}
	
	/**
	 * Cambia el anterior nodo del nodo actual al dado por parametro
	 * @param nodo El nodo a reemplazar
	 */
	public NodoLista<T> cambiarAnterior(NodoLista<T> nodo) {
		anterior = nodo;
		return nodo;
	}
}

