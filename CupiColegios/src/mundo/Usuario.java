package mundo;

import estructuras.ListaOrdenada;

public class Usuario {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El nombre del usuario, es unico
	 */
	private String usuairo;
	
	/**
	 * La contrasena que tiene el usuario
	 */
	private String contrasena;
	
	private ListaOrdenada<Hijo> listaHijos;
	
	//------------------------------------------
	// Contructor
	//------------------------------------------
	
	/**
	 * Crea un nuevo usuario con un nombre y contrasena
	 * @param nUsuario El nombre del usuario
	 * @param nContrasena La contrasena del nuevo usuairo
	 */
	public Usuario(String nUsuario, String nContrasena){
		usuairo = nUsuario;
		contrasena = nContrasena;
		listaHijos = new ListaOrdenada<Hijo>();
	}
	
	//------------------------------------------
	// Metodos
	//------------------------------------------
	
	public Hijo agregarHijo(Hijo hijo){
		return listaHijos.agregar(hijo);
	}
	
	public void eliminarHijo(Hijo hijo){
		listaHijos.eliminar(hijo);
	}
	
	public void agregarColegioHijo(Colegio colegio, Hijo hijo){
		hijo.agregarFavorito(colegio);
	}

	public String getUsuairo() {
		return usuairo;
	}

	public void setUsuairo(String usuairo) {
		this.usuairo = usuairo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
