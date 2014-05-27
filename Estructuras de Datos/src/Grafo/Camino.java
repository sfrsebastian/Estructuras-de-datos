package Grafo;

import java.io.Serializable;
import java.util.Iterator;

import ListaEncadenada.ListaEncadenada;

public class Camino<K extends Comparable<K>, V extends Comparable<V>, A extends IInfoArco> implements Serializable {

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * Identificador de clase
	 */
	private static final long serialVersionUID = -1414255173674084284L;

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
	private Vertice<K, V, A> origen;
	
	/**
	 * El vertice de destino del camino
	 */
	private Vertice<K, V, A> destino;
	
	/**
	 * La lista de arcos que tiene el camino
	 */
	private ListaEncadenada<Arco<K, V, A>> arcos;
	
	/**
	 * El criterio del costo del camino
	 */
	private String criterio;
	
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	public Camino(Vertice<K,V,A> vertice, boolean esInicio, String nCriterio){
		if(!esInicio){
			destino = vertice;
			origen = null;
		}
		else{
			origen = vertice;
			destino = null;
		}
		costo = 0;
		arcos = new ListaEncadenada<Arco<K,V,A>>();
		criterio = nCriterio==null? null:nCriterio;
		longitud = 0;
	}
	
	public Camino(Vertice<K,V,A> vertice, boolean esInicio){
		if(!esInicio){
			destino = vertice;
			origen = null;
		}
		else{
			origen = vertice;
			destino = null;
		}
		costo = 0;
		arcos = new ListaEncadenada<Arco<K,V,A>>();
		criterio = null;
		longitud = 0;
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	/**
	 * Da la longitud del camino
	 * @return int La longitud del camino
	 */
	public int getLongitud(){
		return longitud;
	}
	
	/**
	 * Da el costo del camino
	 * @return float El costo del camino
	 */
	public float getCosto(){
		return costo;
	}
	
	/**
	 * Retorna el vertice de origen
	 * @return El vertice de origen.
	 */
	public V getOrigen(){
		return origen!=null?origen.getElemento():null;
	}
	
	/**
	 * Retorna el vertice de destino
	 * @return El vertice de destino
	 */
	public V getDestino(){
		return destino!=null?destino.getElemento():null;
	}
	
	/**
	 * Retorna el iterador de los vertices del camino
	 * @return Iterator iterador de los vertices del camino
	 */
	public Iterator<V> darVertices(){
		ListaEncadenada<V>lista = new ListaEncadenada<V>();
		Iterator<Arco<K,V,A>> it = arcos.iterator();
		while(it.hasNext()){
			Arco<K,V,A> actual = it.next();
			if(lista.buscar(actual.getOrigen().getElemento()) == null){
				lista.agregar(actual.getOrigen().getElemento());
			}
			lista.agregar(actual.getDestino().getElemento());
		}
		return lista.iterator();
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
		arcos.agregar(arco);
		if(criterio!=null)
			costo+=arco.getInfo().getCosto(criterio);
		else
			costo+=arco.getInfo().getCosto();
		
		longitud++;
		destino = arco.getDestino();
	}
	
	/**
	 * Agrega un arco al final del camino
	 * @param arco El arco que se quiere agregar al final
	 */
	public void agregarArcoComienzo(Arco<K, V, A> arco){
		arcos.agregarInicio(arco);
		if(criterio!=null)
			costo+=arco.getInfo().getCosto(criterio);
		else
			costo+=arco.getInfo().getCosto();
		
		longitud++;
		origen = arco.getOrigen();
	}
}
