package mundo;

import ListaEncadenada.ListaEncadenada;

public class Criterio{
	//Constantes
	public final static String DIURNA = "Diurna";
	public static final String NOCTURNA = "Nocturna";
	public static final String MUY_SUPERIOR = "MUY SUPERIOR";
	public final static String SUPERIOR = "SUPERIOR";
	public final static String ALTO = "ALTO";
	public final static String BAJO = "BAJO";
	public final static String MEDIO = "MEDIO";
	public final static String INFERIOR = "INFERIOR";
	public final static String MUY_INFERIOR = "MUY INFERIOR";
	public final static String MASCULINO = "MASCULINO";
	public final static String FEMENINO = "FEMENINO";
	public final static String MIXTO = "MIXTO";
	public final static String CA = "A";
	public final static String CB = "B";
	public final static String CF = "FLEXIBLE";
	public final static String PRIVADO = "NO OFICIAL";
	public final static String PUBLICO = "OFICIAL";
	
	/**
	 * La lista encadenada de subcriterios.
	 */
	private ListaEncadenada <String> subcriterios;
	
	/**
	 * Crea un nuevo criterio.
	 */
	public Criterio(){
		subcriterios = new ListaEncadenada<String>();
	}
	
	/**
	 * Agrega un nuevo subcriterio al criterio.
	 * @param nuevo
	 */
	public void agregarSubcriterio(String nuevo){
		subcriterios.agregar(nuevo);
	}
	
	/**
	 * Elimina un subcriterio de la lista de subrcrtierios.
	 * @param eliminar
	 * @return
	 */
	public String eliminarSubcriterio(String eliminar){
		return subcriterios.eliminar(eliminar);
	}
	
	/**
	 * Retorna un arreglo con los subcreiterios.
	 */
	public Object[] darSubcriterios(){
		return subcriterios.darArreglo();
	}
	                                
}
