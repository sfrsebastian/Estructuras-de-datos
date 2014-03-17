package ArbolBinOrdenado;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Cola.Cola;
import ListaEncadenada.ListaEncadenada;

public class ArbolBinarioAVLOrdenado<T extends Comparable<T>>{
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La raiz del arbol AVL 
	 */
	private NodoArbolBinarioAVL<T> raiz;
	
	/**
	 * El comparador del arbol 
	 */
	private Comparator<T> comparador;
	
	/**
	 * El peso del arbol binario Adelson,Velski,Ledison
	 */
	private int peso;
	
	/**
	 * La altura del arbol binario
	 */
	private int altura;
	
	//------------------------------------------
	// Constructores
	//------------------------------------------
	
	public ArbolBinarioAVLOrdenado(Comparator<T> c){
		comparador = c;
		raiz = null;
	}
	
	public ArbolBinarioAVLOrdenado(){
		comparador = null;
		raiz = null;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	/**
	 * Balancea el arbol AVL
	 */
	public void balancear(){
		if (raiz != null) 
			raiz.balancearXAltura();
	}
	
	/**
	 * 
	 * @param elemento
	 * @return
	 */
	public boolean eliminar(T elemento){
		if(raiz == null){
			return false;
		}
		else{
			raiz = raiz.eliminar(elemento);
			peso--;
		return true;
		}
	}
	
	/**
	 * 
	 * @param elemento
	 * @return
	 */
	public T buscar(T elemento){
		if(elemento != null){
			return (raiz == null) ? null : raiz.buscar(elemento);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @param elemento
	 * @return
	 */
	public boolean agregar(T elemento){
		if(elemento != null){
			if (raiz == null){
				raiz = new NodoArbolBinarioAVL<T>(elemento, comparador);
			}else{
				boolean b = raiz.agregar(elemento);
				if(b){
					balancear();
				}
				return b;
			}
		}else{
			return false;
		}
		return true;		
	}
	
	/**
	 * Da la altura del arbol
	 * @return La altura del arbol
	 */
	public int darAltura() {
		return raiz != null?raiz.darAltura():0;
	}
	
	/**
	 * Da el peso del arbol
	 * @return el peso del arbol
	 */
	public int darPeso() {
		return peso;
	}
	
	/**
	 * Da un iterador con los elementos ordenados en PREORDEN
	 * @return iterador con los elementos del arbol
	 */
	public Iterator<T> recorrerPreorden() {
		ListaEncadenada<T> listaNodos = new ListaEncadenada<T>();
		raiz.agregarElementosPreorden(listaNodos);
		return listaNodos.iterator();
	}

	/**
	 * Da un iterador con los elementos ordenados en INORDEN
	 * @return iterador con los elementos del arbol
	 */
	public Iterator<T> recorrerInorden() {
		ListaEncadenada<T> listaNodos = new ListaEncadenada<T>();
		raiz.agregarElementosInorden(listaNodos);
		return listaNodos.iterator();
	}

	/**
	 * Da un iterador con los elementos ordenados en POSORDEN
	 * @return iterador con los elementos del arbol
	 */
	public Iterator<T> recorrerPosorden() {
		ListaEncadenada<T> listaNodos = new ListaEncadenada<T>();
		raiz.agregarElementosPosorden(listaNodos);
		return listaNodos.iterator();
	}

	/**
	 * Recorre el arbol por niveles
	 * @return Iterador con los elementos del arbol.
	 */
	public Iterator<T> recorrerNiveles() {
		Cola<NodoArbolBinario<T>> cola = new Cola<NodoArbolBinario<T>>();
		ListaEncadenada<T>lista = new ListaEncadenada<T>();
		//cola.agregar(raiz); TODO 
		try{
			while(true){
				NodoArbolBinario<T> dado = cola.dar();
				lista.agregar(dado.darElemento());
				if(dado.izquierdo !=null){
					cola.agregar(dado.izquierdo);
				}
				if(dado.derecho != null){
					cola.agregar(dado.derecho);
				}
			}
		}
		catch(NoSuchElementException e){
			return lista.iterator();
		}
	}
}
