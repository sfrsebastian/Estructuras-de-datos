package mundo;

import java.io.Serializable;

import Arbol23.Arbol23;

public class Aerolinea implements Comparable<Aerolinea>, Serializable {
	private Arbol23<Vuelo> vuelos;
	private int vuelos15;
	private int vuelos30;
	private int vuelos45;
	private int vuelosCancelados;
	private String codigo;
	private String nombre;
	public Aerolinea(String nCodigo,String nNombre){
		codigo = nCodigo;
		nombre = nNombre;
		vuelos15 = 0;
		vuelos30 = 0;
		vuelos45 = 0;
		vuelosCancelados = 0;
		vuelos = new Arbol23<Vuelo>();
	}
	
	public Aerolinea(String nCodigo){
		codigo = nCodigo;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void agregarVuelo(Vuelo vuelo) throws Exception{
		boolean agregado = vuelos.agregar(vuelo);
		if(agregado){
			vuelos15 += vuelo.getL15();
			vuelos30 += vuelo.getL30();
			vuelos45 += vuelo.getL45();
			vuelosCancelados+=vuelo.getCancelados();
		}
	}

	public void eliminarVuelo(Vuelo actual) throws Exception {
		Vuelo vuelo = vuelos.eliminar(actual); 
		if(vuelo != null){
			vuelos15 -= vuelo.getL15();
			vuelos30 -= vuelo.getL30();
			vuelos45 -= vuelo.getL45();
			vuelosCancelados-=vuelo.getCancelados();
		}
	}

	public int getVuelos15() {
		return vuelos15;
	}

	public int getVuelos30() {
		return vuelos30;
	}

	public int getVuelos45() {
		return vuelos45;
	}

	public int getVuelosCancelados() {
		return vuelosCancelados;
	}
	
	public String toString(){
		return nombre;
	}
	@Override
	public int compareTo(Aerolinea o) {
		int comparacion = codigo.compareTo(o.codigo);
		if(comparacion>0){
			return 1;
		}
		else if(comparacion<0){
			return -1;
		}
		else{
			return 0;
		}
	}
	
}
