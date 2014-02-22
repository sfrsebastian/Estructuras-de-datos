package HashTable;

import java.util.Iterator;

import ListaOrdenada.IListaOrdenada;

public class TablaHashing<K,V extends Comparable<?super V>> implements ITablaHashing<K,V> {

	private int tamano;

	private int capacidad;

	private double factorCarga;

	private int crecimento;// se llamaba incremento no se si es lo mismo.

	private IListaOrdenada<V>[] areaPrimaria;

	public TablaHashing(int nTamano, double nCrecimiento) {
	}

	public int hash(K k) {
		return 0;
	}

	public void reHash() {

	}

	
	public Iterator<V> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean agregar(K nLlave, V nElemento) {
		// TODO Auto-generated method stub
		return false;
	}

	public V eliminar(K nLlave){
		// TODO Auto-generated method stub
		return null;
	}

	public V buscar(K nLlave) {
		// TODO Auto-generated method stub
		return null;
	}


}
