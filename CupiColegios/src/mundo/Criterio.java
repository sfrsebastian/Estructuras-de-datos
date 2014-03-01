package mundo;

import ListaEncadenada.ListaEncadenada;

public class Criterio{
	public final static String DIURNA = "Diurna";
	public static final String NOCTURNA = "Nocturna";
	public static final String MUY_SUPERIOR = "Muy Superior";
	public final static String SUPERIOR = "Superior";
	public final static String ALTO = "Alto";
	public final static String BAJO = "Bajo";
	public final static String MEDIO = "Medio";
	public final static String INFERIOR = "Inferior";
	public final static String MUY_INFERIOR = "Muy Inferior";
	public final static String MASCULINO = "MASCULINO";
	public final static String FEMENINO = "FEMENINO";
	public final static String MIXTO = "MIXTO";
	public final static String CA = "A";
	public final static String CB = "B";
	public final static String CF = "FLEXIBLE";
	public final static String PRIVADO = "NO OFICIAL";
	public final static String PUBLICO = "OFICIAL";
	private ListaEncadenada <String> subcriterios;
	public Criterio(){
		subcriterios = new ListaEncadenada<String>();
	}
	
	public void agregarSubcriterio(String nuevo){
		subcriterios.agregar(nuevo);
	}
	
	public String eliminarSubcriterio(String eliminar){
		return subcriterios.eliminar(eliminar);
	}
	public Object[] darSubcriterios(){
		return subcriterios.darArreglo();
	}
	                                
}
