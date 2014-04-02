package componenteSearch.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import ArbolAVl.ArbolBinarioAVLOrdenado;
import CompresorHuffman.CompresorHuffman;
import CompresorHuffman.TextoComprimido;
import ListaEncadenada.ListaEncadenada;
import uniandes.cupi2.cupIphone.core.ICore;

public class ComponenteSearch implements ICupiSearch {

	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------
	/**
	 * La ruta donde se almacena el historial de exploraciones.
	 */
	private static final String RUTAEXPLORACIONES = "/datos/exploraciones.dat";

	/**
	 * Ruta donde se almacena el UID
	 */
	private static final String RUTAUID = "/datos/UID.dat";
	
	/**
	 * El UID unico de la aplicacion
	 */
	private static final String UID = "4622312e-7c98-4d73-b969-53fb409c170c";
	
	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	/**
	 * La categorias de CupiSearch
	 */
	private ArbolBinarioAVLOrdenado<Categoria> categorias;

	/**
	 * El historial de exploraciones de cupiSearch
	 */
	private ArbolBinarioAVLOrdenado<Exploracion> exploraciones;

	/**
	 * La exploracion actual.
	 */
	private Exploracion exploracionActual;
	
	/**
	 * La referencia al core de la aplciacion
	 */
	private ICore core;

	/**
	 * El lector de recursos de las paginas agregadas.
	 */
	private Scraper scraper;

	/**
	 * Thread que se encarga de la comunicacion con el servidor de persistencia
	 */
	private ThreadComunicacion thread;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea un nuevo ComponenteSearch
	 * @param c El core del Cupiphone
	 * @throws Exception 
	 */
	public ComponenteSearch(ICore c) throws Exception {
		core = c;
		categorias = new ArbolBinarioAVLOrdenado<Categoria>();
		//thread = new ThreadComunicacion(UID);
		exploracionActual = null;
		inicializarExploraciones();
		//recuperarCategorias();
		scraper = new Scraper();
	}

	/**
	 * Metodo auxiliar constructor.<br>
	 * Inicializa las exploraciones segun la disponibilidad de archivo de persistencia serializado
	 * @throws Exception
	 */
	private void inicializarExploraciones() throws Exception {
		File ruta = new File(darRuta()+RUTAEXPLORACIONES);
		if(!ruta.exists()){
			exploraciones = new ArbolBinarioAVLOrdenado<Exploracion>();
		}
		else{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
			exploraciones = (ArbolBinarioAVLOrdenado<Exploracion>) ois.readObject();
		}
	}

	/**
	 * Registra el componente en el servidor de persistencia
	 * @return El UID del componente
	 * @throws Exception
	 */
	private String asignarUID() throws Exception {
		File ruta = new File(darRuta() + RUTAUID);
		if(ruta.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
			String id =  (String) ois.readObject();
			thread = new ThreadComunicacion(id);
			return id;
		}
		else{
			thread = new ThreadComunicacion();
			return thread.registrarAplicacion();
		}
	}
	
	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------

	/**
	 * Agrega el url dado por parametro al explorador
	 */
	public void agregarSitiosFuente(String url){
		scraper.agregarURL(url);
	}
	
	/**
	 * Explora los sitios agregados en el explorador.<br>
	 * Asigna a la exploracion actual los resultados de busqeuda.
	 * @param El tiempo de busqueda
	 * @param niveles Los niveles de busqueda
	 */
	public void explorarSitios(long l, int niveles){
		if(l == 0)
			l = 600000;
		else if (niveles == 0)
			niveles = 100000;
		
		exploracionActual = scraper.explorarSitios(l,niveles);
		exploraciones.agregar(exploracionActual);
	}

	/**
	 * Retorna un iterador con el historial de exploraciones del componente
	 * @return Iterador con las exploraciones
	 */
	public Iterator<Exploracion> darHistorialExploraciones() {
		return exploraciones.recorrerInorden();
	}

	/**
	 * Busca los resultados a partir de los criterios y el valor especificados.
	 * @param criterios Los criterios de busqueda
	 * @param arreglo El arreglo de recursos
	 * @param valor El valor de busqueda. CONTIENE,NOCONTIENE;IGUAL
	 * @return Arreglo con el resultado de busqueda
	 */
	public Object[] buscarResultados(String[] criterios, String valor) {
		exploracionActual.aumentarBusqueda();
		return busquedaNormal(criterios,exploracionActual.getRecursos().darArreglo(),valor);
	}

	/**
	 * Metodo recursivo de busqueda
	 * @param criterios Los criterios de busqueda
	 * @param arreglo El arreglo de recursos
	 * @param valor El valor de busqueda. CONTIENE,NOCONTIENE;IGUAL
	 * @return Arreglo con el resultado de busqueda
	 */
	private Object[] busquedaNormal(String[] criterios, Object[] arreglo,String valor) {
		if(criterios.length == 0){
			return arreglo;
		}
		else{
			ListaEncadenada<Recurso> lista = new ListaEncadenada<Recurso>();
			for(int i = 0; i<arreglo.length;i++){
				Recurso actual = (Recurso) arreglo[i];
				if(verificarValor(criterios[0],valor,actual)){
					lista.agregar(actual);
				}
			}
			return busquedaNormal(disminuirCriterios(criterios),lista.darArreglo(),valor);
		}
	}

	/**
	 * Metodo auxiliar que verifica el recurso a partir del valor dado y el criterio.
	 * @param criterios El criterio de busqueda
	 * @param Recurso el recurso de verificacion
	 * @param valor El valor de busqueda. CONTIENE,NOCONTIENE;IGUAL
	 * @return TRUE si cumple el valor y el criterio, FALSE de lo contrario
	 */
	private boolean verificarValor(String criterio,String valor,Recurso recurso) {
		if(valor.equals("CONTIENE")){
			return recurso.contiene(criterio);
		}
		else if(valor.equals("NO CONTIENE") ){
			return !recurso.contiene(criterio);
		}
		else{
			return recurso.igual(criterio);
		}
	}

	/**
	 * Metodo que disminuye los criterios para la recursion de busqeuda.
	 * @param criterios
	 * @return
	 */
	private String[] disminuirCriterios(String[] criterios) {
		String[] nueva = new String[criterios.length-1];
		for (int i = 1; i < criterios.length; i++) {
			nueva[i-1] = criterios[i];
		}
		return nueva;
	}

	/**
	 * Crea una nueva categoria a partir del nombre y descripcion dados de parametro
	 * @param nombre El nombre de la categoria
	 * @param La descripcion de la categoria
	 */
	public void crearCategoria(String nombre, String descripcion) {
		Categoria nueva = new Categoria(nombre,descripcion);
		categorias.agregar(nueva);
	}

	/**
	 * Elimina la categoria dada por parametro
	 * @param La categoria a eliminar
	 */
	public boolean eliminarCategoria(Categoria categoria) {
		return categorias.eliminar(categoria);
	}

	/**
	 * Agregar el recurso dado por parametro a la categoria
	 * @param La categoria a la cual agregar el recurso
	 * @param El recurso a agregar
	 */
	public void agregarRecursoACategoria(Categoria categoria, Recurso recurso) {
		categoria.agregarRecurso(recurso);
	}

	/**
	 * Agrega la categoria dada por parametro al arbol de categorias
	 * @param cat la categoria a agregar
	 * @return TRUE si se agrega, FALSE de lo contrario
	 */
	public boolean agregarCategoria(Categoria cat){
		return categorias.agregar(cat);
	}

	/**
	 * Elimina el recurso dado por parametro de la categoria dada por parametro
	 */
	public void eliminarRecursoDeCategoria(Categoria categoria, Recurso recurso) {
		categoria.eliminarRecurso(recurso);
	}

	/**
	 * Comprime las categorias acorde al protocolo del servidor de persistencia
	 * @throws Exception
	 */
	public void comprimirCategorias() throws Exception {
		String aComprimir = "";
		if(categorias.darPeso() != 0){
			for (Categoria actual : categorias) {
				aComprimir += actual.comprimir()+"~"; 
			}
			aComprimir = aComprimir.substring(0,aComprimir.length()-1);
			CompresorHuffman c = new CompresorHuffman(aComprimir);
			TextoComprimido a = c.comprimir();
			System.out.println(c.comprimir().descomprimir());
			thread.registrarCategorias(a);
		}
	}

	/**
	 * Recupera las categorias del servidor de persistencia
	 * @throws Exception
	 */
	public void recuperarCategorias() throws Exception {
		Iterator<Categoria> iterator = thread.recuperarCategorias();
		while(iterator.hasNext()){
			Categoria actual = iterator.next();
			agregarCategoria(actual);
		}
	}

	/**
	 * Descarga la imagen del recurso
	 * @throws IOException
	 */
	public String visualizarImagen(Recurso recurso) throws IOException {
		return scraper.descargarImagen(recurso.getImgUrl(),darRuta());
	}

	/**
	 * Serializa el historial de exploraciones y persiste las categorias con el servidor
	 * @throws Exception
	 */
	public void guardar() throws Exception {
		File ubicacion = new File(darRuta() + "/datos");
		File ruta = new File(darRuta() + RUTAEXPLORACIONES);
		if(!ubicacion.exists()){
			ubicacion.mkdir();
		}
		
		ObjectOutputStream ois;
		if(exploracionActual!=null){
			exploraciones.agregar(exploracionActual);
			ois = new ObjectOutputStream(new FileOutputStream(ruta));
			ois.writeObject(exploraciones);
			ois.close();
		}
//		
//		if(UID != null){
//			ruta = new File(darRuta() + RUTAUID);
//			ois = new ObjectOutputStream(new FileOutputStream(ruta));
//			ois.writeObject(UID);
//			ois.close();
//		}
		
		comprimirCategorias();
		System.out.println("guardo!");
	}

	/**
	 * Retorna la ruta para almacenamiento acorde si es standalone o en CupiPhone
	 * @return
	 */
	public String darRuta(){
		if(core !=null){
			return core.darDirectorioDatos().getPath();// + "/datos/exploraciones.dat/");
		}
		else{
			return "./data"; //  /datos/exploraciones.dat/");
		}
	}
	
	/**
	 * Retorna la exploracion actual
	 * @return La exploracion actual
	 */
	public Exploracion getExploracionActual() {
		return exploracionActual;
	}

	/**
	 * Retorna el arbol de categorias
	 * @return Las categorias
	 */
	public ArbolBinarioAVLOrdenado<Categoria> getCategorias() {
		return categorias;
	}
	
	/**
	 * Retorna el historial de exploraciones de la aplicacion
	 * @return El historial de exploraciones
	 */
	public ArbolBinarioAVLOrdenado<Exploracion> getExploraciones() {
		return exploraciones;
	}
	
	/**
	 * Retorna el explorador de recrusos
	 * @return
	 */
	public Scraper getScraper(){
		return scraper;
	}
}
