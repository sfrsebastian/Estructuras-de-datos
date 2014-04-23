package ArbolTrie;

import java.util.Iterator;

public interface IArbolTrie<T> {
	
	/**
	 * Agrega un nuevo elemento al arbol trie dada su palabra
	 * @param palabara La palabra por la que se quiere agregar
	 * @param elemento El elemento que se quiere agregar
	 * @return TRUE si se pudo agregar, FALSE en caso contrario
	 * POST1: Se ha agregado el elemento si la palabra ya existe
	 * POST2: Se ha creado la palabra si esta aun no existe y agregado el elemento
	 */
	public boolean agregar(String palabara, T elemento);
	
	/**
	 * Agrega varios elementos a la pabra dada por parametro
	 * @param palabra La palabra por la que se quiere agregar
	 * @param elementos Iterador de elementos que se quieren agregar
	 * @return TRUE si se puedieron agregar FALSE en caso contrario
	 * POST1: Se han agregado los elementos si la palabra ya existe
	 * POST2: Se ha creado la palabra si esta aun no existe y agregados los elementos
	 */
	public boolean agregarMultiples(String palabra, Iterator<T> elementos);
	
	/**
	 * Busca todos los elementos del nodo dada la palabra
	 * @param palabra La palabra por la que se quiere buscar
	 * @return Un iterador con los elementos de esa palabra, NULL en caso contrario
	 */
	public Iterator<T> buscar(String palabra);
	
	/**
	 * Busca por la palabra dada si existe el elemento dado por parametro
	 * @param palabra La palabra por la que se quiere buscar
	 * @param elemento El elemento en la palabra
	 * @return T el elemento encontrado, NULL en caso contrario
	 */
	public T buscarElemento(String palabra, T elemento);
	
	/**
	 * Elimina la palabra con todos los elementos de esta
	 * @param palabra La palabra por la que se quiere eliminar
	 * @return TRUE si se pudo eliminar FALSE en caso contrario
	 * POST1: Si es una palabra "hoja" se elimina del arbol de lo 
	 * contrario, solo se eliminan sus elementos
	 */
	public boolean eliminar(String palabra);
	
	/**
	 * Busca todos los elementos que esten bajo cierto prefijo
	 * @param palabra La palabra por la que se quiere buscar
	 * @return Un iterador de elementos de la palabara (prefijo)
	 */
	public Iterator<T> buscarXPrefijo(String palabra);
	
	/**
	 * Verifica si el arbol trie contiene el prefijo dado por parametro
	 * @param prefijo El prefijo que se quiere verficar
	 * @return TRUE si lo contiene, FALSE en caso contrario
	 */
	public boolean contienePrefijo(String prefijo);
}
