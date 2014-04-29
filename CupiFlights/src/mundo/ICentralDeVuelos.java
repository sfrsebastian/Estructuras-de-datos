package mundo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

public interface ICentralDeVuelos extends Serializable{

	/**
	 * Carga los 50 aeropuertos mas visitados en el mundo.
	 * @throws Exception
	 */
	public void cargarAeropuertos() throws Exception;

	/**
	 * Agrega el aeropuerto con el codigo dado por parametro a la central<br>
	 * Agrega los vuelos hacia los demas aeropuertos en distintas fechas
	 * @param codigo El codigo del aeropuerto
	 * @return El aeropuerto agregado
	 * @throws Exception
	 */
	public Aeropuerto agregarAeropuerto(String codigo) throws Exception;

	/**
	 * Elimina el aeropuerto con codigo dado por parametro<br>
	 * Elimina los vuelos del aeropuerto
	 * @param codigo El codigo del aeropuerto
	 * @return El aeropuerto eliminado
	 * @throws Exception
	 */
	public Aeropuerto eliminarAeropuerto(String codigo) throws Exception;

	/**
	 * Retorna la calificacion del vuelo con codigo dado
	 * @param codigo El codigo del vuelo
	 * @return La calificacion del vuelo
	 */
	public double consultarCalificacion(String codigo);

	/**
	 * Actualiza la calificacion del vuelo con codigo dado.
	 * @param codigo El codigo del vuelo
	 * @param valor El valor de calificacion
	 * @return La nueva calificacion del vuelo.
	 */
	public double actualizarCalificacion(String codigo, double valor);

	/**
	 * Retorna las fechas contenidas en la central.
	 * @return Iterador de fechas
	 */
	public Iterator<String> darFechas();

	/**
	 * Retorna los aeropuertos de la central
	 * @return Iterador de aereopuertos
	 */
	public Iterator<Aeropuerto> darAeropuertos();

	/**
	 * Consulta los vuelos del aeropuerto en la fecha y segun el tipo especificado.
	 * @param tipo El tipo de vuelo. Vuelo.LLEGANDO || Vuelo.SALIENDO
	 * @param codigo El codigo del aereopuerto.
	 * @param c La fecha especificada
	 * @return Iterador de vuelos.
	 * @throws Exception 
	 */
	public Iterator<Vuelo> darVuelosAeropuertoFecha(String codigo, Calendar c, String tipo) throws Exception;

	//TODO Req 8
	/**
	 * Retorna los vuelos en el rango de la calificacion dada.
	 * @param calificacion La calificacion de 1-5
	 * @return Iterador de vuelos.
	 */
	public Iterator<Vuelo> buscarVuelosPorCalificacion(int calificacion,Calendar c1, Calendar c2);

	/**
	 * Retorena la aerolinea con mayor retraso
	 * @return La aerolinea de mayor retraso
	 */
	public Aerolinea darMayorRetraso();

	/**
	 * Retorna la aerolinea de menor retraso
	 * @return El aerolinea de menor retraso
	 */
	public Aerolinea darMenorRetraso();

	/**
	 * Retorna la ruta del mapa con los aeropuertos organizados por indice de tardanza.
	 * @return La ruta del mapa
	 */
	public String darURLMapa();
}
