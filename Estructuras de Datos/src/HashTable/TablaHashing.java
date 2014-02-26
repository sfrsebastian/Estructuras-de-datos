package HashTable;

import java.io.Serializable;
import java.util.Iterator;

import Lista.Lista;
import ListaOrdenada.IListaOrdenada;
import ListaOrdenada.ListaOrdenada;

public class TablaHashing<K,V extends Comparable<?super V>> implements ITablaHashing<K,V>,Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El tamano de la table de hashing
	 */
	private int tamano;

	/**
	 * La capacidad de almacenamiento de la tabla
	 */
	private int capacidad;

	/**
	 * El factor de carga que mide el rango para el crecimiento del hash
	 */
	private double factorCarga;

	/**
	 * Es el area primaria de la tabla de hashing
	 */
	private ListaOrdenada<NodoTabla<K,V>>[] areaPrimaria;
	
	/**
	 * El factor de crecimiento de la tabla de hashing
	 */
	private int crecimiento;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------

	/**
	 * Contruye una nueva tabla de hashing dado un tamano y un factor de crecimiento
	 * @param nTamano 
	 * @param nCrecimiento
	 */
	public TablaHashing(int nTamano, int nCrecimiento) {
		capacidad = nTamano;
		areaPrimaria = new ListaOrdenada[capacidad];
		for(int i = 0; i<areaPrimaria.length;i++){
			areaPrimaria[i] = new ListaOrdenada<NodoTabla<K,V>>();
		}
		tamano = 0;
		crecimiento = nCrecimiento;
		factorCarga = 0.75;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------

	public boolean agregar(K nLlave, V nElemento) {
		reHash();
		NodoTabla<K,V> nuevo = new NodoTabla<K,V>(nLlave, nElemento);
		int ubicacion = hash(nLlave);
		System.out.println(ubicacion);
		areaPrimaria[ubicacion].agregar(nuevo);
		tamano++;
		return true;
	}

	public V buscar(K nLlave) {
		Iterator<NodoTabla<K,V>> iterador = areaPrimaria[hash(nLlave)].iterator();
		while(iterador.hasNext()){
			NodoTabla<K,V>elemento = iterador.next();
			if(elemento.darLlave().toString().equals(nLlave.toString())){
				return elemento.darElemento();
			}
		}
		return null;
	}

	/**
	 * Da un arreglo de todos los elementos de la tabla de hashing
	 * @return Object[] Arreglo con los elementos de la tabla
	 */
	public Object[] darArreglo(){
		Object[] arreglo = new Object[darLongitud()];
		int posicion = 0;
		for(int i = 0; i<areaPrimaria.length;i++){
			ListaOrdenada<NodoTabla<K,V>> lista = areaPrimaria[i];
			Object[] arregloLista = lista.darElementos();
			for(int j = 0;j<arregloLista.length;j++){
				arreglo[posicion] = (V)((NodoTabla<K, V>)arregloLista[j]).darElemento();
				posicion++;
			}
		}
		return arreglo;
	}

	/**
	 * Da la longitud de la tabla de hashing contando todos los elementos 
	 * @return int La cantidad de elementos en la tabla
	 */
	public int darLongitud(){
		int tamano = 0;
		for(int i = 0;i<areaPrimaria.length;i++){
			ListaOrdenada<NodoTabla<K,V>> actual = areaPrimaria[i];
			tamano += actual.darLongitud(); 
		}
		tamano = tamano;
		return tamano;
	}

	/**
	 * Retorna una lista simple encadenada de todos los elementos de la tabla
	 * @return Lista La lista con todos los elementos
	 */
	public Lista<V> darLista(){
		Lista<V> lista = new Lista();
		
		Iterator i = iterator();
		while(i.hasNext()){
			lista.agregar((V) i.next());
		}
		return lista;
	}
	
	public V eliminar(K nLlave){
		Iterator<NodoTabla<K,V>> iterador = areaPrimaria[hash(nLlave)].iterator();
		while(iterador.hasNext()){
			NodoTabla<K,V>elemento = iterador.next();
			if(elemento.darLlave().toString().equals(nLlave.toString())){
				areaPrimaria[hash(nLlave)].eliminar(elemento);
				tamano--;
				return elemento.darElemento();
			}
		}
		return null;
	}

	/**
	 * Permite realizar la funcion de hash para la llave dada
	 * @param nLlave La llave a la que se aplica el hash
	 * @return El numero resultado de la operacion
	 */
	public int hash(K nLlave) {
		return nLlave.hashCode() % capacidad;
	}
	
	/**
	 * Retorna el iterador de la tabla del area primaria
	 */
	public Iterator iterator(){
		return new IteratorTabla(areaPrimaria);
	}
	
	/**
	 * Crece el tamano de la tabla de hashing al contruir una nueva segun
	 * el factor de crecimiento para manejar colisiones y agregar mas
	 * elementos
	 */
	public void reHash() {
		System.out.println("Tamano " + tamano);
		System.out.println("Capacidad: "+ capacidad);
		double factor = (double)tamano/capacidad;
		System.out.println("factor: "+ factor);
		if(factor>factorCarga){
			System.out.println("rehash-----");
			capacidad =capacidad*2;
			ListaOrdenada<NodoTabla<K,V>>[] nueva = new ListaOrdenada[areaPrimaria.length*2];
			for(int i = 0; i<nueva.length;i++){
				nueva[i] = new ListaOrdenada<NodoTabla<K,V>>();
			}
			for(int i = 0; i<areaPrimaria.length;i++){
				IListaOrdenada<NodoTabla<K,V>> lista = areaPrimaria[i];
				Iterator<NodoTabla<K,V>> iterador = lista.iterator();
				while(iterador.hasNext()){
					NodoTabla<K,V> actual = iterador.next();
					nueva[hash((K) actual.darLlave())].agregar(actual);
				}
			}
			areaPrimaria = nueva;
		}
	}
}
