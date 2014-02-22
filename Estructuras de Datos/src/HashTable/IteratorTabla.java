package HashTable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ListaOrdenada.IListaOrdenada;
import ListaOrdenada.ListaOrdenada;

public class IteratorTabla<V extends Comparable<V>> implements Iterator<V> {

	private NodoTabla<?,V> anteriorAnterior;

	private NodoTabla<?,V> anteriorProximo;

	private NodoTabla<?,V> proximo;
	
	private IListaOrdenada<NodoTabla<?,V>> listaActual;
	
	private IListaOrdenada<NodoTabla<?,V>>[] listas;
	
	private Iterator<NodoTabla<?,V>> iteradorActual;
	
	private int posicion;

	public IteratorTabla(ListaOrdenada<NodoTabla<?,V>>[] lista){
		posicion = 0;
		listas = lista;
		listaActual = listas[0];
		iteradorActual = listaActual.iterator();
	}

	/**
	 * @see estructuras.Iterator#next()
	 */
	public V next() throws NoSuchElementException {
		if(buscarIterador()){
			return (V) iteradorActual.next().darElemento();
		}
		else{
			throw new NoSuchElementException("No hay mas elementos");
		}
	}
	/**
	 * @see estructuras.Iterator#hasNext()
	 */
	public boolean hasNext() {
		if(iteradorActual.hasNext()==true){
			return true;
		}
		else{
			return buscarIterador();
		}
	}


	/**
	 * @see estructuras.Iterator#remove()
	 */
	public void remove() {
		iteradorActual.remove();
	}
	
	private boolean buscarIterador(){
		if(iteradorActual.hasNext()==true){
			return true;
		}
		else{
			try{
				posicion++;
				listaActual = listas[posicion];
				iteradorActual = listaActual.iterator();
				return hasNext();
			}
			catch(Exception e){
				return false;
			}
		}
	}

}
