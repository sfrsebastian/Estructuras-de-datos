package mundo;

import java.util.Iterator;

import ListaOrdenada.ListaOrdenada;

public class Usuario implements Comparable<Usuario> {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * El nombre del usuario
	 */
	private String nombre;

	/**
	 * El apellido del usuario
	 */
	private String apellido;

	/**
	 * El nombre de usuario del usuario
	 */
	private String usuario;

	/**
	 * El correo del usuario
	 */
	private String correo;

	/**
	 * La contrasenia del usuario
	 */
	private String contrasenia;

	private ListaOrdenada<String> ciudades;

	private ListaOrdenada<Aeropuerto> aeropuertos;

	private ListaOrdenada<Aerolinea> aerolineas;

	//--------------------
	//CONTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo usuario con los parametros dados.
	 * @param nNombre El nombre del usuario
	 * @param nApellido El apellido del usuario
	 * @param nUsuario El usuarion del usuario
	 * @param nCorreo El correo del usuario
	 * @param nContrasenia La contrasenia del usuario
	 */
	public Usuario(String nNombre, String nApellido, String nUsuario, String nCorreo, String nContrasenia){
		nombre = nNombre;
		apellido = nApellido;
		usuario = nApellido;
		correo = nCorreo;
		contrasenia = nContrasenia;
		ciudades = new ListaOrdenada<String>();
		aeropuertos = new ListaOrdenada<Aeropuerto>();
		aerolineas = new ListaOrdenada<Aerolinea>();
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Valida el usuario y contrasenia del usuario
	 * @param user El usuario a comparar
	 * @param pass La contrasenia a comparar
	 * @return TRUE si el usuario se valido, FALSE de lo contrario
	 */
	public boolean AutenticarUsuario(String user, String pass){
		return (user.compareTo(usuario) == 0) && (pass.compareTo(contrasenia)==0);
	}

	/**
	 * Retorna el nombre del usuario
	 * @return El nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna el apellido del usuario
	 * @return El apellido del usuario
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Retorna el nombre usuario
	 * @return El nombre de usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Retorna el correo del usuario
	 * @return
	 */
	public String getCorreo() {
		return correo;
	} 
	
	/**
	 * Retorna los aeropuerto del usuario
	 * @return Iterador de aeropuertos
	 */
	public Iterator<Aeropuerto> getAeropuertos() {
		return aeropuertos.iterator();
	}

	/**
	 * Agrega la ciudad dada por parametro a las de preferencia
	 * @param nueva La nueva ciudad
	 */
	public void agregarCiudad(String nueva){
		if(ciudades.buscar(nueva)==null){
			ciudades.agregar(nueva);
		}
	}

	/**
	 * Elimina la ciudad dada de la lista de favoritas
	 * @param eliminar
	 */
	public void eliminarCiudad(String eliminar){
		ciudades.eliminar(eliminar);
	}

	/**
	 * Agrega el aeropuerto dado por parametro a los de preferencia
	 * @param nueva El nuevo aeropuerto
	 */
	public void agregarAeropuerto(Aeropuerto nuevo){
		if(aeropuertos.buscar(nuevo)==null){
			aeropuertos.agregar(nuevo);
		}
	}

	/**
	 * Elimina el aeropeurto dado de la lista de favoritos
	 * @param eliminar
	 */
	public void eliminarAeropuerto(Aeropuerto eliminar){
		aeropuertos.eliminar(eliminar);
	}

	/**
	 * Agrega la aeroliena dada por parametro a las de preferencia
	 * @param nueva La nueva aerolinea
	 */
	public void agregarAerolinea(Aerolinea nueva){
		if(aerolineas.buscar(nueva)==null){
			aerolineas.agregar(nueva);
		}
	}

	/**
	 * Elimina la aerolinea dada de la lista de favoritas
	 * @param eliminar
	 */
	public void eliminarAerolinea(Aerolinea eliminar){
		aerolineas.eliminar(eliminar);
	}

	@Override
	public int compareTo(Usuario o) {
		return usuario.compareTo(o.getUsuario());
	}
}
