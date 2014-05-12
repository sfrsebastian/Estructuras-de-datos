package ListaOrdenada;

import java.io.Serializable;

import Lista.Lista;
import Lista.NodoLista;

/**
 * @author Sebastian Florez
 * @version 1.0
 * @created 01-Feb-2014 6:19:56 PM
 */
public class ListaOrdenada<T extends Comparable <?super T>> extends Lista<T> implements Serializable {
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Metodo constructor de la lista ordenada<br>
	 * El primer nodo es inicializado a null y la longitud en 0
	 */
	public ListaOrdenada(){
		super();
	}

	/**
	 * Agrega el elemento dado por parametro <br>
	 * @param El objeto a agregar
	 * Pre: El primer nodo debe estar inicializado<br>
	 * Pos: La longitud de la lista aumenta en una unidad y se agrega el nuevo elemento a la lista.
	 * @return El elemento agregado a la lista, null si se da alguna falla.
	 */
	public T agregar(T elemento) {
		NodoLista<T> porAgregar = new NodoLista<T>(elemento);
		if(elemento == null){
			return null;
		}
		else if(primero == null){
			primero = porAgregar;
		}
		else if(porAgregar.darElemento().compareTo(primero.darElemento())<0){
			porAgregar.cambiarSiguiente(primero);
			primero.cambiarAnterior(porAgregar);
			primero = porAgregar;
		}
		else{
			NodoLista<T> actual = primero;
			while(actual.darSiguiente() != null && elemento.compareTo(actual.darSiguiente().darElemento())>0){
				actual = actual.darSiguiente();
			}
			if(actual.darSiguiente() == null){
				porAgregar.cambiarAnterior(actual);
				actual.cambiarSiguiente(porAgregar);
			}
			else{
				porAgregar.cambiarSiguiente(actual.darSiguiente());
				actual.darSiguiente().cambiarAnterior(porAgregar);
				actual.cambiarSiguiente(porAgregar);
				porAgregar.cambiarAnterior(actual);
			}
		}
		longitud++;
		return elemento;
	}
	
	/**
	 * Retorna el primer elemento de la lista<br>
	 * Elimina el elemento de la lista.
	 * @return
	 */
	public T darPrimero(){
		T respuesta = primero.darElemento();
		primero = primero.darSiguiente();
		primero.cambiarAnterior(null);
		return respuesta;
	}
}