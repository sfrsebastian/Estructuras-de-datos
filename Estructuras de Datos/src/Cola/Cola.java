package Cola;

import java.io.Serializable;
import java.util.NoSuchElementException;
public class Cola<T> implements Serializable,ICola<T>{
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El primer nodo de la cola
	 */
	private NodoCola<T> primero;
	
	/**
	 * El ultimo nodo de la cola
	 */
	private NodoCola<T> ultimo;
	
	private int longitud;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea una nueva cola vacia.
	 */
	public Cola(){
		primero = null;
		ultimo = null;
		longitud = 0;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna el primer elemento de la cola
	 * @return el primer elemento de la cola
	 * @throws NoSuchElementException En caso de que no haya elementos por retornar
	 */
	public T dar() throws NoSuchElementException{
		if(primero != null){
			T elemento = primero.darElemento();
			primero = primero.darSiguiente();
			longitud--;
			return elemento;
		}
		else{
			throw new NoSuchElementException("No hay elemento");
		}
	}
	
	/**
	 * Agrega un nuevo elemento al final de la cola
	 * @param pElemento el elemento a agregar a la cola
	 */
	public void agregar(T pElemento){
		NodoCola<T> nuevo = new NodoCola<T>(pElemento);
		if(primero == null){
			primero = nuevo;
		}
		
		if(ultimo == null){
			ultimo = nuevo;
		}
		else{
			ultimo.cambiarSiguiente(nuevo);
			ultimo = nuevo;
		}
		longitud++;
	}
	
	public int darLongitud(){
		return longitud;
	}
}
