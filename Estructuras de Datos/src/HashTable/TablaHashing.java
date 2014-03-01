package HashTable;

import java.io.Serializable;
import java.util.Iterator;

import Lista.ILista;
import ListaEncadenada.ListaEncadenada;
import ListaOrdenada.ListaOrdenada;

public class TablaHashing<K,V extends Comparable<?super V>> implements ITablaHashing<K,V>,Serializable {

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

	public boolean agregar(K nLlave, V nElemento) {
		reHash();
		NodoTabla<K,V> nuevo = new NodoTabla<K,V>(nLlave, nElemento);
		int ubicacion = hash(nLlave);
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

	
	public Object[] darArreglo(){
		Object[] arreglo = new Object[darLongitud()];
		int posicion = 0;
		for(int i = 0; i<areaPrimaria.length;i++){
			ListaOrdenada<NodoTabla<K,V>> lista = areaPrimaria[i];
			Object[] arregloLista = lista.darArreglo();
			for(int j = 0;j<arregloLista.length;j++){
				arreglo[posicion] = ((NodoTabla<K,V>)arregloLista[j]).darElemento();
				posicion++;
			}
		}
		return arreglo;
	}

	
	public int darLongitud(){
		int tama�o = 0;
		for(int i = 0;i<areaPrimaria.length;i++){
			ListaOrdenada<NodoTabla<K,V>> actual = areaPrimaria[i];
			tama�o += actual.darLongitud(); 
		}
		tamano = tama�o;
		return tamano;
	}

	public ListaEncadenada<V> darLista(){
		ListaEncadenada<V> lista = new ListaEncadenada();
		
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
				iterador.remove();
				tamano--;
				return elemento.darElemento();
			}
		}
		return null;
	}

	public int hash(K nLlave) {
		return nLlave.hashCode() % capacidad;
	}
	
	public Iterator iterator(){
		return new IteratorTabla(areaPrimaria);
	}
	
	public void reHash() {
		double factor = (double)tamano/capacidad;
		if(factor>factorCarga){
			capacidad =capacidad*crecimiento;
			ListaOrdenada<NodoTabla<K,V>>[] nueva = new ListaOrdenada[areaPrimaria.length*crecimiento];
			for(int i = 0; i<nueva.length;i++){
				nueva[i] = new ListaOrdenada<NodoTabla<K,V>>();
			}
			for(int i = 0; i<areaPrimaria.length;i++){
				ILista<NodoTabla<K,V>> lista = areaPrimaria[i];
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
