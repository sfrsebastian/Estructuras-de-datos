package ListaOrdenada;
/**
 * @author Sebastian
 * @version 1.0
 * @created 01-Feb-2014 6:19:56 PM
 */
public class ListaOrdenada<T extends Comparable <T>> implements IListaOrdenada<T> {
	
	private NodoLista primerNodo;
	private int longitud;


	public ListaOrdenada(){
		primerNodo = null;
		longitud = 0;
	}

	public Object[] darElementos(){
		NodoLista<T> actual = primerNodo;
		Object[] respuesta = new Object[longitud];
		for(int i = 0; i<longitud;i++){
			respuesta[i] = actual.darElemento();
			actual = actual.darSiguiente();
		}
		return respuesta;
	}

	@Override
	public T agregar(T elemento) {
		NodoLista<T> porAgregar = new NodoLista(elemento);
		if(primerNodo == null){
			primerNodo = porAgregar;
		}
		else{
			NodoLista<T> actual = primerNodo;
			while(actual.darSiguiente() != null && porAgregar.darElemento().compareTo(actual.darElemento())>0){
				actual = actual.darSiguiente();
			}
			porAgregar.cambiarSiguiente(actual.darSiguiente());
			actual.cambiarSiguiente(porAgregar);
			longitud++;
		}
		return elemento;
	}

	@Override
	public T buscar(T elemento) {
		NodoLista<T> actual = new NodoLista(elemento);
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

	@Override
	public int darLongitud() {
		return longitud;
	}

	@Override
	public T eliminar(T elemento) {
		if(primerNodo == null){
			return null;
		}
		else if (buscar(elemento) == null){
			return null;
		}
		else{
			NodoLista<T> actual = new NodoLista(elemento);
			while(actual != null){
				if(actual.darSiguiente().darElemento().compareTo(elemento)==0){
					actual.cambiarSiguiente(actual.darSiguiente().darSiguiente());
				}
				actual = actual.darSiguiente();
			}
			longitud--;
			return elemento;
		}
	}
}