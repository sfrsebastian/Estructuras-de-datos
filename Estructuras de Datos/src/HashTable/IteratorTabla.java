package HashTable;

import java.util.Iterator;

public class IteratorTabla<V> implements Iterator<V> {

	private NodoTabla<?,V> anteriorAnterior;

	private NodoTabla<?,V> anteriorProximo;

	private NodoTabla<?,V> proximo;

	public IteratorTabla(NodoTabla<?,V> nodo) {

	}

	/**
	 * @see estructuras.Iterator#next()
	 */
	public V next() {
		return null;
	}


	/**
	 * @see estructuras.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return false;
	}


	/**
	 * @see estructuras.Iterator#remove()
	 */
	public void remove() {

	}

}
