package Grafo;

import java.io.Serializable;
import java.util.Iterator;
import HashTable.TablaHashing;
import Lista.Lista;
import ListaEncadenada.ListaEncadenada;

public class Grafo<K extends Comparable<K>, V, A extends IInfoArco> implements Serializable {
	
	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * Identificador de la clase
	 */
	private static final long serialVersionUID = -7805960351874218169L;

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
		vertices = new TablaHashing<>(verticesPosibles, 2);
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	/**
	 * La cantidad de vertices que tiene el grafo
	 * @return El total de vertices
	 */
	public int darOrden(){
		return vertices.darLongitud();
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
			respuesta = origen.darArcoSucesorHacia(idDestino);
		}
		return respuesta;
	}
	
	/**
	 * Agrega un nuevo vertice al grafo 
	 * @param idVertice El id del vertice que se va a agregar
	 * @param info La informacion que tiene el vertice
	 * @return TRUE si se agrego, FALSE en caso contrario
	 */
	public boolean agregarVertice(K idVertice, V elemento, A info){
		Vertice<K, V, A> vertice = new Vertice<K, V, A>(idVertice,elemento, info);
		return vertices.agregar(idVertice, vertice);
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
			vdestino.agregarArcoPredecesor(arco);
			return true;
		}
		else{
			return false;
		}
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
	 * Verifica si existe una cadena entre un vertice de origen y destino
	 * @param idOrigen La llave del vertice de origen
	 * @param idDestino La llave del vertice de destino
	 * @return TRUE si existe una cadena, FALSE en caso contrario
	 */
	public boolean hayCadena(K idOrigen, K idDestino){
		desmarcarVertices();
		Vertice<K, V, A> vert = vertices.buscar(idOrigen);
		if(vert != null)
			return vert.hayCadenaA(idDestino);
		else
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

	/** Retorna el camino simple mas corto entre dos vertices
	 * @param idOrigen La llave del vertice de origen
	 * @param idDestino La llave del vertice de destino
	 * @return Camino, el camino mas corto entre dos vertices
	 */
	public Camino<K, V, A> darCaminoSimpleMasCorto(K idOrigen, K idDestino){
		desmarcarVertices();
		Vertice<K, V, A> vert = vertices.buscar(idOrigen);
		if(vert != null)
			return vert.darCaminoSimpleMasCortoA(idDestino);
		else
			return null;
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
	 * Desmarca todos los vertices del grafo 
	 */
	private void desmarcarVertices(){
		Iterator<Vertice<K, V, A>> i = vertices.iterator();
		while(i.hasNext()){
			i.next().desmarcar();
		}
	}

	/**
	 * Retorna todos los vertices del grafo
	 * @return
	 */
	Iterator<Vertice<K,V,A>> recorridoPlano(){
		return vertices.iterator();
	}
	
	/**
	 * Retorna el iterador con la busqueda por profunidad del grafo
	 * @param idOrig El Vertice de origen de la busqueda
	 * @return Iterator El iterador de la busqueda
	 */
	public Iterator<NodoProfundidad<K, V, A>> recorridoXProfundidad(K idOrig){
		Lista<NodoProfundidad<K,V,A>> rta = new ListaEncadenada<NodoProfundidad<K,V,A>>();
		Vertice<K,V,A> origen = darVertice(idOrig);
		if(origen != null){
			desmarcarVertices();
			origen.recorridoXProfundidad(rta,0,null);
		}
		return rta.iterator();
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
}
