package Grafo;

import java.util.Iterator;

import HashTable.TablaHashing;

public class Grafo<K, V, A> {
	
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
		Vertice<K,V,A> origen = vertices.buscar(idOrigen);
		Arco<K,V,A> respuesta = null;
		if(origen!=null){
			respuesta = origen.darSucesorHacia(idDestino);
		}
		return respuesta;
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
	 * Elimina el vertice con el identificador dado de parametro<br>
	 * Los arcos entre los dos vertices son eliminados
	 * @param idVertice El identificador unico del vertice
	 * @return El vertice eliminado, Null de lo contrario
	 */
	public Vertice<K,V,A> eliminarVertice(K idVertice){
		Vertice<K,V,A> eliminado = null;
		eliminado = vertices.eliminar(idVertice);
		if(eliminado!=null){
			Iterator<Vertice<K,V,A>> it = vertices.iterator();
			while(it.hasNext()){
				Vertice<K,V,A> actual = it.next();
				eliminarArco(eliminado.getId(),actual.getId());
				eliminarArco(actual.getId(),eliminado.getId());
			}
			numeroVertices--;
		}
		return eliminado;
	}
	
	/**
	 * Elimina el arco con origen y destino dados
	 * @param idOrigen El vertice de origen
	 * @param idDestino El vertice de destino
	 * @return El arco eliminado, null de lo contrario
	 */
	public Arco<K,V,A> eliminarArco(K idOrigen,K idDestino){
		Vertice<K,V,A> origen = vertices.buscar(idOrigen);
		Arco<K,V,A>respuesta = null;
		if(origen!=null){
			respuesta = origen.eliminarArcoSucesor(idDestino);
		}
		return respuesta;
	}
	
	/**
	 * Indica si el vertice dado pertenece a un ciclo simple.
	 * @param idOrigen El vertice a consultar
	 * @return TRUE si pertenece, FALSE de lo contrario
	 */
	public boolean hayCicloSimpleCon(K idOrigen){
		Vertice<K,V,A> origen = vertices.buscar(idOrigen);
		if(origen != null){
			return origen.pertenezcoACicloSimple();
		}
		return false;
	}
	
	/**
	 * Retorna un camino simple entre los dos vertices dados
	 * @param idOrigen El codigo del vertice origen
	 * @param idDestino El codigo del vertice destino
	 * @return El camino entre los dos vertices, null de lo contrario.
	 */
	public Camino<K,V,A> darCaminoSimple(K idOrigen, K idDestino){
		Vertice<K,V,A> origen = vertices.buscar(idOrigen);
		Camino<K,V,A> respuesta = null;
		if(origen!=null){
			respuesta = origen.darCaminoSimpleA(idDestino);
		}
		return respuesta;
	}
	
	/**
	 * Retorna el camino simple de menor costo entre los dos vertices dados
	 * @param idOrigen El codigo del vertice origen
	 * @param idDestino El codigo del vertice destino
	 * @return El camino entre los dos vertices, null de lo contrario.
	 */
	public Camino<K,V,A> darCaminoSimpleMasBaratoA(K idOrigen, K idDestino){
		Vertice<K,V,A> origen = vertices.buscar(idOrigen);
		Camino<K,V,A> respuesta = null;
		if(origen!=null){
			respuesta = origen.darCaminoSimpleMasBaratoA(idDestino);
		}
		return respuesta;
	}	
	
	/**
	 * Retorna todos los vertices del grafo
	 * @return
	 */
	Iterator<Vertice<K,V,A>> recorridoPlano(){
		return vertices.iterator();
	}
	
	/**
	 * Recorre el grafo por niveles, partiendo del vertice dado.
	 * @param idOrigen El codigo del vertice de partida
	 * @return Iterador de los nodos del grafo.
	 */
	Iterator<NodoNivel<K,V,A>> recorridoXNiveles(K idOrigen){
		desmarcarVertices();
		Vertice<K,V,A> origen = vertices.buscar(idOrigen);
		if(origen!=null){
			return origen.recorridoXNiveles();
		}
		return null;
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
