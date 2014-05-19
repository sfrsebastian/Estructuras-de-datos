package mundo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

import Grafo.Camino;

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
	 * Retorna la calificacion del aeropuerto con codigo dado
	 * @param codigo El codigo del aeropuerto
	 * @return La calificacion del aeropuerto
	 */
	public double consultarCalificacionAeropuerto(String codigo);
	
	/**
	 * Actualiza la calificacion del aeropuerto con codigo dado.
	 * @param codigo El codigo del aeropuerto
	 * @param valor El valor de calificacion
	 * @return La nueva calificacion del aeropuerto.
	 */
	public double ActualizarCalificacionAeropuerto(String codigo, double calificacion);
	
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

	/**
	 * Retorna las cantidades de tardanzas acumuladas del aeropeurto con codigo dado
	 * @param codigo El codigo del aeropuerto
	 * @param c1 La fecha de limite inferior
	 * @param c2 La fecha de limite superior
	 * @return Arreglo con los valores acumulados de tardanza.
	 * @throws Exception 
	 */
	public int[] darTardanzasAeropuertoPorFecha(String codigo, Calendar c1, Calendar c2) throws Exception;
	
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
	 * Retorna la ruta del mapa con los aeropuertos y el arbol con los indices de los aeropuertos agregados organizados por indice de tardanza.
	 * @return La ruta del mapa
	 */
	public Object[] darURLMapa();
	
	////////////////////
	//CUPIFLIGHTS 2.0
	///////////////////
	/**
	 * Registra el usuario con los datos dados.
	 * @param nombre El nombre del usuario
	 * @param apellido El apellido del usuario
	 * @param usuario El usuario deseado
	 * @param correo El correo del usuario
	 * @param constrasenia La contrasenia del usuario
	 * @return TRUE si se registra exitosamente, FALSE de lo contrario
	 */
	public boolean registrarUsuario(String nombre, String apellido, String usuario, String correo, String constrasenia);
	
	/**
	 * Autentica al usuario para darle ingreso al sistema.
	 * @param usuario El usuario de ingreso
	 * @param contrasenia La contrasenia del usuario
	 * @return El usuario autenticado, NULL de lo contrario.
	 */
	public Usuario ingresar(String usuario, String contrasenia);
	
	////////
	//Requerimiento 3
	////////
	/**
	 * Agrega el aeropuerto con codigo dado a las preferencias del usuario
	 * @param codigoAeropuerto El codigo del aeropuerto
	 * @return El aeropuerto agregado
	 */
	public Aeropuerto agregarAeropuertoUsuario(String codigoAeropuerto);
	
	/**
	 * Elimina el aeropuerto con codigo dado de las preferencias del usuario
	 * @param codigoAeropuerto El codigo del aeropuerto
	 * @return El aeropuerto eliminado, NULL de lo contrario
	 */
	public Aeropuerto eliminarAeropuertoUsuario(String codigoAeropuerto);
	/**
	 * Agrega la aerolinea con codigo dado a las preferencias del usuario.
	 * @param codigoAerolinea El codigo de la aerolinea a agregar
	 * @return La aerolinea agregada
	 */
	public Aerolinea agregarAerolineaUsuario(String codigoAerolinea);
	
	/**
	 * Elimina la aerolinea con codigo dado de las preferencias del usuario.
	 * @param codigoAerolinea El codigo de la aerolinea
	 * @return La aerolinea eliminada, NULL de lo contrario
	 */
	public Aerolinea eliminarAerolineaUsuario(String codigoAerolinea);
	
	/**
	 * Agrega la ciudad dada a las preferencias del usuario
	 * Si la ciudad no existe, se agrega el aeropuerto tambien.
	 * @param codigoCiudad El codigo de la ciudad
	 * @return El nombre de la ciudad agregada
	 */
	public String agregarCiudadUsuario(String codigoCiudad);
	
	/**
	 * Elimina la ciudad dada de las preferencias del usuario.
	 * @param El nombre de la ciudad
	 * @return La ciudad eliminada, NULL de lo contrario.
	 */
	public String eliminarCiudadUsuario(String nombreCiudad);
	
	/**
	 * Establece el rango de tiempo en las preferencias del usuario
	 * @param t1 El tiempo minimo de vuelo
	 * @param t2 El tiempo maximo de vuelos
	 */
	public void definirRangoTiempoUsuario(String t1, String t2);
	/////////
	//Fin requerimiento 3
	////////
	/**
	 * Retorna el camino mas corto entre dos aeropuertos
	 * @return La ruta a seguir
	 */
	public Iterator<Aeropuerto> darGrado(String codigo1, String codigo2);
	
	/**
	 * Retorna el camino de menor longitud entre dos aeropuertos
	 * @param codigo1 EL codigo del aeropuerto de salida
	 * @param codigo2 El codigo del aeropuerto de llegada
	 * @return La ruta de menor longitud
	 */
	public Iterator<Aeropuerto> darRutaMenorLongitud(String codigo1,String codigo2);
	
	/**
	 * Retorna la ruta de menor longitud que involucra paradas
	 * @param codigo1 El codigo del aeorpuerto de salida
	 * @param codigo El codigo del aeropuerto de entrada
	 * @return La ruta de menor longitud con parada
	 */
	public Iterator<Aeropuerto> darRutaMenorLongitudConParada(String codigo1, String codigo2);
	
	/**
	 * Retorna el camino de menor tiempo entre dos aeropuertos
	 * @param codigo1 EL codigo del aeropuerto de salida
	 * @param codigo2 El codigo del aeropuerto de llegada
	 * @return La ruta de menor tiempo
	 */
	public Iterator<Aeropuerto> darRutaMenorTiempo();
	
	/**
	 * Retorna la ruta de menor tiempo que involucra paradas
	 * @param codigo1 El codigo del aeorpuerto de salida
	 * @param codigo El codigo del aeropuerto de entrada
	 * @return La ruta de menor tiempo con parada
	 */
	public Iterator<Aeropuerto> darRutaMenorTiempoConParada(String codigo1, String codigo2);
	
	/**
	 * Retorna la ruta con vuelos de mayor rating
	 * @param codigo1 El codigo del aeropuerto de salida
	 * @param codigo2 El codigo del aeropuerto de entrada
	 * @return La ruta de mayor rating
	 */
	public Iterator<Aeropuerto> darRutaMayorRating(String codigo1, String codigo2);
	
	/**
	 * Retorna la ruta con menor cantidad de vuelos tardios
	 * @param codigo1 El codigo del aeropuerto de salida
	 * @param codigo2 El codigo del aeropuerto de llegada
	 * @return La ruta de menor cantidad de vuelos tardios
	 */
	public Iterator<Aeropuerto>darRutaMenorTardios(String codigo1,String codigo2);
	
	/**
	 * Retorna el tour mas largo al aeropuerto dado por parametro<br>
	 * El tour mas largo es el camino mas largo con salida y llegada en el aeropuerto dado.
	 * @param codigo1 El codigo del aeropuerto
	 * @return El camino mas largo
	 */
	public Iterator<Aeropuerto>darTourMasLargo(String codigo1);
	
	/**
	 * Retorna los tours disponibles que incluya los aeropeurtos dados
	 * @param lista La lista de lugares del tour
	 * @return Iterador con los distintos tours disponibles
	 */
	public Iterator<Camino> darToursDisponibles(String[] lista);
	
	/**
	 * Retorna un tour que parte del aeropuerto dado y pasa por los aeropuertos de la preferencia del usuario.
	 * @param codigo El codigo del aeropuerto de inicio
	 * @return El tour de resultado
	 */
	public Iterator<Aeropuerto> darTourDesde(String codigo);
}
