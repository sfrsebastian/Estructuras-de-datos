package Lista;

import java.io.Serializable;

public class NodoLista<T> implements Serializable{

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
	 * Cambia el siguiente nodo del nodo actual al dado por parametro
	 * @param nodo El nodo a reemplazar
	 */
	public void cambiarSiguiente(NodoLista<T> nodo) {
		siguiente = nodo;
	}

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
	
	public NodoLista<T> darAnterior(){
		return anterior;
	}

	public void cambiarAnterior(NodoLista<T> nodo) {
		anterior = nodo;
	}
}

