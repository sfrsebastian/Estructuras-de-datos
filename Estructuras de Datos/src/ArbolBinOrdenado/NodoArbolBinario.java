package ArbolBinOrdenado;

import java.util.Comparator;

import ListaEncadenada.ListaEncadenada;

public class NodoArbolBinario<T extends Comparable<T>>{
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El elemento que tiene el nodo
	 */
	private T elemento;
	
	/**
	 * El hijo izquierdo que tiene el nodo
	 */
	public NodoArbolBinario<T> izquierdo;
	
	/**
	 * El hijo derehoc que tiene el nodo
	 */
	public NodoArbolBinario<T> derecho;
	
	/**
	 * El comparados del nodo
	 */
	private Comparator<T> comparador;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo nodoArbolBinario
	 * @param nElemento El elemento del arbol
	 * @param c el comparador del arbol
	 */
	public NodoArbolBinario(T nElemento, Comparator<T> c){
		elemento = nElemento;
		izquierdo = null;
		derecho = null;
		comparador = c;
	}
	
	/**
	 * Retorna el nodo con el mayor elemento del nodo
	 * @return El mayor nodo.
	 */
	private NodoArbolBinario<T> darMayor() {
		if(esHoja()){
			return this;
		}
		else{
			if(derecho!=null)
				return derecho.darMayor();
			else
				return this;
		}
	}

	/**
	 * Determina si el nodo es una hoja
	 * @return TRUE si no tiene nodos hijos FALSE en caso contrario
	 */
	private boolean esHoja(){
		if(izquierdo == null && derecho == null)
			return true;
		else
			return false;
	}

	/**
	 * Da el elemento que tiene el nodo
	 * @return T el elemento del nodo
	 */
	public T darElemento(){
		return elemento;
	}

	/**
	 * Retorna la altura del arbol
	 * Compara las alturas de los nodos izquierdo y derechos.
	 * @return La altura del arbol
	 */
	public int darAltura() {
		int izq = 0;
		int der = 0;
		if(esHoja()){
			return 1;
		}
		if(izquierdo != null){
			izq = izquierdo.darAltura();
		}
		if(derecho != null){
			der = derecho.darAltura();
		}
		return Math.max(izq, der)+1;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Agrega un nuevo elemento al nodo actual
	 * @param pElemento El elemento que se quiere agregar
	 * @return boolean TRUE si se pudo agregar, FALSE en caso contrario
	 */
	public boolean agregar(T pElemento){
		if(comparar(elemento,pElemento) == 0){
			return false;
		}
		else if(comparar(elemento,pElemento) < 0){
			if(derecho != null){
				return derecho.agregar(pElemento);
			}
			else{
				derecho = new NodoArbolBinario<T>(pElemento, comparador);
				return true;
			}
		}
		else{
			if(izquierdo != null){
				return izquierdo.agregar(pElemento);
			}
			else{
				izquierdo = new NodoArbolBinario<T>(pElemento, comparador);
				return true;
			}
		}
	}
	
	/**
	 * Busca un elemento T en el arbol binario
	 * @param pElemento El elemento que se quiere buscar
	 * @return T el elemento buscado, null en caso contrario
	 */
	public T buscar(T pElemento){
		if(comparar(elemento,pElemento) == 0){
			return darElemento();
		}
		else if(comparar(elemento,pElemento) < 0){
			if(derecho != null){
				return derecho.buscar(pElemento);
			}
		}
		else {
			if(izquierdo != null){
				return izquierdo.buscar(pElemento);
			}
		}
		return null;
	}
	
	/**
	 * Elimina el elemento dado por parametro
	 * @param pElemento El elemento a eliminar
	 * @return El nodoArbol reorganizado con el elemento eliminado.
	 */
	public NodoArbolBinario <T> eliminar(T pElemento){
		if(comparar(elemento,pElemento) == 0){
			if(esHoja()){
				return null;
			}
			else if(derecho == null){
				return izquierdo;
			}
			else if(izquierdo == null){
				return derecho;
			}
			else{
				NodoArbolBinario<T> mayor = izquierdo.darMayor();
				izquierdo = izquierdo.eliminar(mayor.darElemento());
				mayor.derecho = derecho;
				mayor.izquierdo = izquierdo;
				return mayor;
			}
		}
		else if(comparar(elemento,pElemento) < 0){
			if(derecho != null){
				derecho = derecho.eliminar(pElemento);
			}
		}
		else{
			if(izquierdo != null){
				izquierdo = izquierdo.eliminar(pElemento);
			}
		}
		return this;
	}
	
	/**
	 * Agrega a la lista dada por parametro los elementos INORDEN (izquierdo-raiz-derecho)
	 * @param La lista para acumular los valores.
	 */
	public void agregarElementosInorden(ListaEncadenada<T> lista){
		if(izquierdo != null){
			izquierdo.agregarElementosInorden(lista);
		}
		lista.agregar(this.darElemento());
		if(derecho != null){
			derecho.agregarElementosInorden(lista);
		}
	}

	/**
	 *Agrega a la lista dada por parametro los elementos PREORDEN (raiz-izquierdo-derecho)
	 * @param La lista para acumular los valores.
	 */
	public void agregarElementosPreorden(ListaEncadenada<T> lista) {
		lista.agregar(darElemento());
		if(izquierdo != null){
			izquierdo.agregarElementosPreorden(lista);
		}
		if(derecho != null){
			derecho.agregarElementosPreorden(lista);
		}
	}

	/**
	 * Agrega a la lista dada por parametro los elemento POSORDEN (izquierdo-derecho-raiz)
	 * @param La lista para acumular los valores.
	 */
	public void agregarElementosPosorden(ListaEncadenada<T> lista) {
		if(izquierdo != null){
			izquierdo.agregarElementosPosorden(lista);
		}
		if(derecho != null){
			derecho.agregarElementosPosorden(lista);
		}
		lista.agregar(this.darElemento());
	}

	/**
	 * Metodo to string del nodo. Indica quien es y cuales son sus nodos izquierdo y derecho.
	 * @return toString del nodo
	 */
	public String toString(){
		String der = "NO TIENE";
		String izq = "NO TIENE";
		if(derecho != null){
			try {
				der = derecho.darElemento().toString();
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				der = "TIENE";
			}
		}
		if(izquierdo != null){
			try {
				izq = izquierdo.darElemento().toString();
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				izq = "TIENE";
			}
		}
		return "Soy: " + elemento.toString() + ", derecho: " + der + " izquierdo: " + izq;
	}

	/**
	 * Compara dos elementos por su comparador, si este existe
	 * De lo contrario los compara por su metodo de compareTo
	 * @param elem1 El elemento visitante
	 * @param elem2 El elemento local
	 * @return int El valor de la comparacion 
	 */
	private int comparar(T elem1, T elem2){
		if (comparador == null){
			return elem1.compareTo(elem2);
		}
		else{
			return comparador.compare(elem1, elem2);
		}
	}

}
