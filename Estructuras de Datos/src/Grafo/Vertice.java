package Grafo;

import Lista.Lista;
import ListaOrdenada.ListaOrdenada;

public class Vertice<K, V, A> implements Comparable<Vertice<K, V, A>>{

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * El representador unico del vertice
	 */
	private K idVertice;
	
	/**
	 * La informacion que tiene el vertice
	 */
	private V infoVertice;
	
	/**
	 * Indica si el nodo se encuentra marcado
	 */
	private boolean marcado;
	
	/**
	 * La lista de arcos sucesores del vertice
	 */
	private Lista<Arco<K,V,A>> sucesores;
	
	/**
	 * La lista de arcos predecesores del vertice
	 */
	private Lista<Arco<K, V, A>> predecesores;
	
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	/**
	 * Contruye un nuevo vertice con su id y con su informacion
	 * @param id El identificador del vertice
	 * @param info La informacion que tiene el vertice
	 */
	public Vertice(K id, V info){
		idVertice = id;
		infoVertice = info;
		marcado = false;
		sucesores = new ListaOrdenada<Arco<K,V,A>>();
		predecesores = new ListaOrdenada<Arco<K,V,A>>();
				
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	/**
	 * Marca el nodo
	 */
	public void marcar(){
		marcado = true;
	}
	
	/**
	 * Desmarca el nodo
	 */
	public void desmarcar(){
		marcado = false;
	}
	
	@Override
	public int compareTo(Vertice<K, V, A> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
