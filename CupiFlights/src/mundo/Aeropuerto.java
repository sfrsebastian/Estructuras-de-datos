package mundo;

import java.io.Serializable;
import java.util.Calendar;
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
	 * Vuelos de salida del aeropuerto, organizados por fecha
	 */
	private ArbolTrie<Vuelo> vuelosSalida;
	
	/**
	 * La calificacion del aeropuerto
	 */
	private double calificacion;
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
		calificacion = 0;
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
	 * Retorna la suma de tardanzas de los vuelos que se encuentran entre la fecha dada.
	 * @param c1 La fecha inferior
	 * @param c2 La fecha superior
	 * @return El arreglo con la suma de tardanzas de vuelos entre las fechas dadas
	 */
	public int[] darTardanzasPorFecha(Calendar c1, Calendar c2) {
		int l15 = 0;
		int l30 = 0;
		int l45 = 0;
		int cancelados = 0;
		Iterator <Vuelo> it1 = vuelosEntrada.buscarXPrefijo("");
		Iterator<Vuelo> it2 = vuelosSalida.buscarXPrefijo("");
		while(it1.hasNext()){
			Vuelo actual = it1.next();
			if(actual.getFecha().compareTo(c1)>0 && actual.getFecha().compareTo(c2)<0){
				l15 += actual.getL15();
				l30 += actual.getL30();
				l45 += actual.getL45();
				cancelados += actual.getCancelados();
			}
		}
		while(it2.hasNext()){
			Vuelo actual = it2.next();
			if(actual.getFecha().compareTo(c1)>0 && actual.getFecha().compareTo(c2)<0){
				l15 += actual.getL15();
				l30 += actual.getL30();
				l45 += actual.getL45();
				cancelados += actual.getCancelados();
			}
		}
		int[] respuesta = new int[4];
		respuesta[0] = l15;
		respuesta[1] = l30;
		respuesta[2] = l45;
		respuesta[3] = cancelados;
		return respuesta;
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
	 * Retorna la calificacion del aeropuerto
	 * @return
	 */
	public double getCalificacion(){
		return calificacion;
	}
	
	/**
	 * Cambia la calificacion a la dada por parametro
	 */
	public double setCalificacion(double nCalificacion){
		return calificacion = nCalificacion;
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
