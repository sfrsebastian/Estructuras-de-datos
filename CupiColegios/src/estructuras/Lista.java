package estructuras;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Lista<T extends Comparable<?super T>> implements ILista<T>, Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * La longitud total de la lista, la cuenta de todos sus elementos
	 */
	private int longitud;
	
	/**
	 * El primero nodo de la lista
	 */
	private NodoLista<T> primero;
	
	/**
	 * El ultimo nodo de la lista
	 */
	private NodoLista<T> ultimo;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------	
	
	/**
	 * Construye una nueva Lista de tipo T 
	 */
	public Lista() {
		longitud = 0;
		primero = null;
		ultimo = null;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	 
	public T agregar(T elemento) {
		if(elemento == null)
			return null;
		
		if(primero == null){
			NodoLista<T> aAgregar = new NodoLista<T>(elemento);
			primero = aAgregar;
			ultimo = primero;
			longitud++;
		}else{
			NodoLista<T> nuevo = new NodoLista<T>(elemento);
			ultimo.cambiarSiguiente(nuevo);
			ultimo = nuevo;
			longitud++;
		}
		return elemento;
	}


	public T buscar(T elemento) {	
		NodoLista<T> actual = primero;
		
		while(actual != null){
			if(elemento.compareTo(actual.darElemento()) == 0){
				//TODO check comparison method
				return actual.darElemento();
			}else {
				actual = actual.darSiguiente();
			}
		}
		return null;
	}

	public Object[] darArreglo() {
		Object[] array = new Object[longitud];
		NodoLista<T> actual = primero;
		
		int i = 0;
		while(actual != null){
			array[i] = actual.darElemento();
			i++;
			actual = actual.darSiguiente();
		}
		
		return array;
	}

	public int darLongitud() {
		return longitud;
	}

	public T darUltimo(){
		return ultimo.darElemento();
	}
	
	public T eliminar(T elemento) {
		NodoLista<T> actual = primero;
		
		if(longitud == 0)
			return null;

		if(elemento.compareTo(actual.darElemento()) == 0){
			primero = actual.darSiguiente();
			longitud--;
			return actual.darElemento();
		}else{
			while(actual != null && actual.darSiguiente() != null){
				if(elemento.compareTo(actual.darSiguiente().darElemento()) == 0){
					if(actual.darSiguiente() == ultimo){
						ultimo = actual;
					}
					longitud--;
					return actual.desconectarSiguiente().darElemento();
				}else{
					actual = actual.darSiguiente();
				}
			}
		}
		return null;
	}

}
