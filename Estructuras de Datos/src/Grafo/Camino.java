package Grafo;

import java.io.Serializable;
import java.util.Iterator;

import Lista.Lista;

public class Camino<K extends Comparable<K>, V, A> implements Serializable {

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * El costo del camino
	 */
	private float costo;
	
	/**
	 * La longitud del camino
	 */
	private int longitud;
	
	/**
	 * El vertice de inicio del camino
	 */
	private Vertice<K, V, A> inicio;
	
	/**
	 * El vertice de destino del camino
	 */
	private Vertice<K, V, A> destino;
	
	/**
	 * La lista de arcos que tiene el camino
	 */
	private Lista<Arco<K, V, A>> arcos;
	
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	public Camino(Vertice<K,V,A> vtce, boolean es_vtc_inicio){
		//TODO 
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	/**
	 * Da la longitud del camino
	 * @return int La longitud del camino
	 */
	public int darLongitud(){
		return longitud;
	}
	
	/**
	 * Da el costo del camino
	 * @return float El costo del camino
	 */
	public float darCosto(){
		return costo;
	}
	
	/**
	 * Retorna el iterador de los vertices del camino
	 * @return Iterator iterador de los vertices del camino
	 */
	public Iterator<Vertice<K, V, A>> darVertices(){
		//TODO 
		return null;
	}
	
	/**
	 * Retorna iterador de los arcos del camino
	 * @return Iterator iterador de los arcos del camino
	 */
	public Iterator<Arco<K, V, A>> darArcos(){ 
		return arcos.iterator();
	}
	
	/**
	 * Agrega un arco al final del camino
	 * @param arco El arco que se quiere agregar
	 */
	public void agregarArcoFinal(Arco<K, V, A> arco){
		//TODO 
	}
	
	/**
	 * Agrega un arco al final del camino
	 * @param arco El arco que se quiere agregar al final
	 */
	public void agregarArcoComienzo(Arco<K, V, A> arco){
		//TODO
	}
}
