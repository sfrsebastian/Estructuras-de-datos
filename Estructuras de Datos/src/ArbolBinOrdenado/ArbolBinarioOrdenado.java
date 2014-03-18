package ArbolBinOrdenado;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Cola.Cola;
import ListaEncadenada.ListaEncadenada;
public class ArbolBinarioOrdenado<T extends Comparable<T>> implements IArbolBinarioOrdenado<T> {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El peso del arbol 
	 */
	protected int peso;
	
	/**
	 * La raiz del arbol ordenado
	 */
	protected NodoArbolBinario<T> raiz;
	
	/**
	 * El comparador del arbol binario
	 */
	private Comparator<T> comparador;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo arbol binario
	 */
	public ArbolBinarioOrdenado(){
		raiz = null;
		peso = 0;
		comparador = null;
	}
	
	/**
	 * Crea un nuevo arbol binario con un comparador
	 * @param comp
	 */
	public ArbolBinarioOrdenado(Comparator<T> comp){
		raiz = null;
		peso = 0;
		comparador = comp;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Da el peso del arbol
	 * @return el peso del arbol
	 */
	public int darPeso() {
		return peso;
	}

	/**
	 * Da la altura del arbol
	 * @return La altura del arbol
	 */
	public int darAltura() {
		return raiz != null?raiz.darAltura():0;
	}
	
	/**
	 * Agrega el elemento dado por parametro al arbol
	 * @param El elemento a agregar
	 * @return TRUE si se agrega el elemento, FALSE de lo contrario
	 */
	public boolean agregar(T elemento) {
		if(elemento != null){
			if(raiz == null){
				raiz = new NodoArbolBinario<T>(elemento,comparador);
				peso++;
				return true;
			}
			else if(raiz.agregar(elemento)){
				peso++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca el elemento dado por parametro
	 * @param El elemento a buscar
	 * @return El elemento encontrado, null de lo contrario 
	 */
	public T buscar(T elemento) {
		if(raiz == null){
			return null;
		}
		else{
			return raiz.buscar(elemento);
		}	
	}

	/**
	 * Elimina del arbol el elemento dado por parametro
	 * @param el elemento a eliminar
	 * @return TRUE si fue eliminado, FALSE de lo contrario.
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
		cola.agregar(raiz);
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

	/**
	 * Retorna un iterador inorden
	 */
	public Iterator<T> iterator() {
		return recorrerInorden();
	}
}
