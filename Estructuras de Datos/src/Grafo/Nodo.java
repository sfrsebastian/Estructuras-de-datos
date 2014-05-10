package Grafo;

import java.io.Serializable;

public abstract class Nodo<K,V,A> implements Serializable {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * El vertice que le pertenece al nodo
	 */
	private Vertice<K,V,A> vertice;
	
	/**
	 * El arco por el cual se llega al nodo
	 */
	Arco<K,V,A> arcoPredecesor;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo Nodo con el vertice y arcoPredecesorDado
	 * @param nVertice El vertice del nodo
	 * @param nArco El arco de llegada
	 */
	public Nodo(Vertice<K,V,A> nVertice, Arco<K,V,A> nArco ){
		vertice = nVertice;
		arcoPredecesor = nArco;
	}
	
	//--------------------
	//METODOS
	//--------------------
	/**
	 * Retorna el vertice del nodo
	 * @return El vertice del nodo
	 */
	public Vertice<K, V, A> getVertice() {
		return vertice;
	}
	
	/**
	 * Retorna el arco Predecesor del nodo
	 * @return El arco de llegada
	 */
	public Arco<K, V, A> getArcoPredecesor() {
		return arcoPredecesor;
	}
}
