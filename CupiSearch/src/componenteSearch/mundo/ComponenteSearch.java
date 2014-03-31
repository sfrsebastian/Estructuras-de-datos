package componenteSearch.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Timer;

import co.edu.uniandes.cupi2.datos.cmpRegistro.compartido.Protocolo;
import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;
import CompresorHuffman.CompresorHuffman;
import ListaEncadenada.ListaEncadenada;
import uniandes.cupi2.cupIphone.core.ICore;

public class ComponenteSearch implements ICupiSearch {

	private static final String RUTAEXPLORACIONES = "/datos/exploraciones.dat";

	private static final String RUTAUID = "/datos/UID.dat";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------

	private IArbolBinarioOrdenado<Categoria> categorias;

	private IArbolBinarioOrdenado<Exploracion> exploraciones;

	private Exploracion exploracionActual;
	/**
	 * La referencia al core de la aplciacion
	 */
	private ICore core;

	private Scraper scraper;
	
	private static String UID;

	private ThreadComunicacion thread;
	/**
	 * 
	 * @param c
	 * @throws Exception 
	 */
	public ComponenteSearch(ICore c) throws Exception {
		core = c;
		categorias = new ArbolBinarioAVLOrdenado<Categoria>();
		UID = asignarUID();
		exploracionActual = null;
		inicializarExploraciones();
		recuperarCategorias();
		scraper = new Scraper();
	}

	private void inicializarExploraciones() throws Exception {
		File ruta = new File(darRuta()+RUTAEXPLORACIONES);
		if(!ruta.exists()){
			exploraciones = new ArbolBinarioAVLOrdenado<Exploracion>();
		}
		else{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
			exploraciones = (IArbolBinarioOrdenado<Exploracion>) ois.readObject();
		}
	}

	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------


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

	public void agregarSitiosFuente(String url){
		scraper.agregarURL(url);
	}

	/**
	 * @param l 
	 * @param niveles 
	 * @see package0.ICupiSearch#explorarSitios()
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
	 * @see package0.ICupiSearch#mostrarEstadistica(package0.Exploracion)
	 */
	public String mostrarEstadistica(Exploracion nExploracion) {
		return exploraciones.buscar(nExploracion).darEstadistica();
	}

	/**
	 * @see package0.ICupiSearch#darHistorialExploraciones()
	 */
	public Iterator<Exploracion> darHistorialExploraciones() {
		return exploraciones.recorrerInorden();
	}

	/**
	 * @see package0.ICupiSearch#buscarResultados(Criterio[], Timer, int)
	 */
	@Override
	public Object[] buscarResultados(String[] criterios, String valor) {
		exploracionActual.aumentarBusqueda();
		return busquedaNormal(criterios,exploracionActual.getRecursos().darArreglo(),valor);
	}

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

	private String[] disminuirCriterios(String[] criterios) {
		String[] nueva = new String[criterios.length-1];
		for (int i = 1; i < criterios.length; i++) {
			nueva[i-1] = criterios[i];
		}
		return nueva;
	}

	/**
	 * @see package0.ICupiSearch#crearCategoria(java.lang.String)
	 */
	public void crearCategoria(String nombre, String descripcion) {
		Categoria nueva = new Categoria(nombre,descripcion);
		categorias.agregar(nueva);
	}


	/**
	 * @see package0.ICupiSearch#eliminarCategoria(java.lang.String)
	 */
	public boolean eliminarCategoria(Categoria categoria) {
		return categorias.eliminar(categoria);
	}


	/**
	 * @see package0.ICupiSearch#agregarRecursoACategoria(java.lang.String, package0.Recurso)
	 */
	public void agregarRecursoACategoria(Categoria categoria, Recurso recurso) {
		categoria.agregarRecurso(recurso);
	}


	/**
	 * @see package0.ICupiSearch#eliminarRecursoDeCategoria(java.lang.String, package0.Recurso)
	 */
	public void eliminarRecursoDeCategoria(Categoria categoria, Recurso recurso) {
		categoria.eliminarRecurso(recurso);
	}


	/**
	 * @throws Exception 
	 * @see package0.ICupiSearch#comprimirCategorias()
	 */
	public void comprimirCategorias() throws Exception {
		String aComprimir = "";
		for (Categoria actual : categorias) {
			aComprimir += actual.toString()+Protocolo.SEPARATOR; 
		}
		aComprimir = aComprimir.substring(0,aComprimir.length());
		CompresorHuffman c = new CompresorHuffman(aComprimir);
		thread.registrarCategorias(c.comprimir());
	}


	/**
	 * @throws Exception 
	 * @see package0.ICupiSearch#recuperarCategorias()
	 */
	public void recuperarCategorias() throws Exception {
		Iterator<Categoria> iterator = thread.recuperarCategorias();
	}


	/**
	 * @throws IOException 
	 * @see package0.ICupiSearch#visualizarResultado(package0.Recurso)
	 */
	public String visualizarImagen(Recurso recurso) throws IOException {
		return scraper.descargarImagen(recurso.getImgUrl(),darRuta());
	}

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
		
		if(UID != null){
			ruta = new File(darRuta() + RUTAUID);
			ois = new ObjectOutputStream(new FileOutputStream(ruta));
			ois.writeObject(UID);
			ois.close();
		}
		
		//comprimirCategorias();
		System.out.println("guardo!");
	}

	public String darRuta(){
		if(core !=null){
			return core.darDirectorioDatos().getPath();// + "/datos/exploraciones.dat/");
		}
		else{
			return "./data"; //  /datos/exploraciones.dat/");
		}
	}
	public Exploracion getExploracionActual() {
		return exploracionActual;
	}
}