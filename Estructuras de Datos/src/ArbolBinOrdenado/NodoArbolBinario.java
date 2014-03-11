package ArbolBinOrdenado;

import java.util.Comparator;

public class NodoArbolBinario<T>{

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
		if((comparar(pElemento, elemento)) == 0){
			System.out.println("Intentado agregar elemento repetido");
			return false;
		}else{
			if(comparar(pElemento, elemento) < 0){
				if(izquierdo == null){
				NodoArbolBinario<T> izq = new  NodoArbolBinario(pElemento,comparador);
				izquierdo = izq;
				return true;
				}else{
					return izquierdo.agregar(pElemento);
				}
			}
			else if(comparar(pElemento, elemento) > 0){
				if(derecho == null){
					NodoArbolBinario<T> der = new NodoArbolBinario(pElemento, comparador);
					derecho = der;
					return true;
				}else{
					return derecho.agregar(pElemento);
				}
			}
		}
		return false;
	}
	
	/**
	 * Busca un elemento T en el arbol binario
	 * @param pElemento El elemento que se quiere buscar
	 * @return T el elemento, null en caso contrario
	 */
	public T buscar(T pElemento){
		if((comparar(pElemento, elemento)) == 0){
			return darElemento();
		}else{
			if((comparar(pElemento, elemento) > 0) && derecho != null){
				return derecho.buscar(pElemento);
			}else if((comparar(pElemento, elemento) < 0) && izquierdo != null){
				return izquierdo.buscar(pElemento);
			}
		}
		return null;
	}
	
	public T eliminar(T pElemento){
		if(derecho != null && (comparar(pElemento, elemento)) > 0){
			if(comparar(pElemento, derecho.darElemento()) == 0){
				if(derecho.esHoja()){
					T elem = derecho.darElemento();
					derecho = null;
					return elem;
				}else if(derecho.tieneSoloUnSubArbol()){
					if(derecho.derecho == null){
						T elem = derecho.darElemento();
						this.derecho = this.derecho.izquierdo;
						return elem;
					}else if(derecho.izquierdo == null){
						T elem = derecho.darElemento();
						this.derecho = this.derecho.derecho;
						return elem;
					}		
				}else if(!derecho.tieneSoloUnSubArbol()){
					T retorno = derecho.darElemento();
					T elem = derecho.izquierdo.darElemMasDerecho();
					if(comparar(derecho.izquierdo.darElemento(), elem) == 0){
						derecho.izquierdo = null;
					}
					derecho.elemento = elem;
					
					return retorno;
				}
			}else{
				return derecho.eliminar(pElemento);
			}
		}
		if(izquierdo != null && (comparar(pElemento, elemento)) < 0){
			if(comparar(pElemento, izquierdo.darElemento()) == 0){
				if(izquierdo.esHoja()){
					T elem = izquierdo.darElemento();
					izquierdo = null;
					return elem;
				}else if(izquierdo.tieneSoloUnSubArbol()){
					if(izquierdo.derecho == null){
						T elem = izquierdo.darElemento();
						this.izquierdo = this.izquierdo.izquierdo;
						return elem;
					}else if(izquierdo.izquierdo == null){
						T elem = izquierdo.darElemento();
						this.izquierdo = this.izquierdo.derecho;
						return elem;
					}		
				}else if(!izquierdo.tieneSoloUnSubArbol()){
					T retorno = izquierdo.darElemento();
					T elem = izquierdo.izquierdo.darElemMasDerecho();
					if(comparar(izquierdo.izquierdo.darElemento(), elem) == 0){
						izquierdo.izquierdo = null;
					}
					izquierdo.elemento = elem;
					
					return retorno;
				}
			}else{
				return izquierdo.eliminar(pElemento);
			}
		}
		return null;
	}
	
	private T darElemMasDerecho() {
		if(!esHoja()){
			if(derecho.derecho == null){
				T elem = derecho.darElemento();
				derecho = null;
				return elem;
			}
			else
				return derecho.darElemMasDerecho();
		}else{
			return darElemento();
		}

	}

	private boolean tieneSoloUnSubArbol(){
		if(derecho == null && izquierdo != null)
			return true;
		else if (izquierdo == null && derecho != null)
			return true;
		else
			return false;
	}
	
	public int darAltura(int a) {
		return 0;
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
			return ((Comparable<T>)elem1).compareTo(elem2);
		}else{
			return comparador.compare(elem1, elem2);
		}
	}

}
