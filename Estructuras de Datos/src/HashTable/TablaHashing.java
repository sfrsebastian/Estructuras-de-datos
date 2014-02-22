package HashTable;

import java.util.Iterator;

import Lista.Lista;
import ListaOrdenada.IListaOrdenada;
import ListaOrdenada.ListaOrdenada;

public class TablaHashing<K,V extends Comparable<?super V>> implements ITablaHashing<K,V> {

	private int tamano;

	private int capacidad;

	private double factorCarga;

	private ListaOrdenada<NodoTabla<K,V>>[] areaPrimaria;
	
	private int crecimiento;

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

	public int hash(K nLlave) {
		return nLlave.hashCode() % capacidad;
	}

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

	
	public Iterator iterator(){
		return new IteratorTabla(areaPrimaria);
	}

	
	public boolean agregar(K nLlave, V nElemento) {
		reHash();
		NodoTabla<K,V> nuevo = new NodoTabla<K,V>(nLlave, nElemento);
		int ubicacion = hash(nLlave);
		System.out.println(ubicacion);
		areaPrimaria[ubicacion].agregar(nuevo);
		tamano++;
		return true;
	}

	public V eliminar(K nLlave){
		Iterator<NodoTabla<K,V>> iterador = areaPrimaria[hash(nLlave)].iterator();
		while(iterador.hasNext()){
			NodoTabla<K,V>elemento = iterador.next();
			if(elemento.darLlave() == nLlave){
				iterador.remove();
				tamano--;
				return elemento.darElemento();
			}
		}
		return null;
	}

	public V buscar(K nLlave) {
		Iterator<NodoTabla<K,V>> iterador = areaPrimaria[hash(nLlave)].iterator();
		while(iterador.hasNext()){
			NodoTabla<K,V>elemento = iterador.next();
			if(elemento.darLlave() == nLlave){
				return elemento.darElemento();
			}
		}
		return null;
	}
	
	public int darLongitud(){
		int tamaño = 0;
		for(int i = 0;i<areaPrimaria.length;i++){
			ListaOrdenada<NodoTabla<K,V>> actual = areaPrimaria[i];
			tamaño += actual.darLongitud(); 
		}
		tamano = tamaño;
		return tamano;
	}
}
