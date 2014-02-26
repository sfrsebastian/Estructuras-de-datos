package mundo;

import Lista.Lista;

public class Criterio {
	public final static String DIURNA = "Diurna";
	public static final String NOCTURNA = "Nocturna";
	public static final String MUY_SUPERIOR = "Muy Superior";
	public final static String SUPERIOR = "Superior";
	public final static String ALTO = "Alto";
	public final static String BAJO = "Bajo";
	public final static String MEDIO = "Medio";
	public final static String INFERIOR = "Inferior";
	public final static String MUY_INFERIOR = "Muy Inferior";
	public final static String MASCULINO = "Masculino";
	public final static String FEMENINO = "Femenino";
	public final static String MIXTO = "Mixto";
	public final static String CA = "A";
	public final static String CB = "B";
	public final static String CF = "F";
	public final static String PRIVADO = "Privado";
	public final static String PUBLICO = "Publico";
	private Lista <String> subcriterios;
	public Criterio(){
		subcriterios = new Lista<String>();
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
