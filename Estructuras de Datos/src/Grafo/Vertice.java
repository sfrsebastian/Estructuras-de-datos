package Grafo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Cola.Cola;
import Lista.Lista;
import ListaEncadenada.ListaEncadenada;
import ListaOrdenada.ListaOrdenada;

public class Vertice<K extends Comparable<K>, V, A extends IInfoArco> implements Comparable<Vertice<K, V, A>>{

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
	 *  La lista de arcos sucesores del vertice
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
	
	/**
	 * Retorna el id del vertice
	 * @return K el id del vertices
	 */
	public K getId(){
		return idVertice;
	}

	/**
	 * Retorna el elemento del vertice
	 * @return
	 */
	public V getElemento(){
		return elemento;
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
	public boolean getMarca(){
		return marcado;
	}
	
	/**
	 * Retorna un iterador de los sucedores del vertice.
	 * @return Los sucesores del vertice.
	 */
	public Iterator<Arco<K,V,A>> getSucesores(){
		return sucesores.iterator();
	}
	
	/**
	 * Retorna un iterador de los predecesores del vertice.
	 * @return Los predecesores del vertice.
	 */
	public Iterator<Arco<K,V,A>> getPredecesores(){
		return predecesores.iterator();
	}
	
	/**
	 * Verifica si existe un arco sucesor a un vertice de destino
	 * @param idDestino
	 * @return
	 */
	public boolean tengoSucesorA(K idDestino){
		Iterator<Arco<K, V, A>> i = sucesores.iterator();
		while(i.hasNext()){
			Arco<K, V, A> actual = i.next();
			if(actual.getDestino().getId().compareTo(idDestino) == 0)
				return true;
		}		
		return false;
	}

	/**
	 * Indica si el vertice tiene un arco con origen en el nodo con el codigo dado.
	 * @param idOrigen El codigo del vertice de origen
	 * @return TRUE si contiene el predecesor, FALSE de lo contrario.
	 */
	public boolean tengoPredecesorDesde(K idOrigen){
		boolean respuesta = false;
		Iterator<Arco<K,V,A>> it = predecesores.iterator();
		while(it.hasNext() && !respuesta){
			Arco<K,V,A> actual = it.next();
			if(actual.getOrigen().getId().equals(idOrigen)){
				respuesta = true;
			}
		}
		return respuesta;
	}
	
	/**
	 * Verifica si existe un solo arco entre este vertice y el buscado
	 * @param idDestino El vertice de destino que se quiere verificar
	 * @return TRUE si son adyacentes, FALSE en caso contrario
	 */
	public boolean somosAdyacentes(K idDestino){
		boolean loSomos = false;
		
		Iterator<Arco<K, V, A>> suceIterator = sucesores.iterator();
		while(suceIterator.hasNext()){
			Arco<K, V, A> actual = suceIterator.next();
			if(actual.getDestino().getId().compareTo(idDestino) == 0)
				loSomos = true;
		}
		if(!loSomos){
			Iterator<Arco<K, V, A>> suIterator = predecesores.iterator();
			while(suceIterator.hasNext()){
				Arco<K, V, A> actual = suIterator.next();
				if(actual.getOrigen().getId().compareTo(idDestino) == 0)
					loSomos = true;
			}
		}
		return loSomos;
	}

	/**
	 * Indica si el vertice tiene un arco con destino en el vertice con codigo dado.
	 * @param idDestino El codigo del vertice de origen
	 * @return El arco sucesor, null de lo contrario.
	 */
	public Arco<K,V,A>darArcoSucesorHacia(K idDestino){
		Arco<K,V,A> respuesta = null;
		Iterator<Arco<K,V,A>> it = sucesores.iterator();
		while(it.hasNext() && respuesta!=null){
			Arco<K,V,A> actual = it.next();
			if(actual.getDestino().getId().equals(idDestino)){
				respuesta = actual;
			}
		}
		return respuesta;
	}

	/**
	 * Retorna el arco que tiene como origen el dado por parametro.
	 * @param idOrigen El codigo del vertice origen
	 * @return El arco con el origen.
	 */
	public Arco<K, V, A> darArcoPredecesorDesde(K idOrigen){
		Arco<K,V,A> respuesta = null;
		Iterator<Arco<K,V,A>> it = predecesores.iterator();
		while(it.hasNext() && respuesta == null){
			Arco<K,V,A> actual = it.next();
			if(actual.getOrigen().getId().equals(idOrigen)){
				respuesta = actual;
			}
		}
		return respuesta;
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

	/**
	 * Agrega un arco sucesor al vertice
	 * @param arcoSucesor El arco sucesor
	 */
	public void agregarArcoSucesor(Arco<K, V, A> arcoSucesor){
		sucesores.agregar(arcoSucesor);
	}

	/**
	 * Agrega el arco dado por parametro a la lista de predecesores
	 * @param arco El arco a agregar
	 */
	public void agregarArcoPredecesor(Arco<K,V,A> arco){
		predecesores.agregar(arco);
	}
	
	/**
	 * Elimina un arco sucesor del grafo
	 * @param idDestino El arco a eliminar
	 */
	public Arco<K,V,A> eliminarArcoSucesor(K idDestino){
		Arco<K,V,A> respuesta = null;
		Iterator<Arco<K,V,A>> it = sucesores.iterator();
		while(it.hasNext() && respuesta!=null){
			Arco<K,V,A> actual = it.next();
			if(actual.getDestino().getId().equals(idDestino)){
				respuesta = predecesores.eliminar(actual);
			}
		}
		return respuesta;
	}

	/**
	 * Elimina el arco con origen en el vertice dado por parametro
	 * @param idOrigen El vertice de origen
	 * @return El arco eliminado
	 */
	public Arco<K,V,A> eliminarArcoPredecesor(K idOrigen){
		Arco<K,V,A> respuesta = null;
		Iterator<Arco<K,V,A>> it = predecesores.iterator();
		while(it.hasNext() && respuesta!=null){
			Arco<K,V,A> actual = it.next();
			if(actual.getOrigen().getId().equals(idOrigen)){
				respuesta = predecesores.eliminar(actual);
			}
		}
		return respuesta;
	}

	/**
	 * Verifica si existe un camino simple desde un vertice de salida
	 * @param idDest El id vertice al que se quiere llegar
	 * @return TRUE si hay camino, FALSE en caso contrario
	 */
	public boolean hayCaminoSimpleA(K idDest){
		boolean encontro = false;
		if (!marcado){
			marcar( );
			if (this.getId().compareTo(idDest) == 0) {
				encontro = true; 
			}
			else{
				Iterator<Arco<K,V,A>> itsucesores = getSucesores(); 
				while ( itsucesores.hasNext() && !encontro){
					Arco<K,V,A> arco = itsucesores.next();
					Vertice<K,V,A> vtceSucesor = arco.getDestino(); 
					encontro = vtceSucesor.hayCaminoSimpleA( idDest );
				}
			}
		}
		return encontro;
	}

	/**
	 * Indica si el vertice pertences a un ciclo simple
	 * @return TRUE si pertenece, FALSE de lo contrario.
	 */
	public boolean pertenezcoACicloSimple(){
		boolean respuesta = false;
		Iterator<Arco<K,V,A>> it = sucesores.iterator(); 
		while (it.hasNext() && !respuesta){
			Arco<K,V,A> actual = it.next();
			Vertice<K,V,A> destino = actual.getDestino();
			if (destino.hayCaminoSimpleA(idVertice)){
				respuesta = true;
			}
		}
		return respuesta;
	}

	/**
	 * Verifica si existe una cadena entre este vertice y otro de destino
	 * @param destino El vertice de destino al que se quiere llegar
	 * @return TRUE si es cierto, FALSE en caso contrario
	 */
	public boolean hayCadenaA(K destino){
		boolean hayCadena = false;
		if (!marcado){
			if(idVertice.compareTo(destino)==0){
				hayCadena = true;
			}
			else{
				Iterator<Arco<K,V,A>> itsucesores = sucesores.iterator(); 
				while ( itsucesores.hasNext() && !hayCadena){
					marcar();
					Arco<K,V,A> arco = itsucesores.next();
					Vertice<K,V,A> vtceSucesor = arco.getDestino(); 
					hayCadena = vtceSucesor.hayCadenaA(destino);
					desmarcar();
				}
				if(!hayCadena){
					Iterator<Arco<K,V,A>> itpredecesores = predecesores.iterator(); 
					while ( itpredecesores.hasNext() && !hayCadena){
						marcar();
						Arco<K,V,A> arco = itpredecesores.next();
						Vertice<K,V,A> vtcePredecesor = arco.getDestino(); 
						hayCadena = vtcePredecesor.hayCadenaA(destino);
						desmarcar();
					}
				}
			}
		}
		return hayCadena;
	}

	/**
	 * Retorna un camino simple al vertice con codigo dado
	 * @param idDestino El codigo del vertice de destino
	 * @return El camino entre this y el vertice destino
	 */
	public Camino<K,V,A> darCaminoSimpleA(K idDestino){
		Camino<K,V,A> respuesta = null;
		if(!marcado){
			marcar();
			if(idVertice.compareTo(idDestino)==0){
				respuesta = new Camino<K,V,A>(this,false);
			}
			else{
				Arco<K,V,A> arco = null; 
				Iterator<Arco<K,V,A>> itsucesores = sucesores.iterator(); 
				while (itsucesores.hasNext() && respuesta == null){
					arco = itsucesores.next( );
					Vertice<K,V,A> vtceSucesor = arco.getDestino(); 
					respuesta = vtceSucesor.darCaminoSimpleA(idDestino);
				}
				if(respuesta!=null){
					respuesta.agregarArcoComienzo(arco);
				}
			} 
		}
		return respuesta;
	}

	/**
	 * Retorna el camino simple mas barato a un vertice de destino
	 * @param idDest la llave del vertice de destino
	 * @return Camino, el camino mas corto, NULL en caso contrario
	 */
	public Camino<K,V,A> darCaminoSimpleMasCortoA(K idDest){
		Camino<K,V,A> masCorto = null; 
		if(!marcado){
			marcar( );
			if(this.getId().compareTo(idDest) == 0){ 
				masCorto = new Camino<K,V,A>(this, false); 
			}
			else{
				Iterator<Arco<K,V,A>> itsucesores = getSucesores(); 
				while (itsucesores.hasNext()){
					Arco<K,V,A> arco = itsucesores.next();
					Vertice<K,V,A> vtceSucesor = arco.getDestino();
					Camino<K,V,A> camino = vtceSucesor.darCaminoSimpleMasCortoA(idDest);
					if (camino != null){ 
						camino.agregarArcoComienzo(arco); 
						if(masCorto == null){ 
							masCorto = camino; }
						else if ( masCorto.getLongitud() > camino.getLongitud() ) { 
							masCorto = camino; 
						}
					} 
				}
			}
			desmarcar( );
		}
		return masCorto;
	}

	/**
	 * Retorna el camino simple de menor costo entre los dos vertices dados.
	 * @param idDestino El vertice de destino
	 * @return El camino de menor costo
	 */
	public Camino<K,V,A> darCaminoSimpleMasBaratoA(K idDestino){
		Camino<K,V,A> respuesta = null;
		if(!marcado){
			marcar();
			if(idVertice.compareTo(idDestino)==0){
				respuesta = new Camino<K,V,A>(this,false);
			}
			else{
				Iterator<Arco<K,V,A>> itsucesores = sucesores.iterator(); 
				while (itsucesores.hasNext()){
					Arco<K,V,A> arco = itsucesores.next( );
					Vertice<K,V,A> vtceSucesor = arco.getDestino(); 
					Camino<K,V,A> camino = vtceSucesor.darCaminoSimpleMasBaratoA(idDestino);
					if(camino!=null){
						camino.agregarArcoComienzo(arco);
						if (camino != null){ 
							camino.agregarArcoComienzo(arco); 
							if(respuesta == null){ 
								respuesta = camino; }
							else if ( respuesta.getCosto() > camino.getCosto() ) { 
								respuesta = camino; 
							}
						} 
					}
				}
			} 
			desmarcar();
		}
		return respuesta;
	}

	/**
	 * Realiza la busqueda por profundidad del vertice 
	 * @param rta La lista que almacena la respuesta
	 * @param profundidad La profundidad del recorrido 
	 * @param arco El arco predecesor
	 */
	public void recorridoXProfundidad(Lista<NodoProfundidad<K, V, A>> rta, int profundidad, Arco<K, V, A> arco){
		if(!marcado){
			marcar();
			NodoProfundidad<K, V, A> nuevo = new NodoProfundidad<K,V,A>(this, arco, profundidad);
			rta.agregar(nuevo);
			Iterator<Arco<K, V, A>> as = sucesores.iterator();
			while(as.hasNext()){
				Arco<K, V, A> actual = as.next();
				Vertice<K, V, A> vec = actual.getDestino();
				vec.recorridoXProfundidad(rta, profundidad + 1, arco);
			}
		}
	}

	/**
	 * Recorre el nodo por niveles
	 * @return Iterador con los nodos del vertice organizados por nivel
	 */
	public Iterator<NodoNivel<K,V,A>> recorridoXNiveles(){
		ListaEncadenada<NodoNivel<K,V,A>> lista = new ListaEncadenada<NodoNivel<K,V,A>>();
		Cola<NodoNivel<K,V,A>> cola = new Cola<NodoNivel<K,V,A>>();
		NodoNivel<K,V,A> nodo = new NodoNivel<K,V,A>(this,null,1);
		cola.agregar(nodo);
		try{
			while(true){
				NodoNivel<K,V,A> actual = cola.dar();
				Vertice<K,V,A> v = actual.getVertice();
				Iterator<Arco<K,V,A>> arcos = v.sucesores.iterator();
				while(arcos.hasNext()){
					Arco<K,V,A> arco = arcos.next();
					Vertice<K,V,A> destino = arco.getDestino();
					NodoNivel<K,V,A> nuevo = new NodoNivel<K,V,A>(destino,arco,actual.getNivel()+1); 
					cola.agregar(nuevo);
				}
				lista.agregar(actual);
			}
		}
		catch(NoSuchElementException e){
			return lista.iterator();
		}
	}

	@Override
	public int compareTo(Vertice<K, V, A> o) {
		if(idVertice.compareTo(o.getId())>0){
			return 1;
		}
		else if(idVertice.compareTo(o.getId())<0){
			return -1;
		}
		else{
			return 0;
		}
	}

	public void Dijkstra(ListaOrdenada<NodoDijkstra<K, V, A>> frenteExploracion, ListaOrdenada<NodoDijkstra<K, V, A>> rta) {
		frenteExploracion.agregar(new NodoDijkstra<K,V,A>(this, null, 0)); 

		NodoDijkstra<K, V, A> nodo;
		while((nodo = frenteExploracion.darPrimero()) != null){
			if(nodo.getMarca()){
				Vertice<K, V, A> vert = nodo.getVertice();
				Iterator<Arco<K, V, A>> suc = vert.getSucesores();
				while(suc.hasNext()){
					Arco<K, V, A> actual = suc.next();
					Vertice<K, V, A> v = actual.getDestino();
					NodoDijkstra<K, V, A> nod = new NodoDijkstra<K, V, A>(v, actual,nodo.getCosto() + actual.getInfo().getCosto());
					if(frenteExploracion.buscar(nod) == null){	
						frenteExploracion.agregar(nod);
					}else{
						NodoDijkstra<K, V, A> buscado = rta.buscar(nod);
						if(buscado.compareTo(nod) < 0){
							frenteExploracion.eliminar(buscado);
							frenteExploracion.agregar(nod);
						}
					}
				}
				rta.agregar(nodo);
				nodo.marcar();
			}
		}

	}
}
