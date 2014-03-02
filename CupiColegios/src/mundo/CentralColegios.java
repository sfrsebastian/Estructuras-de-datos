package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import HashTable.TablaHashing;
import Lista.Lista;
import ListaEncadenada.ListaEncadenada;
import ListaOrdenada.ListaOrdenada;

public class CentralColegios implements ICentralColegios {
	
	//------------------------------------------
	// Atributos
	//------------------------------------------
	
	/**
	 * El usuario actual de la central
	 */
	private Usuario usuarioActual;
	
	/**
	 * 
	 */
	private TablaHashing<Llave,Colegio> colegios;
	
	private ListaOrdenada<Anio> anios;
	/**
	 * 
	 */
	private TablaHashing<Llave, Usuario> usuarios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public CentralColegios() throws FileNotFoundException, IOException, ClassNotFoundException{
		usuarioActual = null;
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream("./data/serializados/2011.col"));
		colegios = (TablaHashing<Llave, Colegio>) ois.readObject();
		ois.close();
		
		try {
			ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("./data/usuarios.us"));
			usuarios = (TablaHashing<Llave, Usuario>)ois1.readObject();
			ois1.close();
		} catch (Exception e) {
			usuarios = new TablaHashing<Llave, Usuario>(7,2);
			System.out.println("users not created");
		}
	}

	@Override
	public Usuario agregarNuevoUsuario(String usuario, String contrasena) {
		Usuario usu = new Usuario(usuario, contrasena);
		usuarios.agregar(new Llave(usu.getUsuario()), usu);
		usuarioActual = usu;
		System.out.println("Usuario: " + usu.getUsuario() + " conectado!");
		return usu;
	}

	@Override
	public boolean registrarHijoUsuario(Usuario usuario, Hijo hijo) {
		usuario.agregarHijo(hijo);
		return true;
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salvarUsuarios() throws FileNotFoundException, IOException{
		File archivo = new File("./data/usuarios.us");
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(archivo));
		
		os.writeObject(usuarios);
		os.close();
	}

	@Override
	public boolean anadirColegioHijo(Colegio colegio, Hijo hijo)
			throws GeneroIncompatibleException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] buscarPorCriterio(Criterio[] criterios){	
		return auxiliar(criterios, colegios.darLista());
	}	
	
	private Object[] auxiliar(Criterio[] criterios, Lista nColegios){
		if(criterios.length == 0){
			return nColegios.darArreglo();
		}
		else{
			Criterio criterio = criterios[0];
			if(criterio.darSubcriterios().length == 0){
				return auxiliar(nuevosCriterios(criterios),nColegios);
			}
			Lista<Colegio> nueva = new ListaEncadenada<Colegio>();
			Iterator<Colegio> iterador = nColegios.iterator();
			while(iterador.hasNext()){
				Colegio actual = iterador.next();
				if(actual.cumpleCriterio(criterio)){
					nueva.agregar(actual);
				}
			}
			return auxiliar(nuevosCriterios(criterios),nueva);
		}
	}
	private Criterio[] nuevosCriterios(Criterio[] criterios) {
		Criterio[] nueva = new Criterio[criterios.length-1];
		for(int i = 1; i<criterios.length;i++){
			nueva[i-1] = criterios[i];
		}
		return nueva;
	}

	@Override
	public Colegio[] buscarPorArea(Area area, AnioAcademico anio, int puntaje)
			throws RangoInvalidoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Colegio mostrarInfoColegio(Colegio colegio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] mostrarRecomendados(Hijo hijo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarEstadisticas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrarEstadisticasNacionales() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] mostrarColegiosPorUbicacion(String ubicacion,
			boolean esDepto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario darUsuarioActual() {
		return usuarioActual;
	}
	
	public Object[] darColegios(){
		return colegios.darArreglo();
	}

	public boolean buscarUsuario(String usuario, String pass) {

		Usuario encontrado = usuarios.buscar(new Llave(usuario));
		if(encontrado != null){
			if(encontrado.validarContrasena(pass)){
				usuarioActual = encontrado;
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	public Object[] darHijosUsuarioActual() {
		if(usuarioActual != null)
			return usuarioActual.darHijos();
		else
			return new Object[0];
	}

	public void eliminarHijo(Hijo elim) {
		usuarioActual.eliminarHijo(elim);
	}

	public void cerrarSesion() {
		usuarioActual = null;
	}

}
