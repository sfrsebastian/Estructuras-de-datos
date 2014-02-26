package Lista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Lista.NodoLista;

public class IteratorLista <T extends Comparable<?super T>> implements Iterator<T>{

	NodoLista<T> proximo;
	NodoLista<T> anteriorProximo; 
	NodoLista<T> anteriorAnterior;
	
	public IteratorLista(NodoLista<T> primerNodo){
		proximo = primerNodo;
		anteriorProximo = null;
		anteriorAnterior = null;
	}

	@Override
	public boolean hasNext() {
		return proximo != null;
	}

	@Override
	public T next() throws NoSuchElementException{
		if(proximo==null){
			throw new NoSuchElementException("No hay proximo");
		}
		else{
			T elemento = proximo.darElemento();
			anteriorAnterior = anteriorProximo;
			anteriorProximo = proximo;
			proximo = proximo.darSiguiente();
			return elemento;
		}
	}

	@Override
	public void remove()throws UnsupportedOperationException, NoSuchElementException{
		if(anteriorProximo == null && anteriorAnterior == null){
			throw new NoSuchElementException("No hay elemento a eliminar");
		}
		if(anteriorProximo != null && anteriorAnterior == null){
			throw new UnsupportedOperationException("No se puede eliminar el primer elemento de la lista");
		}
		else{
			anteriorAnterior.cambiarSiguiente(proximo);
			anteriorProximo.cambiarSiguiente(null);
			anteriorProximo = anteriorAnterior;
			//anteriorAnterior = null;
		}
		
	}

}
