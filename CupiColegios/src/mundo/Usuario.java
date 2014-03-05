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
	
	/**
	 * 
	 */
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
	
	/**
	 * Agrega un hijo al usuario.
	 * @param hijo
	 * @return
	 */
	public Hijo agregarHijo(Hijo hijo){
		return listaHijos.agregar(hijo);
	}
	
	/**
	 * Elimina un hijo del usuario.
	 * @param hijo
	 */
	public void eliminarHijo(Hijo hijo){
		listaHijos.eliminar(hijo);
	}
	
	/**
	 * Agrega un colegio al hijo dado de parametro
	 * @param colegio
	 * @param hijo
	 */
	public void agregarColegioHijo(Colegio colegio, Hijo hijo){
		hijo.agregarFavorito(colegio);
	}

	/**
	 * Retorna el usuario
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Retorna los hijos del usuario
	 * @return
	 */
	public Object[] darHijos(){
		return listaHijos.darArreglo();
	}
	
	/**
	 * Valida la contrasena dada por parametro
	 * @param con
	 * @return true si concuerda, false de lo contrario.
	 */
	public boolean validarContrasena(String con){
		return (contrasena.equals(con));
	}

	/**
	 * Metodo que compara dos usuario a partir del nombre.
	 */
	@Override
	public int compareTo(Usuario o) {
		if(usuario.compareTo(o.getUsuario()) > 0)
			return 1;
		else if(usuario.compareTo(o.getUsuario()) < 0)
			return -1;
		else
			return 0;
	}
}
