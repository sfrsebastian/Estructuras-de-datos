package mundo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

public interface ICentralDeVuelos extends Serializable{
	
	/**
	 * Agrega el aeropuerto con el codigo dado por parametro a la central<b>
	 * Agrega los vuelos hacia los demas aeropuertos en distintas fechas
	 * @param codigo El codigo del aeropuerto
	 * @return El aeropuerto agregado
	 * @throws Exception
	 */
	public Aeropuerto agregarAeropuerto(String codigo) throws Exception;
	
	/**
	 * Elimina el aeropuerto con codigo dado por parametro<b>
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
	 * @return TRUE si se actualizo,FALSE de lo contrario
	 */
	public boolean ActualizarCalificacion(String codigo, double valor);
	
	/**
	 * Retorna las fechas contenidas en la central.
	 * @return Iterador de fechas
	 */
	public Iterator<Fecha> consultarFechas();
	
	/**
	 * Retorna los aeropuertos de la central
	 * @return Iterador de aereopuertos
	 */
	public Iterator<Aeropuerto> darAeropuertos();
	
	/**
	 * Consulta los vuelos en la fecha y hora dada del aeropuerto con codigo y tipo dados.
	 * @param c Contenedor de la fecha.
	 * @param codigo El codigo del aereopuerto
	 * @param tipo El tipo de vuelos a consultar. Vuelo.LLEGANDO || Vuelo.SALIENDO.
	 * @param nHora La hora de la busqueda.
	 * @return Iterador de vuelos que cumplen con los criterios
	 * @throws Exception
	 */
	public Iterator<Vuelo> consultarVuelos(Calendar c, String codigo, String tipo,int nHora) throws Exception;
	
	/**
	 * Consulta los vuelos del aeropuerto segun el tipo especificado.
	 * @param tipo El tipo de vuelo. Vuelo.LLEGANDO || Vuelo.SALIENDO
	 * @param codigo El codigo del aereopuerto.
	 * @return Iterador de vuelos.
	 */
	public Iterator<Vuelo> consultarVuelosPorEstado(String tipo,String codigo);
	
	/**
	 * Retorna los vuelos en el rango de la calificacion dada.
	 * @param calificacion La calificacion de 1-5
	 * @return Iterador de vuelos.
	 */
	public Iterator<Vuelo> buscarVuelosPorCalificacion(int calificacion);
	
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
