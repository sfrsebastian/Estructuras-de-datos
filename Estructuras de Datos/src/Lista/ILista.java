package Lista;

public interface ILista<T> {
	
	/**
	 * Agrega un nuevo elemento al final de la lista
	 * @param elemento El elemento para agregar de tipo T
	 * @return True en caso de exito, False de lo contrario.
	 */
	public T agregar(T elemento);
	
	/**
	 * Busca el elemento de tipo T en la lista
	 * @param elemento El elemento a buscar
	 * @return El elemento encontrado de tipo T, null en caso contrario
	 */
	public T buscar(T elemento);
	
	/**
	 * Convierte todos los elementos de la lista a un arreglo de tipo Objecto
	 * @return Arreglo con los elementos de la lista
	 */
	public Object[] darArreglo();
	
	/**
	 * Da la longitud total de la lista, la cuenta de todos los elementos
	 * @return int Tamano de la lista
	 */
	public int darLongitud();
	
	/**
	 * Elimina el elemento de tipo T de la lista
	 * @param elemento El elemento que se quiere eliminar
	 * @return El elemento eliminado, null en caso contrario
	 */
	public T eliminar(T elemento);
}
