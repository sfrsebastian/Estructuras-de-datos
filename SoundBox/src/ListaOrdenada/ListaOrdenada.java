package ListaOrdenada;
/**
 * @author Sebastian Florez
 * @version 1.0
 * @created 01-Feb-2014 6:19:56 PM
 */
public class ListaOrdenada<T extends Comparable <T>> implements IListaOrdenada<T> {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El primer nodo de la lista ordenada
	 */
	private NodoLista<T> primerNodo;
	
	/**
	 * La longitud de la lista ordenada
	 */
	private int longitud;

	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Metodo constructor de la lista ordenada<br>
	 * El primer nodo es inicializado a null y la longitud en 0
	 */
	public ListaOrdenada(){
		primerNodo = null;
		longitud = 0;
	}

	//------------------------------------------
	// Metodos
	//------------------------------------------
	/**
	 * Retorna los objetos de la lista ordenada
	 * @return El arreglo con todos los elementos en la lista.
	 */
	public Object[] darElementos(){
		NodoLista<T> actual = primerNodo;
		Object[] respuesta = new Object[longitud];
		for(int i = 0; i<longitud;i++){
			respuesta[i] = actual.darElemento();
			actual = actual.darSiguiente();
		}
		return respuesta;
	}

	/**
	 * Agrega el elemento dado por parametro <br>
	 * @param El objeto a agregar
	 * Pre: El primer nodo debe estar inicializado<br>
	 * Pos: La longitud de la lista aumenta en una unidad y se agrega el nuevo elemento a la lista.
	 */
	public T agregar(T elemento) {
		NodoLista<T> porAgregar = new NodoLista<T>(elemento);
		if(primerNodo == null){
			primerNodo = porAgregar;
		}
		else if(porAgregar.darElemento().compareTo(primerNodo.darElemento())<0){
			porAgregar.cambiarSiguiente(primerNodo);
			primerNodo = porAgregar;
		}
		else{
			NodoLista<T> actual = primerNodo;
			while(actual.darSiguiente() != null && porAgregar.darElemento().compareTo(actual.darSiguiente().darElemento())>0){
				actual = actual.darSiguiente();
			}
			porAgregar.cambiarSiguiente(actual.darSiguiente());
			actual.cambiarSiguiente(porAgregar);
		}
		longitud++;
		return elemento;
	}

	/**
	 * Busca el elemento dado como parametro.
	 * @param El objeto a buscar.
	 * @return El objeto dado por parametro, null de lo contrario.
	 */
	public T buscar(T elemento) {
		NodoLista<T> actual = primerNodo;
		while(actual != null){
			if(actual.darElemento().compareTo(elemento)==0){
				return actual.darElemento();
			}
			else{
				actual = actual.darSiguiente();
			}
		}
		return null;
	}

	/**
	 * Retorna la longitud de la lista.
	 * @return La longitud de la lista.
	 */
	public int darLongitud() {
		return longitud;
	}

	/**
	 * Elimina el elemento dado por parametro de la lista
	 * @param El elemento a eliminar
	 * @return El elemento eliminado, null en caso de no haber sido encontrado.
	 */
	public T eliminar(T elemento) {
		NodoLista<T> actual = primerNodo;
		if(actual !=null && actual.darElemento().compareTo(elemento)==0){
			primerNodo = actual.darSiguiente();
			actual.cambiarSiguiente(null);
			longitud--;
			return elemento;
		}
		else{
			while(actual != null && actual.darSiguiente()!=null){
				NodoLista<T> siguiente = actual.darSiguiente();
				if(siguiente.darElemento().compareTo(elemento)==0){
					longitud--;
					return actual.desconectarSiguiente().darElemento();
				}
				actual = actual.darSiguiente();
			}
		}
		return null;
	}
}