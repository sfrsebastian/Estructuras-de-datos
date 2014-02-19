package ListaOrdenada;

/**
 * @author Sebastian
 * @version 1.0
 * @created 01-Feb-2014 6:16:04 PM
 */
public interface IListaOrdenada<T> {

	/**
	 * 
	 * @param o
	 */
	public T agregar(T elemento);

	/**
	 * 
	 * @param o
	 */
	public T buscar(T elemento);

	public Object[] darElementos();

	public int darLongitud();

	/**
	 * 
	 * @param o
	 */
	public T eliminar(T elemento);



}