package ArbolTrie;

import java.io.Serializable;
import java.util.Iterator;

import ListaOrdenada.ListaOrdenada;

public class ArbolTrie<T extends Comparable<T>> implements IArbolTrie<T>,Serializable {

	//------------------------------------------------------------
	// Atributos
	//------------------------------------------------------------
	
	/**
	 * La raiz del arbol trie
	 */
	private NodoTrie raiz;
	
	/**
	 * La lista ordenada de palabras
	 */
	private ListaOrdenada<String> palabras;
	
	//------------------------------------------------------------
	// Constructores
	//------------------------------------------------------------
	
	public ArbolTrie(){
		raiz = new NodoTrie('*');
		palabras = new ListaOrdenada<String>();
	}
	
	//------------------------------------------------------------
	// Metodos
	//------------------------------------------------------------
	
	@Override
	public boolean agregar(String palabra, T elemento) {
		if(elemento == null)
			return false;
		else{
			if(palabras.buscar(palabra) == null)
				palabras.agregar(palabra);
			return raiz.agregar(palabra, elemento);
		}
	}

	@Override
	public boolean agregarMultiples(String palabra, Iterator<T> elementos) {
		return raiz.agregarMultiples(palabra,elementos);
	}

	@Override
	public Iterator<T> buscar(String palabra) {
		return raiz.buscar(palabra);
	}
	
	public Iterator<String> darPalabras(){
		return palabras.iterator();
	}

	@Override
	public boolean eliminar(String palabra) {
		palabras.eliminar(palabra);
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
