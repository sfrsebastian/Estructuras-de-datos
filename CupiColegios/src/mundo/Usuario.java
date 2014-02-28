package mundo;

import java.io.Serializable;

import ListaOrdenada.ListaOrdenada;


public class Usuario implements Comparable<Usuario>,Serializable{
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El nombre del usuario, es unico
	 */
	private String usuario;
	
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
		usuario = nUsuario;
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
		return usuario;
	}

	public void setUsuairo(String usuairo) {
		this.usuario = usuairo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public Object[] darHijos(){
		return listaHijos.darElementos();
	}

	@Override
	public int compareTo(Usuario o) {
		if(usuario.compareTo(o.getUsuairo()) > 0)
			return 1;
		else if(usuario.compareTo(o.getUsuairo()) < 0)
			return -1;
		else
			return 0;
	}
}
