package ArbolNArio;

import java.util.Iterator;

public interface IArbolNArio<T> extends Iterable<T>{

	/**
	 * Agrega un elemento al arbol-n-ario
	 * @param elemento El elemento que se quiere agregar
	 * @return boolean TRUE si se agrego, FALSE en caso contrario
	 */
	public boolean agregar(T elemento, T padre);
	
	/**
	 * Busca un elemento en el arbol-n-ario
	 * @param elemento El elemento que se quiere buscar
	 * @return T el elemento econtrado, null en caso contrario
	 */
	public T buscar(T elemento);
	
	/**
	 * Elimina un elemento del arbol-n-ario
	 * @param elemento El elemento que se quiere eliminar
	 * @return T el elemento eliminado, null en caso contrario
	 */
	public boolean eliminar(T elemento);
	
	/**
	 * Itera sobre los elementos del arbol-n-ario
	 * @return Iterator El iterador del arbol
	 */
	public Iterator<T> iterator();
	
	/**
	 * Da la altura del arbol-n-ario
	 * @return int La altura del arbol
	 */
	public int darAltura();
	
	/**
	 * Da el peso del arbol-n-ario
	 * @return int El peso del arbol
	 */
	public int darPeso();
	
	/**
	 * Retorna un arreglo de todos los elementos del arbol
	 * @return
	 */
	public Object[] darArreglo();
}
