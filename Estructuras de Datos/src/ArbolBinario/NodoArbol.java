package ArbolBinario;

public class NodoArbol <T> {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El elemento del nodo
	 */
	private T elemento;
	
	/**
	 * El nodo izquierdo
	 */
	private NodoArbol<T> izquierdo;
	
	/**
	 * El nodo derecho
	 */
	private NodoArbol<T> derecho;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo nodoarbol
	 * @param nElemento El elemento del nodo.
	 */
	public NodoArbol(T nElemento){
		elemento = nElemento;
		izquierdo = null;
		derecho = null;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna el nodo izquierdo
	 * @return el nodo izquierdo
	 */
	public NodoArbol<T> getIzquierdo() {
		return izquierdo;
	}

	/**
	 * Cambia el nodo izquierdo por el dado por parametro
	 * @param nuevo el nuevo nodo izquierdo
	 */
	public void setIzquierdo(NodoArbol<T> nuevo) {
		izquierdo = nuevo;
	}

	/**
	 * retorna el nodo derecho
	 * @return el nodo derecho
	 */
	public NodoArbol<T> getDerecho() {
		return derecho;
	}
	
	/**
	 * Cambia el nodo derecho por el dado por parametro
	 * @return nuevo el nuevo nodo derecho
	 */
	public void setDerecho(NodoArbol<T> nuevo) {
		derecho = nuevo;
	}

	/**
	 * Retorna el elemento del nodo
	 * @return El elemento del nodo
	 */
	public T getElemento() {
		return elemento;
	}
	
	/**
	 * Indica si el nodo es hoja
	 * @return True si es hoja, false de lo contrario
	 */
	public boolean esHoja(){
		return izquierdo==null && derecho==null;
	}
	
	/**
	 * Metodo To String del nodo Indica valores de sus nodos adyacentes.
	 */
	public String toString(){
		String der = "NO TIENE";
		String izq = "NO TIENE";
		if(derecho != null){
			try {
				der = derecho.getElemento().toString();
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				der = "TIENE";
			}
		}
		if(izquierdo != null){
			try {
				izq = izquierdo.getElemento().toString();
			} catch (Exception e) {
				System.out.println("Agregar el metodo toString() del elemento");
				izq = "TIENE";
			}
		}
		return "Soy: " + elemento.toString() + ", derecho: " + der + " izquierdo: " + izq;
	}
}
