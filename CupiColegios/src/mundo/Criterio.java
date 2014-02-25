package mundo;

import estructuras.Lista;

public class Criterio {
	public
	private String nombre;
	private Lista <String> subcriterios;
	public Criterio(String nNombre){
		nombre = nNombre;
		subcriterios = new Lista<String>();
	}
	
	public void agregarSubcriterio(String nuevo){
		subcriterios.agregar(nuevo);
	}
	
	public String darNombreCriterio(){
		return nombre;
	}
	
	public String eliminarSubcriterio(String eliminar){
		return subcriterios.eliminar(eliminar);
	}
	public Object[] darSubcriterios(){
		return subcriterios.darArreglo();
	}
	                                
}
