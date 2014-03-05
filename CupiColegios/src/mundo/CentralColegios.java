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
	 * La tabla de colegios mas actualizada (2011)
	 */
	private TablaHashing<Llave,Colegio> colegios;
	
	/**
	 * El anio mas actualizado
	 */
	private Anio actualizado;
	
	/**
	 * Lista que contiene los anios de la central.
	 */
	private ListaOrdenada<Anio> anios;
	
	/**
	 * Hash Table que almacena los usuarios de la central.
	 */
	private TablaHashing<Llave, Usuario> usuarios;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	
	/**
	 * Crea una nueva central de colegios.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public CentralColegios() throws FileNotFoundException, IOException{
		usuarioActual = null;
		anios = new ListaOrdenada<Anio>();		
		cargarTablas();
		Iterator<Anio> iterador = anios.iterator();
		while(iterador.hasNext() && colegios == null){
			Anio actual = iterador.next();
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

	/**
	 * Carga todos los anios disponible para la central.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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

	/**
	 * Agrega un nuevo usuario a la central.
	 */
	@Override
	public Usuario agregarNuevoUsuario(String usuario, String contrasena) {
		Usuario usu = new Usuario(usuario, contrasena);
		usuarios.agregar(new Llave(usu.getUsuario()), usu);
		usuarioActual = usu;
		System.out.println("Usuario: " + usu.getUsuario() + " conectado!");
		return usu;
	}

	/**
	 * Registra un hijo al usuario
	 */
	@Override
	public boolean registrarHijoUsuario(Usuario usuario, Hijo hijo) {
		usuario.agregarHijo(hijo);
		return true;
	}
	
	/**
	 * Guarda un archivo serializado con los usuarios.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salvarUsuarios() throws FileNotFoundException, IOException{
		File archivo = new File("./data/usuarios.us");
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(archivo));
		
		os.writeObject(usuarios);
		os.close();
	}

	/**
	 * Filtra por los criterios dados la lista de colegios.
	 */
	@Override
	public Object[] buscarPorCriterio(Criterio[] criterios){	
		return auxiliar(criterios, colegios.darLista());
	}	
	
	/**
	 * Metodo auxiliar para filtrar los colegios.
	 * @param criterios
	 * @param nColegios
	 * @return
	 */
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
	
	/**
	 * Metodo auxiliar para disminuir la lista de criterios.
	 * @param criterios
	 * @return
	 */
	private Criterio[] nuevosCriterios(Criterio[] criterios) {
		Criterio[] nueva = new Criterio[criterios.length-1];
		for(int i = 1; i<criterios.length;i++){
			nueva[i-1] = criterios[i];
		}
		return nueva;
	}

	/**
	 * Busca los colegios segun el area dada y rangos dados.
	 */
	@Override
	public Object[] buscarPorArea(Area area, int anio, int puntajeA, int puntajeB){
		ListaOrdenada<Colegio> buscados = new ListaOrdenada<Colegio>();
		Anio elegido = anios.buscar(new Anio(anio,null,null));
		Iterator<Colegio> iterador = elegido.getColegios().iterator();
		while(iterador.hasNext()){
			Colegio actual = iterador.next();
			
			int puntaje = actual.getNotas().getLista().buscar(area).getPuntaje();
			if(puntaje >= puntajeB && puntaje <= puntajeA && puntaje != Area.NO_APLICA){
				buscados.agregar(actual);
			}
		}
		return buscados.darArreglo();
	}

	/**
	 * Retorna una matriz con los datos que representan la cantidad de colegios MUY SUPERIORES por cad departamento
	 */
	@Override
	public String[][] darDatosDepartamentos() {
		TablaHashing<Llave,Departamento> departamentos = actualizado.getDepartamentos();
		String[][] respuesta = new String[departamentos.darLongitud()][2];
		Iterator<Departamento> iterador =departamentos.iterator();
		int i = 0;
		while(iterador.hasNext()){
			Departamento actual = iterador.next();
			respuesta[i][0] = actual.getNombre();
			respuesta[i][1] = ""+ actual.darTotalMuySuperiores();
			i++;
		}
		return respuesta;
	}
	
	/**
	 * Retorna una matriz de datos con los promedio de icfes de cada anio.
	 * @return
	 */
	@Override
	public String[][] darPromediosIcfes(){
		Iterator i = anios.iterator();
		String[][] respuesta = new String[anios.darLongitud()][2];
		int c = 0;
		while(i.hasNext()){
			Anio anioActual = (Anio)i.next();
			double resp = anioActual.darPromedioIcfes();
			respuesta[c][0] = "" + anioActual.getAnio();
			respuesta[c][1] = "" + resp;
			c++;
		}
		
		return respuesta;
		
	}
	
	/**
	 * Retorna una matriz con los datos referentes a los departamentos con mayor cantidad de colegios uy superores.
	 * @return
	 */
	@Override
	public String[][] darDatosDepartamentosAnio(){
		Iterator i = anios.iterator();
		String[][] respuesta = new String[anios.darLongitud()][2];
		
		int c = 0;
		
		while(i.hasNext()){
			Anio anioActual = (Anio)i.next();
			int resp = anioActual.darCantidadDeptosSuperiores();
			respuesta[c][0] = "" + anioActual.getAnio();
			respuesta[c][1] = "" + resp;
			c++;
		}
		
		return respuesta;
	}

	/**
	 * Retorna los colegios que cumplen con las ubicaciones dadas por parametro.
	 */
	@Override
	public Object[] mostrarColegiosPorUbicacion(int codigoDepartamento, int codigoMunicipio) {
		if(codigoMunicipio > 0){
			return actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento)).buscarMunicipio(new Llave(codigoMunicipio)).getColegios().darArreglo();
		}
		else{
			Departamento d = actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento));
			Object[] cols = d.getColegios().darArreglo();
			//return actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento)).getColegios().darArreglo();
			return cols;
		}
		
	}

	/**
	 * retorna el usuario actual de la central.
	 * @return
	 */
	public Usuario darUsuarioActual() {
		return usuarioActual;
	}
	
	/**
	 * Retorna los colegios de la central.
	 */
	public Object[] darColegios(){
		return colegios.darArreglo();
	}
	
	/**
	 * Retorna los departamentos de la central.
	 * @return
	 */
	public Object[] darDepartamentos(){
		return actualizado.getDepartamentos().darArreglo(); 
	}
	
	/**
	 * Retorna los municipios del departamento dado por parametro.
	 * @param codigoDepartamento
	 * @return
	 */
	public Object[] darMunicipios(int codigoDepartamento){
		return actualizado.getDepartamentos().buscar(new Llave(codigoDepartamento)).getMunicipios().darArreglo();
	}

	/**
	 * Busca el usuario con usuario y contrasena dados por parametro.
	 * @param usuario
	 * @param pass
	 * @return true si se valido al usuario, false de lo contrario.
	 */
	@Override
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

	/**
	 * Retorna los hijos del usuario actual.
	 * @return
	 */
	public Object[] darHijosUsuarioActual() {
		if(usuarioActual != null)
			return usuarioActual.darHijos();
		else
			return new Object[0];
	}

	/**
	 * Elimina el hijo dado por parametro del usuario actual.
	 * @param elim
	 */
	public void eliminarHijo(Hijo elim) {
		usuarioActual.eliminarHijo(elim);
	}

	/**
	 * Cierra sesion del usuario actual.
	 */
	public void cerrarSesion() {
		usuarioActual = null;
	}

	/**
	 * Busca un colegio dado su codigo.
	 * @param cod
	 * @return
	 */
	public Colegio buscarColegioCodigo(String cod) {
		Llave llave = new Llave(cod);
		return colegios.buscar(llave);
	}

	/**
	 * Retorna un arreglo con los anios de la central.
	 * @return
	 */
	public Object[] darAnios() {
		return anios.darArreglo();
	}

	/**
	 * Busca un colegio dado un anio y codigo.
	 * @param codigo
	 * @param n
	 * @return
	 */
	public Colegio buscarAnioColegio(String codigo, Anio n) {
		Llave l = new Llave(codigo);
		return n.getColegios().buscar(l);
	}
	
	/**
	 * Retorna una matriz con la informacion del promedio de cada anio.
	 * @param codigoDepartamento
	 * @return
	 */
	@Override
	public String[][] darPromedioAnios(int codigoDepartamento){
		String[][] respuesta = new String [anios.darLongitud()][2];
		Iterator<Anio> iterador = anios.iterator();
		int i  = 0;
		while(iterador.hasNext()){
			Anio actual = iterador.next();
			Departamento dep = actual.getDepartamentos().buscar(new Llave(codigoDepartamento));
			respuesta[i][0]=""+actual.getAnio();
			respuesta[i][1]=""+dep.darPromedio();
			i++;
		}
		return respuesta;
	}

	/**
	 * Retorna una matriz con los datos correspondientes a el promedio de matematicas de cada anio.
	 * @return
	 */
	@Override
	public String[][] darDatosGraficaLibre() {
		String[][] respuesta = new String [anios.darLongitud()][2];
		Iterator<Anio> iterador = anios.iterator();
		int i  = 0;
		while(iterador.hasNext()){
			Anio actual = iterador.next();;
			respuesta[i][0]=""+actual.getAnio();
			respuesta[i][1]=""+actual.darPromedioMatematicas();
			i++;
		}
		return respuesta;
	}

	//Metodos de extension.
	public String metodoExtension1() {
		return "Metodo extension 1";
	}

	public String metodoExtension2() {
		return "Metodo extension 1";
	}

}
