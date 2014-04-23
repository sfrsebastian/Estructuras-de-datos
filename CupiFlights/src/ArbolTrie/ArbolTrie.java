package ArbolTrie;

import java.util.Iterator;

import ListaOrdenada.ListaOrdenada;

public class ArbolTrie<T extends Comparable<T>> implements IArbolTrie<T> {

	//------------------------------------------------------------
	// Atributos
	//------------------------------------------------------------
	
	/**
	 * La raiz del arbol trie
	 */
	private NodoTrie raiz;
	
	//------------------------------------------------------------
	// Constructores
	//------------------------------------------------------------
	
	public ArbolTrie(){
		raiz = new NodoTrie('*');
	}
	
	//------------------------------------------------------------
	// Metodos
	//------------------------------------------------------------
	
	@Override
	public boolean agregar(String palabra, T elemento) {
		if(elemento == null)
			return false;
		else
			return raiz.agregar(palabra, elemento);
	}

	@Override
	public boolean agregarMultiples(String palabra, Iterator<T> elementos) {
		return raiz.agregarMultiples(palabra,elementos);
	}

	@Override
	public Iterator<T> buscar(String palabra) {
		return raiz.buscar(palabra);
	}

	@Override
	public boolean eliminar(String palabra) {
		boolean elim = raiz.eliminar(palabra);
		raiz.eliminarDependencias();
		return elim;
	}

	@Override
	public Iterator<T> buscarXPrefijo(String palabra) {
		ListaOrdenada<T> elementos = new ListaOrdenada<T>();
		return raiz.buscarXPrefijo(palabra, elementos);
	}

	@Override
	public boolean contienePrefijo(String prefijo) {
		return raiz.contienePrefijo(prefijo);
	}

	@Override
	public T buscarElemento(String palabra, T elemento) {
		return (T) raiz.buscarElemento(palabra,elemento);
	}

}
