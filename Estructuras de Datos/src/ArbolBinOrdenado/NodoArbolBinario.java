package ArbolBinOrdenado;

import java.util.Comparator;
import Lista.Lista;
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
	private NodoArbolBinario<T> izquierdo;
	
	/**
	 * El hijo derehoc que tiene el nodo
	 */
	private NodoArbolBinario<T> derecho;
	
	/**
	 * El comparados del nodo
	 */
	private Comparator<T> comparador;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	public NodoArbolBinario(T nElemento, Comparator<T> c){
		elemento = nElemento;
		izquierdo = null;
		derecho = null;
		comparador = c;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	/**
	 * Agrega un nuevo elemento al nodo actual
	 * @param pElemento El elemento que se quiere agregar
	 * @return boolean TRUE si se pudo agregar, false en caso contrario
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
	 * @return T el elemento, null en caso contrario
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
				NodoArbolBinario<T> menor = derecho.darMenor();
				derecho = derecho.eliminar(menor.darElemento());
				menor.derecho = derecho;
				menor.izquierdo = izquierdo;
				return menor;
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
	
	private NodoArbolBinario<T> darMenor() {
		if(esHoja()){
			return this;
		}
		else{
			if(izquierdo!=null)
				return izquierdo.darMenor();
			else
				return this;
		}
	}
	
	/**
	 * Determina si el nodo es una hoja
	 * @return TRUE si no tiene nodos hijos FALSE en caso contrario
	 */
	public boolean esHoja(){
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
	 * 
	 * @param lista
	 */
	public void agregarElementosInorden(Lista lista){
		if(izquierdo != null){
			izquierdo.agregarElementosInorden(lista);
		}
		lista.agregar(this.darElemento());
		if(derecho != null){
			derecho.agregarElementosInorden(lista);
		}
	}

	/**
	 * 
	 * @param listaNodos
	 */
	public void agregarElementosPreorden(ListaEncadenada<T> listaNodos) {
		listaNodos.agregar(darElemento());
		if(izquierdo != null){
			izquierdo.agregarElementosPreorden(listaNodos);
		}
		if(derecho != null){
			derecho.agregarElementosPreorden(listaNodos);
		}
	}

	/**
	 * 
	 * @param listaNodos
	 */
	public void agregarElementosPosorden(ListaEncadenada listaNodos) {
		if(izquierdo != null){
			izquierdo.agregarElementosPosorden(listaNodos);
		}
		if(derecho != null){
			derecho.agregarElementosPosorden(listaNodos);
		}
		listaNodos.agregar(this.darElemento());
	}

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
	public int comparar(T elem1, T elem2){
		if (comparador == null){
			return elem1.compareTo(elem2);
		}
		else{
			return comparador.compare(elem1, elem2);
		}
	}

	public int darAltura() {
		// TODO Auto-generated method stub
		return 0;
	}

}
