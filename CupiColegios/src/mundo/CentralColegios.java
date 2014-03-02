package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import lector.LectorExcel;
import HashTable.TablaHashing;
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
	
	private Anio actualizado;
	
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
		anios = new ListaOrdenada<Anio>();		
		cargarTablas();
		Iterator<Anio> iterador = anios.iterator();
		while(iterador.hasNext() && colegios == null){
			Anio actual = (Anio)iterador.next();
			if(actual.getAnio() == 2011){
				actualizado = actual;
				colegios = actual.getColegios();
			}
		}
		try {
			ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("./data/usuarios.us"));
			usuarios = (TablaHashing<Llave, Usuario>)ois1.readObject();
			ois1.close();
		} catch (Exception e) {
			usuarios = new TablaHashing<Llave, Usuario>(7,2);
			System.out.println("users not created");
		}
	}

	private void cargarTablas() throws FileNotFoundException,IOException {
		LectorExcel lector = new LectorExcel();
		anios.agregar(lector.construirAnio("./data/2004.xls", 8861, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2005.xls", 8838, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2006.xls", 9250, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2007.xls", 9681, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2008.xls", 10161, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2009.xls", 10376, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2010.xls", 10975, 19, 0, 1));
		anios.agregar(lector.construirAnio("./data/2011.xls", 8587, 19, 0, 1));
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
	public boolean anadirColegioHijo(Colegio colegio, Hijo hijo){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] buscarPorCriterio(Criterio[] criterios){	
		return auxiliar(criterios, colegios.darLista());
	}	
	
	private Object[] auxiliar(Criterio[] criterios, ListaEncadenada<Colegio> nColegios){
		if(criterios.length == 0){
			return nColegios.darArreglo();
		}
		else{
			Criterio criterio = criterios[0];
			if(criterio.darSubcriterios().length == 0){
				return auxiliar(nuevosCriterios(criterios),nColegios);
			}
			ListaEncadenada<Colegio> nueva = new ListaEncadenada<Colegio>();
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
	public Object[] buscarPorArea(Area area, int anio){
		ListaOrdenada<Colegio> buscados = new ListaOrdenada<Colegio>();
		Anio elegido = anios.buscar(new Anio(anio,null,null));
		Iterator<Colegio> iterador = elegido.getColegios().iterator();
		while(iterador.hasNext()){
			Colegio actual = iterador.next();
			int puntaje = actual.getNotas().getLista().buscar(area).getPuntaje();
			if(puntaje == area.getPuntaje() && puntaje != Area.NO_APLICA){
				buscados.agregar(actual);
			}
		}
		return buscados.darArreglo();
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
	public Object[] mostrarColegiosPorUbicacion(int codigoDepartamento, int codigoMunicipio) {
		if(!(codigoMunicipio > 0)){
			return actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento)).buscarMunicipio(new Llave(codigoMunicipio)).getColegios().darArreglo();
		}
		else{
			return actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento)).getColegios().darArreglo();
		}
		
	}

	public Usuario darUsuarioActual() {
		return usuarioActual;
	}
	
	public Object[] darColegios(){
		return colegios.darArreglo();
	}
	
	public Object[] darDepartamentos(){
		return actualizado.getDepartamentos().darArreglo(); 
	}
	
	public Object[] darMunicipios(int codigoDepartamento){
		return actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento)).getMunicipios().darArreglo();
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
