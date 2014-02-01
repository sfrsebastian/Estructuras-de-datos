package Lista;

import java.lang.reflect.Array;

public class Lista<T extends Comparable<T>> implements ILista<T> {

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
	private NodoLista primero;
	
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
	

	@Override
	public int darLongitud() {
		return longitud;
	}

	@Override
	public boolean agregar(T elemento) {
		boolean agrego = false;
		NodoLista<T> actual = primero;
		if(primero == null){
			NodoLista<T> aAgregar = new NodoLista<T>(elemento);
			primero = aAgregar;
			agrego = true;
			longitud++;
		}else{
			while(actual != null && !agrego){
				if(actual.darSiguiente() == null){
					NodoLista<T> aAgregar = new NodoLista<T>(elemento);
					actual.cambiarSiguiente(aAgregar);
					agrego = true;
					longitud++;
				}else{
					actual = actual.darSiguiente();
				}
			}
		}
		return agrego;
	}

	@Override
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

	@Override
	public T eliminar(T elemento) {
		NodoLista<T> actual = primero;

		if(elemento.compareTo(actual.darElemento()) == 0){
			primero = actual.darSiguiente();
			longitud--;
			return actual.darElemento();
		}else{
			while(actual != null && actual.darSiguiente() != null){
				if(elemento.compareTo(actual.darSiguiente().darElemento()) == 0){
					longitud--;
					return actual.desconectarSiguiente().darElemento();
				}else{
					actual = actual.darSiguiente();
				}
			}
		}
		return null;
	}

	@Override
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

}
