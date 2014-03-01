package Lista;

import java.io.Serializable;
import java.util.Iterator;

public abstract class Lista<T extends Comparable<?super T>> implements ILista<T>, Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La longitud total de la lista, la cuenta de todos sus elementos
	 */
	protected int longitud;
	
	/**
	 * El primero nodo de la lista
	 */
	protected NodoLista<T> primero;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------	
	
	/**
	 * Construye una nueva Lista de tipo T 
	 */
	public Lista() {
		longitud = 0;
		primero = null;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	public T buscar(T elemento) {	
		NodoLista<T> actual = primero;
		while(actual != null){
			if(elemento.compareTo(actual.darElemento()) == 0){
				return actual.darElemento();
			}
			else {
				actual = actual.darSiguiente();
			}
		}
		return null;
	}

	public T eliminar(T elemento) {
		NodoLista<T> actual = primero;
		if(elemento == null ||longitud == 0 ){
			return null;
		}
		else if(elemento.compareTo(actual.darElemento()) == 0){
			primero = actual.darSiguiente();
			if(primero != null){
				primero.cambiarAnterior(null);
			}
			longitud--;
			return elemento;
		}
		else{
			actual= actual.darSiguiente();
			while(actual != null){
				if(elemento.compareTo(actual.darElemento()) == 0){
					NodoLista<T> anterior = actual.darAnterior();
					NodoLista<T> siguiente = actual.darSiguiente();
					anterior.cambiarSiguiente(siguiente);
					if(siguiente != null){
						siguiente.cambiarAnterior(anterior);
					}
					longitud--;
					return elemento;
				}
				else{
					actual = actual.darSiguiente();
				}
			}
		}
		return null;
	}

	public Object[] darArreglo() {
		Object[] arreglo = new Object[darLongitud()];
		NodoLista<T> actual = primero;
		int i = 0;
		while(actual != null){
			arreglo[i] = actual.darElemento();
			i++;
			actual = actual.darSiguiente();
		}
		return arreglo;
	}

	public int darLongitud() {
		return longitud;
	}
	
	public Iterator<T> iterator() {
		return new IteratorLista<T>(this);
	}
}
