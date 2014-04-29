package mundo;

import java.io.Serializable;
import java.util.Iterator;

import ArbolTrie.ArbolTrie;

public class Aeropuerto implements Comparable<Aeropuerto>,Serializable{
	//--------------------
	//CONSTANTES
	//--------------------
	/**
	 * Serial identificador de la clase
	 */
	private static final long serialVersionUID = -1054162308338049538L;

	/**
	 * Constante que indica que no se tiene codigo de ciudad
	 */
	public static final String NOTIENE = "No tiene";

	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * El codigo de la ciudad del aeropuerto
	 */
	private String codigoCiudad;

	/**
	 * Codigo del pais del aeropuerto
	 */
	private String codigoPais;

	/**
	 * Nombre del aeropuerto
	 */
	private String nombre;

	/**
	 * Nombre de la ciudad del aeropuerto
	 */
	private String ciudad;

	/**
	 * Nombre del pais del aeropuerto
	 */
	private String pais;

	/**
	 * Indice de tardanza del aeropuerto
	 */
	private double tardanza;

	/**
	 * Latitud del aeropuerto
	 */
	private String latitud;

	/**
	 * Longitud del aeropuerto
	 */
	private String longitud;

	/**
	 * Codigo flightstats identificador
	 */
	private String codigo;

	/**
	 * Vuelos de entrada del aeropuerto, organizados por fecha
	 */
	private ArbolTrie<Vuelo> vuelosEntrada;

	/**
	 * vuelos de salida del aeropuerto, organizados por fecha
	 */
	private ArbolTrie<Vuelo> vuelosSalida;

	//--------------------
	//CONSTRUCTORES
	//--------------------
	/**
	 * Crea un nuevo aeropuerto con los parametros dados.<br>
	 * Se inicializan vacias las contenedoras de vuelos.
	 * @param nNombre El nombre del aeropuerto
	 * @param nCiudad La ciudad del aeropuerto
	 * @param nPais El pais del aeropuerto
	 * @param nCodigoCiudad El codigo de ciudad del aeropuerto
	 * @param nCodigoPais El codigo de pais del aeropuerto
	 * @param nCodigo El codigo identificador del aeropuerto
	 * @param nTardanza El indice de tardanza del aeropuerto
	 * @param nLatitud La latitud del aeropuerto
	 * @param nLongitud La longitud del aeropuerto
	 */
	public Aeropuerto(String nNombre, String nCiudad,String nPais, String nCodigoCiudad,String nCodigoPais,String nCodigo, double nTardanza,String nLatitud, String nLongitud){
		nombre = nNombre;
		ciudad = nCiudad;
		pais = nPais;
		codigoCiudad = nCodigoCiudad;
		codigoPais = nCodigoPais;
		codigo = nCodigo;
		tardanza = nTardanza;
		vuelosEntrada = new ArbolTrie<Vuelo>();
		vuelosSalida = new ArbolTrie<Vuelo>();
		latitud = nLatitud;
		longitud = nLongitud;
	}

	/**
	 * Crea un aeropuerto a partir del codigo dado de parametro
	 * @param nCodigo
	 */
	public Aeropuerto(String nCodigo){
		codigo = nCodigo;
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Agrega el vuelo dado por parametro con la fecha indicada al arbol de entradas.
	 * @param fecha La fecha del vuelo
	 * @param vuelo El vuelo a agregar
	 * @return TRUE si agrego, FALSE de lo contrario
	 * @throws Exception
	 */
	public boolean agregarVueloEntrada(String fecha ,Vuelo vuelo) throws Exception{
		return vuelosEntrada.agregar(fecha, vuelo);
	}

	/**
	 * Agrega el vuelo dado por parametro con la fecha indicada al arbol de salidas.
	 * @param fecha La fecha del vuelo
	 * @param vuelo El vuelo a agregar
	 * @return TRUE si agrego, FALSE de lo contrario
	 * @throws Exception
	 */
	public boolean agregarVueloSalida(String fecha, Vuelo vuelo) throws Exception{
		return vuelosSalida.agregar(fecha, vuelo);
	}

	/**
	 * Retorna un iterador con los vuelos que entran al aeropuerto
	 * @return Iterador de vuelos
	 */
	public Iterator<Vuelo> darVuelosEntrada(){
		return vuelosEntrada.buscarXPrefijo("");
	}

	/**
	 * Retorna un iterador con los vuelos que salen del aeropuerto
	 * @return Iterador de vuelos
	 */
	public Iterator<Vuelo> darVuelosSalida(){
		return vuelosSalida.buscarXPrefijo("");
	}

	/**
	 * Retorna los vuelos de entrada del aeropuerto
	 * @return Arbol trie con los vuelos
	 */
	public ArbolTrie<Vuelo> getVuelosEntrada(){
		return vuelosEntrada;
	}

	/**
	 * Retorna los vuelos de salida del arbol
	 * @return Arbol trie con los vuelos
	 */
	public ArbolTrie<Vuelo> getVuelosSalida(){
		return vuelosSalida;
	}

	/**
	 * Retorna el codigo de ciudad del aeropuerto
	 * @return El codigo del aeropuerto
	 */
	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	/**
	 * Retorna el codigo de pais del aeropuerto
	 * @return El codigo del pais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Retorna el nombre del aeropuerto
	 * @return El nombre del aeropuerto
	 */
	public String getNombre() {
		return nombre;
	}


	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Retorna el nombre del pais del aeropuerto
	 * @return El pais del aeropuerto
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Retorna el indice de tardanza del aeropuerto
	 * @return El indice de tardanza
	 */
	public double getTardanza() {
		return tardanza;
	}

	/**
	 * Retorna el codigo del aeropuerto
	 * @return El codigo del aeropuerto
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna la latitud del aeropuerto
	 * @return La latitud del aeropuerto
	 */
	public String getLatitud(){
		return latitud;
	}

	/**
	 * Retorna la longitud del aeropeurto
	 * @return La longitud del aeropuerto
	 */
	public String getLongitud(){
		return longitud;
	}

	/**
	 * Metodo toString del aeropuerto<br>
	 * Retorna el nombre del aeropuerto
	 */
	public String toString(){
		return nombre;
	}

	/**
	 * Metodo de comparacion de dos aeropuertos a partir de su codigo
	 */
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
