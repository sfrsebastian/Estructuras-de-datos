package Arbol23;

import java.io.Serializable;
import java.util.Iterator;

public interface IArbol23<T> extends Serializable, Iterable<T> {
	/**
	 * Agrega un nuevo elemento al arbol
	 * @param elemento El elemento que se quiere agregar
	 * @return boolean TRUE si se pudo agregar, false en caso contrario
	 * @throws Exception 
	 */
	public boolean agregar(T elemento) throws Exception;
	
	/**
	 * Busca el elemento T dentro del arbol
	 * @param elemento El elemento que se quiere buscar
	 * @return T el elemento encontrado, NULL en caso contrario
	 */
	public T buscar(T elemento);
	
	/**
	 * Elimina un elemento del arbol 
	 * @param elemento El elemento que se quiere eliminar
	 * @return true si elimina correctamente,false de lo contrario.
	 */
	public boolean eliminar(T elemento);
	
	/**
	 * Da el peso del arbol (El numero total de elementos)
	 * @return int El numero de elementos
	 */
	public int darPeso();
	
	/**
	 * Da la altura del arbol binario
	 * @return int La altura del arbol
	 */
	public int darAltura();
	
	/**
	 * Retorna un iterador del arbol binario recorriendolo en inorden
	 * @return Iterator El iterador con el recorrido
	 */
	public Iterator<T> recorrerInorden();
	
//	/**
//	 * Retorna un iterador del arbol binario recorriendolo en preorden
//	 * @return Iterator El iterador con el recorrido
//	 */
//	public Iterator<T> recorrerPreorden();
	
//	/**
//	 * Retorna un iterador del arbol binario recorriendolo en pos-orden
//	 * @return Iterator El iterador con el recorrido
//	 */
//	public Iterator<T> recorrerPosorden();
	
//	/**
//	 * Retorna un iterador del arbol binario recorriendolo por niveles
//	 * @return Iterator El iterador con el recorrido
//	 */
//	public Iterator<T> recorrerNiveles();
	
//	/**
//	 * Retorna un arreglo inorden de elementos
//	 */
//	public T[] darArreglo();
}
