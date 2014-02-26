package HashTable;

public interface ITablaHashing<K,V> extends Iterable<V> {

	/**
	 * Agrega un nuevo elemento dada su llave y el elemento
	 * @param nLlave La llave que se quiere utilizar
	 * @param nElemento El elemento que se quiere agregar
	 * @return TRUE si se agrego, FALSE en caso contrario
	 */
	public boolean agregar(K nLlave, V nElemento);

	/**
	 * Busca un elemento dada su llave
	 * @param nLlave La llave por la cual se quiere buscar el elemento
	 * @return El elemento buscado, null en caso contrario
	 */
	public V buscar(K nLlave);

	/**
	 * Elimina un objeto dada su llave 
	 * @param nLlave La llave por la cual se quiere eliminar
	 * @return El elemento eliminado, null en caso contrario
	 */
	public V eliminar(K nLlave);

}
