package Cola;

public class NodoCola<T> {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El elemento del nodo
	 */
	private T elemento;
	
	/**
	 * El nodo siguiente
	 */
	private NodoCola<T> siguiente;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo nodo cola con el elemento dado de parametro
	 * @param pElemento el elemento del nodo
	 */
	public NodoCola(T pElemento) {
		elemento = pElemento;
		siguiente = null;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna el elemento del nodo
	 * @return
	 */
	public T darElemento() {
		return elemento;
	}

	/**
	 * Retorna el siguiente nodo
	 * @return el siguiente nodo
	 */
	public NodoCola<T> darSiguiente() {
		return siguiente;
	}
	
	/**
	 * Cambia el siguiente nodo por el dado por parametro
	 * @param nuevo El nodo a cambiar el siguiente.
	 */
	public void cambiarSiguiente(NodoCola<T> nuevo){
		siguiente = nuevo;
	}
}
