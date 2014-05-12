package Grafo;

import java.util.Iterator;

import Lista.Lista;
import ListaOrdenada.ListaOrdenada;

public class Vertice<K extends Comparable<K>, V, A> implements Comparable<Vertice<K, V, A>>{

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
	
	/**
	 * Retorna el id del vertice
	 * @return K el id del vertices
	 */
	public K darId(){
		return idVertice;
	}
	
	/**
	 * Verifica si existe un arco sucesor a un vertice de destino
	 * @param idDestino
	 * @return
	 */
	public boolean tengoSucesorA(K idDestino){
		//TODO revisar
		Iterator<Arco<K, V, A>> i = sucesores.iterator();
		while(i.hasNext()){
			Arco<K, V, A> actual = i.next();
			if(actual.darDestino().darId().compareTo(idDestino) == 0)
				return true;
		}		
		return false;
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
			if(actual.darDestino().darId().compareTo(idDestino) == 0)
				loSomos = true;
		}
		Iterator<Arco<K, V, A>> suIterator = predecesores.iterator();
		while(suceIterator.hasNext()){
			Arco<K, V, A> actual = suIterator.next();
			if(actual.darOrigen().darId().compareTo(idDestino) == 0)
				loSomos = true;
		}
		
		return loSomos;
	}
	
	public Arco<K, V, A> darArcoPredecesorDesde(K idOrigen){
		return null;	
	}

	/**
	 * Agrega un arco sucesor al vertice
	 * @param arcoSucesor El arco sucesor
	 */
	public void agregarArcoSucesor(Arco<K, V, A> arcoSucesor){
		sucesores.agregar(arcoSucesor);
	}
	
	/**
	 * Elimina un arco sucesor del grafo
	 * @param arcoSucesor El arco a eliminar
	 */
	public void eliminarArcoSucesor(Arco<K, V, A> arcoSucesor){
		sucesores.eliminar(arcoSucesor);
	}
	
	/**
	 * Agrega un arco predecesor al vertice
	 * @param arcoPredecesor El arco predecesorx
	 */
	public void agregarArcoPredecesor(Arco<K, V, A> arcoPredecesor){
		predecesores.agregar(arcoPredecesor);
	}
	
	public Arco<K, V, A> darArcoSucesorHacia(K destino){
		return null;
	}

	/**
	 * Verifica si existe un camino simple desde un vertice de salida
	 * @param idDest El id vertice al que se quiere llegar
	 * @return TRUE si hay camino, FALSE en caso contrario
	 */
	public boolean hayCaminoSimpleA(K idDest){
		boolean encontro = false;
		if ( !marcado ){
			marcar( );
			if ( this.darId().compareTo( idDest ) == 0) {
				encontro = true; 
			}
		}else{
			Iterator<Arco<K,V,A>> itsucesores = darSucesores(); 
			while ( itsucesores.hasNext() && !encontro){
				Arco<K,V,A> arco = itsucesores.next();
				Vertice<K,V,A> vtceSucesor = arco.darDestino(); 
				encontro = vtceSucesor.hayCaminoSimpleA( idDest );
			}
		}
		return encontro;
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
				Vertice<K, V, A> vec = actual.darDestino();
				vec.recorridoXProfundidad(rta, profundidad + 1, arco);
			}
		}
	}
	
	/**
	 * Verifica si existe una cadena entre este vertice y otro de destino
	 * @param destino El vertice de destino al que se quiere llegar
	 * @return TRUE si es cierto, FALSE en caso contrario
	 */
	public boolean hayCadena(K destino){
		boolean hayCadena = false;
		if ( !marcado ){
			marcar( );
			Iterator<Arco<K,V,A>> itsucesores = darSucesores(); 
			while ( itsucesores.hasNext()){
				Arco<K,V,A> arco = itsucesores.next();
				Vertice<K,V,A> vtceSucesor = arco.darDestino(); 
				hayCadena = vtceSucesor.hayCadena( destino );
			}
		}else{
			hayCadena = true;
		}
		return hayCadena;
	}

	/**
	 * Retorna el camino simple mas barato a un vertice de destino
	 * @param idDest la llave del vertice de destino
	 * @return Camino, el camino mas corto, NULL en caso contrario
	 */
	public Camino<K,V,A> darCaminoSimpleMasCorto(K idDest){
		Camino<K,V,A> masCorto = null; 
		{
			marcar( );
			if ( this.darId().compareTo( idDest ) == 0 ) { 
				masCorto = new Camino(this, false); 
			}else{
				Iterator<Arco<K,V,A>> itsucesores = darSucesores(); while ( itsucesores.hasNext() )
				{
					Arco<K,V,A> arco = itsucesores.next();
					Vertice<K,V,A> vtceSucesor = arco.darDestino();
					Camino<K,V,A> camino = vtceSucesor.darCaminoSimpleMasCorto( idDest );
					if ( camino != null ){ 
						camino.agregarArcoComienzo( arco ); 
						if(masCorto == null){ 
							masCorto = camino; }
						else if ( masCorto.darLongitud() > camino.darLongitud() ) { 
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
	 * Retorna el iterador de los arcos sucesores
	 * @return
	 */
	private Iterator<Arco<K, V, A>> darSucesores() {
		return sucesores.iterator();
	}

	@Override
	public int compareTo(Vertice<K, V, A> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
