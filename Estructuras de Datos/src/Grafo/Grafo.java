package Grafo;

import java.util.Iterator;

import HashTable.TablaHashing;

public class Grafo<K extends Comparable<K>, V, A> {
	
	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * El numero de vertices que tiene el grafo
	 */
	private int numeroVertices;
	
	/**
	 * Los vertices que tiene el grafo
	 */
	private TablaHashing<K, Vertice<K, V, A>> vertices;
	
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	/**
	 * Construye un grafo dirigido 
	 * @param verticesPosibles La cantidad de vertices posibles
	 */
	public Grafo(int verticesPosibles){
		numeroVertices = verticesPosibles;
		vertices = new TablaHashing<>(7, 2);
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	public int darOrden(){
		//TODO terminar metodo, que hace?
		return 0;
	}
	
	/**
	 * De el vertice dado su id pasado por parametro
	 * @param idVert El id del vertice que se quiere buscar
	 * @return El vertice, NULL en caso contrario
	 */
	public Vertice<K, V, A> darVertice(K idVert){
		return vertices.buscar(idVert);
	}
	
	/**
	 * Retorna el arco que conecta dos vertices dados sus ids
	 * @param idOrigen El id del vertice de origen
	 * @param idDestino El id del vertice de destino
	 * @return El arco que los conecta, NULL en caso contrario
	 */
	public Arco<K, V, A> darArco(K idOrigen, K idDestino){
		Vertice<K, V, A> vorigen = vertices.buscar(idOrigen);
		Vertice<K, V, A> vdestino = vertices.buscar(idDestino);
		if(vorigen != null && vdestino != null){
			
		}else{
			return null;
		}
		return null;
	}
	
	/**
	 * Agrega un nuevo arco al grafo dado un vertice de origen y otro de destino
	 * @param idOrigen
	 * @param idDestino
	 * @param info
	 * @return
	 */
	public boolean agregarArco(K idOrigen, K idDestino, A info){
		Vertice<K, V, A> vorigen = vertices.buscar(idOrigen);
		Vertice<K, V, A> vdestino = vertices.buscar(idDestino);
		if(vorigen != null && vdestino != null){
			Arco<K, V, A> arco = new Arco<K, V, A>(vorigen,vdestino,info);
			vorigen.agregarArcoSucesor(arco);
			vdestino.agregarArcoPredecesor(arco); //TODO revisar orden del arco
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Agrega un nuevo vertice al grafo 
	 * @param idVertice El id del vertice que se va a agregar
	 * @param info La informacion que tiene el vertice
	 * @return TRUE si se agrego, FALSE en caso contrario
	 */
	public boolean agregarVertice(K idVertice, V info){
		Vertice<K, V, A> vertice = new Vertice<K, V, A>(idVertice, info);
		return vertices.agregar(idVertice, vertice);
		//TODO revisar implementacion
	}
	
	/**
	 * Retorna el camino simple mas corto entre dos vertices
	 * @param idOrigen La llave del vertice de origen
	 * @param idDestino La llave del vertice de destino
	 * @return Camino, el camino mas corto entre dos vertices
	 */
	public Camino<K, V, A> darCaminoSimpleMasCorto(K idOrigen, K idDestino){
		desmarcarVertices();
		Vertice<K, V, A> vert = vertices.buscar(idOrigen);
		if(vert != null)
			return vert.darCaminoSimpleMasCorto(idDestino);
		else
			return null;
	}
	
	/**
	 * Verifica si existe un camino simple entre un vertice de origen a un destino
	 * @param idOrigen La llave del vertice de origen
	 * @param idDestino La llave del vertice de destino
	 * @return TRUE si existe un camino, FALSE en caso contrario
	 */
	public boolean hayCaminoSimple(K idOrigen, K idDestino){
		desmarcarVertices();
		Vertice<K, V, A> vert = vertices.buscar(idOrigen);
		if(vert != null)
			return vert.hayCaminoSimpleA(idDestino);
		else
			return false;
	}
	
	/**
	 * Verifica si existe una cadena entre un vertice de origen y destino
	 * @param idOrigen La llave del vertice de origen
	 * @param idDestino La llave del vertice de destino
	 * @return TRUE si existe una cadena, FALSE en caso contrario
	 */
	public boolean hayCadena(K idOrigen, K idDestino){
		desmarcarVertices();
		Vertice<K, V, A> vert = vertices.buscar(idOrigen);
		if(vert != null)
			return vert.hayCadena(idDestino);
		else
			return false;
	}
	
	/**
	 * Desmarca todos los vertices del grafo 
	 */
	private void desmarcarVertices(){
		Iterator<Vertice<K, V, A>> i = vertices.iterator();
		while(i.hasNext()){
			((Vertice<K,V,A>)i.next()).desmarcar();
		}
	}
}
