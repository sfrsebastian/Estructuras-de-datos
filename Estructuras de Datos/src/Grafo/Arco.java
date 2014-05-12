package Grafo;

import java.io.Serializable;

public class Arco<K extends Comparable<K>, V, A extends IInfoArco> implements Comparable<Arco<K, V, A>>, Serializable {

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * La informacion del arco
	 */
	private A infoArco;
	
	/**
	 * El vertice de origen del arco
	 */
	private Vertice<K, V, A> origen;
	
	/**
	 * El vertice de destino del arco
	 */
	private Vertice<K, V, A> destino;
	
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	/**
	 * Construye un nuevo arco con un vertice inicial y otro de de destino
	 * @param vOrigen El vertice de origen del arco
	 * @param vDestino El vertice de destino del arco
	 * @param info La informacion del arco
	 */
	public Arco(Vertice<K,V,A> vOrigen, Vertice<K,V,A> vDestino, A info){
		origen = vOrigen;
		destino = vDestino;
		infoArco = info;
	}
	//---------------------------------------
	// Metodos
	//---------------------------------------
	/**
	 * Retorna el vertice de destino del arco
	 * @return Vertice el vertice de destino del arco
	 */
	public Vertice<K, V, A> getDestino() {
		return destino;
	}
	
	/**
	 * Retorna el vertice de origen del arco
	 * @return Vertice de origen del arco
	 */
	public Vertice<K, V, A> getOrigen(){
		return origen;
	}
	
	/**
	 * Retorna la informacion del arco
	 * @return La informacion del arco
	 */
	public A getInfo(){
		return infoArco;
	}
	
	@Override
	public int compareTo(Arco<K, V, A> o) {
		if(infoArco.compareTo(o.getInfo())>0){
			return 1;
		}
		else if(infoArco.compareTo(o.getInfo())<0){
			return -1;
		}
		else{
			return 0;
		}
	}
}
