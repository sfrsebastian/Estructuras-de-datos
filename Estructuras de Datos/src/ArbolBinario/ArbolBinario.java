package ArbolBinario;

public class ArbolBinario <T> {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * La raiz del arbol
	 */
	private NodoArbol<T> raiz;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo arbol binario
	 */
	public ArbolBinario(){
		raiz = null;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna la raiz del arbol
	 * @return la raiz del arbol
	 */
	public NodoArbol<T> getRaiz() {
		return raiz;
	}

	/**
	 * Cambia la raiz del arbol
	 * @param nueva la nueva raiz.
	 */
	public void setRaiz(NodoArbol<T> nueva) {
		raiz = nueva;
	}
	
}
