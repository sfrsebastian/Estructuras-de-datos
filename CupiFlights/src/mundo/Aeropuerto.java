package mundo;

import java.io.Serializable;
import java.util.Iterator;

import Arbol23.Arbol23;

public class Aeropuerto implements Comparable<Aeropuerto>,Serializable{

	public static final String NOTIENE = "No tiene";
	private String codigoCiudad;
	private String codigoPais;
	private String nombre;
	private String ciudad;
	private String pais;
	private double tardanza;
	private String latitud;
	private String longitud;
	/**
	 * Codigo flightstats
	 */
	private String codigo;
	
	private Arbol23<Vuelo> vuelosEntrada;
	
	private Arbol23<Vuelo> vuelosSalida;
	public Aeropuerto(String nNombre, String nCiudad,String nPais, String nCodigoCiudad,String nCodigoPais,String nCodigo, double nTardanza,String nLatitud, String nLongitud){
		nombre = nNombre;
		ciudad = nCiudad;
		pais = nPais;
		codigoCiudad = nCodigoCiudad;
		codigoPais = nCodigoPais;
		codigo = nCodigo;
		tardanza = nTardanza;
		vuelosEntrada = new Arbol23<Vuelo>();
		vuelosSalida = new Arbol23<Vuelo>();
		latitud = nLatitud;
		longitud = nLongitud;
	}
	
	public Aeropuerto(String nCodigo){
		codigo = nCodigo;
	}
	
	public boolean agregarVueloEntrada(Vuelo vuelo) throws Exception{
		return vuelosEntrada.agregar(vuelo);
	}
	
	public boolean agregarVueloSalida(Vuelo vuelo) throws Exception{
		return vuelosSalida.agregar(vuelo);
	}
	public Iterator<Vuelo> darVuelosEntrada(){
		return vuelosEntrada.iterator();
	}
	
	public Iterator<Vuelo> darVuelosSalida(){
		return vuelosSalida.iterator();
	}
	
	public Arbol23<Vuelo> getVuelosEntrada(){
		return vuelosEntrada;
	}
	
	public Arbol23<Vuelo> getVuelosSalida(){
		return vuelosSalida;
	}
	public String getCodigoCiudad() {
		return codigoCiudad;
	}


	public String getCodigoPais() {
		return codigoPais;
	}


	public String getNombre() {
		return nombre;
	}


	public String getCiudad() {
		return ciudad;
	}


	public String getPais() {
		return pais;
	}


	public double getTardanza() {
		return tardanza;
	}


	public String getCodigo() {
		return codigo;
	}

	public String getLatitud(){
		return latitud;
	}
	public String getLongitud(){
		return longitud;
	}
	
	public String toString(){
		return nombre;
	}
	@Override
	public int compareTo(Aeropuerto o) {
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
