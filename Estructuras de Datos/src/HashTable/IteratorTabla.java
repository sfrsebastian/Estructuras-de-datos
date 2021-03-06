package HashTable;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

import HashTable.NodoTabla;
import Lista.ILista;
import ListaOrdenada.ListaOrdenada;

public class IteratorTabla<V extends Comparable<V>> implements Iterator<V>,Serializable {

	private ILista<NodoTabla<?,V>> listaActual;
	
	private ILista<NodoTabla<?,V>>[] listas;
	
	private Iterator<NodoTabla<?,V>> iteradorActual;
	
	private int posicion;

	public IteratorTabla(ListaOrdenada[] lista){
		posicion = 0;
		listas = lista;
		listaActual = listas[0];
		iteradorActual = listaActual.iterator();
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
	 * @see estructuras.Iterator#remove()
	 */
	public void remove() {
		iteradorActual.remove();
	}

}
