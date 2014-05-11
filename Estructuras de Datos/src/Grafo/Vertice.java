package Grafo;

import java.util.Iterator;

import Lista.Lista;
import ListaEncadenada.ListaEncadenada;

public class Vertice<K, V, A> implements Comparable<Vertice<K, V, A>>{

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * El representador unico del vertice
	 */
	private K idVertice;
	
	/**
	 * El elemento del vertice
	 */
	private V elemento;
	
	/**
	 * La informacion del vertice
	 */
	private A infoVertice;
	
	/**
	 * Indica si el nodo se encuentra marcado
	 */
	private boolean marcado;
	
	/**
	 * Los arcos sucesores del vertice
	 */
	private Lista<Arco<K,V,A>> sucesores;
	
	/**
	 * Los arcos sucesores del vertice
	 */
	private Lista<Arco<K,V,A>> predecesores;
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	/**
	 * Contruye un nuevo vertice con su id y con su informacion
	 * @param id El identificador del vertice
	 * @param info La informacion que tiene el vertice
	 */
	public Vertice(K id, V nElemento, A info){
		idVertice = id;
		elemento = nElemento;
		infoVertice = info;
		sucesores = new ListaEncadenada<Arco<K,V,A>>();
		predecesores = new ListaEncadenada<Arco<K,V,A>>();
		marcado = false;
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	/**
	 * Retorna la informacion del vertice.
	 * @return La informacion del vertice
	 */
	public A getInfo(){
		return infoVertice;
	}
	
	/**
	 * Indica si el vertice esta marcado o no.
	 * @return TRUE si esta marcado, FALSE de lo contrario.
	 */
	public boolean darMarca(){
		return marcado;
	}
	
	/**
	 * Retorna un iterador de los sucedores del vertice.
	 * @return Los sucesores del vertice.
	 */
	public Iterator<Arco<K,V,A>> darSucesores(){
		return sucesores.iterator();
	}
	
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
