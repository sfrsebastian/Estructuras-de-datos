package mundo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Vuelo implements Serializable,Comparable<Vuelo> {
	//--------------------
	//CONSTANTES
	//--------------------
	/**
	 * Identificador de la clase
	 */
	private static final long serialVersionUID = 7370494462035777075L;

	/**
	 * Constante que indica si el vuelo esta saliendo
	 */
	public final static String SALIENDO = "Saliendo";

	/**
	 * Constante que indica si el vuelo esta llegando
	 */
	public final static String LLEGANDO = "Llegando";

	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Numero identificador del vuelo
	 */
	private String numero;

	/**
	 * Tipo del vuelo<br>
	 * tipo = SALIENDO || tipo = LLEGANDO
	 */
	private String tipo;

	/**
	 * Rating del vuelo<br>
	 * rating: 1-5
	 */
	private double rating;

	/**
	 * Aeropuerto de salida del vuelo
	 */
	private Aeropuerto salida;

	/**
	 * Aeropuerto de llegada del vuelo
	 */
	private Aeropuerto llegada;

	/**
	 * Aerolinea del vuelo
	 */
	private Aerolinea aerolinea;

	/**
	 * Cantidad de vuelo con tardanza de 15 minutos<br>
	 * La cantidad es medida a partir de los vuelos en fechas anteriores con mismo numero de vuelo
	 */
	private int l15;

	/**
	 * Cantidad de vuelo con tardanza de 30 minutos<br>
	 * La cantidad es medida a partir de los vuelos en fechas anteriores con mismo numero de vuelo
	 */
	private int l30;

	/**
	 * Cantidad de vuelo con tardanza de 45 minutos<br>
	 * La cantidad es medida a partir de los vuelos en fechas anteriores con mismo numero de vuelo
	 */
	private int l45;

	/**
	 * Cantidad de vuelos cancelados<br>
	 * La cantidad es medida a partir de los vuelos en fechas anteriores con mismo numero de vuelo
	 */
	private int cancelados;
	
	/**
	 * La duracion del vuelo
	 */
	private float duracion;

	/**
	 * Fecha del vuelo
	 */
	private Calendar fecha;

	/**
	 * Formateador de fecha
	 */
	private SimpleDateFormat dateFormat;

	//--------------------
	//CONSTRUCTORES
	//--------------------
	/**
	 * Crea un nuevo vuelo a partir de los parametros dados.<br>
	 * @param nNumero El numero de vuelo
	 * @param nSalida El aeropuerto de salida
	 * @param nLlegada El aeropuerto de llegada
	 * @param nAereolinea La aerolinea del vuelo
	 * @param nTipo El tipo de vuelo
	 * @param nRating El rating del vuelo contiene indices de tardanza
	 * @param c fecha del vuelo
	 */
	public Vuelo(String nNumero, Aeropuerto nSalida, Aeropuerto nLlegada, Aerolinea nAereolinea, String nTipo, Object[] nRating,float nDuracion, Calendar c) {
		fecha = c;
		numero = nNumero;
		salida = nSalida;
		llegada = nLlegada;
		tipo = nTipo;
		aerolinea = nAereolinea;
		rating = (double) nRating[0];
		l15 = (int) nRating[1];
		l30 = (int) nRating[2];
		l45 = (int) nRating[3];
		duracion = nDuracion;
		cancelados = (int) nRating[4];	
		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	}

	/**
	 * Crea un nuevo vuelo a partir del codigo dado por parametro.
	 * @param nNumero
	 */
	public Vuelo(String nNumero){
		numero = nNumero;
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Retorna el aeropuerto de salida del vuelo
	 * @return El aeropuerto de salida
	 */
	public Aeropuerto getSalida() {
		return salida;
	}

	/**
	 * Retorna el aeropuerto de llegada del vuelo
	 * @return El aeropuerto de llegada
	 */
	public Aeropuerto getLlegada() {
		return llegada;
	}

	/**
	 * Retorna la aerolinea del vuelo
	 * @return La aerolinea del vuelo
	 */
	public Aerolinea getAereolinea() {
		return aerolinea;
	}

	/**
	 * Retorna el numero del vuelo
	 * @return El numero de vuelo
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Retorna el tipo de vuelo
	 * @return El tipo de vuelo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna el rating del vuelo
	 * @return El rating del vuelo
	 */
	public double getRating(){
		return rating;
	}

	/**
	 * Actualiza el rating del vuelo<br>
	 * Se promedia el rating actual con el recibido por parametro
	 * @param nRating 
	 * @return El nuevo rating del vuelo
	 */
	public double actualizarRating(double nRating){
		return rating = (rating+nRating)/2;
	}

	/**
	 * Retorna la cantidad de vuelos con tardanza de 15 minutos
	 * @return La cantidad de vuelos
	 */
	public int getL15() {
		return l15;
	}

	/**
	 * Retorna la cantidad de vuelos con tardanza de 30 minutos
	 * @return La cantidad de vuelos
	 */
	public int getL30() {
		return l30;
	}

	/**
	 * Retorna la cantidad de vuelos con tardanza de 45 minutos
	 * @return La cantidad de vuelos
	 */
	public int getL45() {
		return l45;
	}

	/**
	 * Retorna la cantidad de vuelos cancelados
	 * @return La cantidad de vuelos
	 */
	public int getCancelados() {
		return cancelados;
	}

	/**
	 * Retorna la duracion del vuelo en minutos
	 * @return
	 */
	public float getDuracion(){
		return duracion;
	}
	
	/**
	 * Retorna la distanci que se viaja entre los dos aeropuertos del vuelo
	 * @return La distancia del vuelo
	 */
	public float getDistancia() {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(Double.parseDouble(llegada.getLatitud())-Double.parseDouble(salida.getLatitud()));
		double dLng = Math.toRadians(Double.parseDouble(llegada.getLongitud())-Double.parseDouble(salida.getLongitud()));
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(Double.parseDouble(salida.getLatitud()))) * Math.cos(Math.toRadians(Double.parseDouble(llegada.getLatitud()))) * Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;
		double KmConversion = 1.609;
		return (float) (dist * KmConversion);
	}
	
	/**
	 * Retorna la cantidad de vuelos tardios
	 * @return
	 */
	public float getTardios(){
		return l15+l30+l45;
	}
	
	/**
	 * Retorna la fecha del vuelo
	 * @return La fecha del vuelo
	 */
	public Calendar getFecha(){
		return fecha;
	}

	/**
	 * Retorna la fecha formateada
	 * @return La fecha del vuelo
	 */
	public String getFechaString(){
		return dateFormat.format(fecha.getTime());
	}

	/**
	 * Metodo toString del vuelo<br>
	 * Retorna el numero de vuelo y nombre de la aerolinea
	 */
	public String toString(){
		return numero + " " + aerolinea.toString();
	}

	/**
	 * Metodo que compara dos vuelos a partir del numero de vuelo
	 */
	public int compareTo(Vuelo o) {
		int comparacion = numero.compareTo(o.numero);
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
