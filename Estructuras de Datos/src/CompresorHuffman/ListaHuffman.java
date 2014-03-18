package CompresorHuffman;


public class ListaHuffman {
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El primer nodo de la lista
	 */
	private NodoHuffman primero;
	
	/**
	 * La longitud de la lista.
	 */
	private int longitud;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea una nueva listaHuffman
	 * El primer nodo es null y su longitud es cero.
	 */
	public ListaHuffman(){
		primero = null;
		longitud = 0;
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Agrega el nodo dado por parametro a la lista<br>
	 * Se agrega a partir del metodo compareTo de manera ordenada.
	 * @param nuevo el nodo a agregar.
	 */
	public void agregar(NodoHuffman nuevo) {
		if(primero == null){
			primero = nuevo;
		}
		else if(nuevo.compareTo(primero)<0){
			nuevo.cambiarSiguiente(primero);
			primero.cambiarAnterior(nuevo);
			primero = nuevo;
		}
		else{
			NodoHuffman actual = primero;
			while(actual.getSiguiente() != null && nuevo.compareTo(actual)>0){
				actual = actual.getSiguiente();
			}
			if(actual.getSiguiente() == null){
				nuevo.cambiarAnterior(actual);
				actual.cambiarSiguiente(nuevo);
			}
			else{
				nuevo.cambiarSiguiente(actual);
				nuevo.cambiarAnterior(actual.getAnterior());
				actual.getAnterior().cambiarSiguiente(nuevo);
				actual.cambiarAnterior(nuevo);
			}
		}
		longitud++;
	}
	
	/**
	 * Retorna el primer nodo de la lista<br>
	 * Reasigna el primero al siguiente del retornado.
	 * @return el primer nodo de la lista.
	 */
	private NodoHuffman darPrimero(){
		NodoHuffman retornar = primero;
		if(primero != null){
			primero = primero.getSiguiente();
		}
		else{
			primero = null;
		}
		return retornar;
	}
	
	/**
	 * Genera el arbol Huffman a partir de la lista.<br>
	 * Asigna los codigos correspondientes a cada caracter.
	 */
	public void generarArbol(){
		while(longitud > 1){
			NodoHuffman nuevo = new NodoHuffman(darPrimero(),darPrimero());
			longitud-=2;
			agregar(nuevo);
		}
		if(primero != null){
			primero.asignarCodigos();
		}
	}
}
