package Lista;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorLista <T extends Comparable<?super T>> implements Iterator<T>{

	NodoLista<T> proximo;
	NodoLista<T> anteriorProximo; 
	NodoLista<T> anteriorAnterior;
	Lista<T> lista;
	
	public IteratorLista(Lista<T> nLista){
		lista = nLista;
		proximo = nLista.primero;
		anteriorProximo = null;
		anteriorAnterior = null;
	}

	@Override
	public boolean hasNext() {
		return proximo != null;
	}

	@Override
	public T next() throws NoSuchElementException{
		if(proximo == null){
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
	public void remove()throws NoSuchElementException{
		if(anteriorProximo == null && anteriorAnterior == null){
			throw new NoSuchElementException("No hay elemento a eliminar");
		}
		if(anteriorProximo != null && anteriorAnterior == null){
			lista.eliminar(anteriorProximo.darElemento());
			proximo = lista.primero;
			anteriorProximo = null;
			anteriorAnterior = null;
		}
		else{
			lista.eliminar(anteriorProximo.darElemento());
			anteriorProximo = anteriorAnterior;
			anteriorAnterior = null;
		}
		
	}

}
