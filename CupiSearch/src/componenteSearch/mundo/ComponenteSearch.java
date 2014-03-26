package componenteSearch.mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;
import ListaEncadenada.ListaEncadenada;
import uniandes.cupi2.cupIphone.core.ICore;

public class ComponenteSearch implements ICupiSearch {

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

	/**
	 * 
	 * @param c
	 */
	public ComponenteSearch(ICore c) {
		core = c;
		categorias = new ArbolBinarioAVLOrdenado<Categoria>();
		exploraciones = new ArbolBinarioAVLOrdenado<Exploracion>();
		exploracionActual = null;
		scraper = new Scraper();
	}

	//-----------------------------------------------------------------
	// Metodos
	//-----------------------------------------------------------------
	/**
	 * @see package0.ICupiSearch#agregarSitiosFuente(URL)
	 */
//	public void agregarSitiosFuente(URL url) {
//		scraper.agregarURL(url.getPath());
//	}

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
	public Object[] buscarResultados(String[] criterios, int segundos, int nivel) {
		if(segundos != 0){
			long tiempoInicio = System.currentTimeMillis();
			return buscarXtiempo(criterios,segundos, exploracionActual.getRecursos().darArreglo(),tiempoInicio);
		}
		else if(nivel != 0){
			return buscarXNivel(criterios,exploracionActual.getRecursos().darArreglo(),nivel);
		}
		else{
			return busquedaNormal(criterios,exploracionActual.getRecursos().darArreglo());
		}
	}

	private Object[] busquedaNormal(String[] criterios, Object[] arreglo) {
		if(criterios.length == 0){
			return arreglo;
		}
		else{
			ListaEncadenada<Recurso> lista = new ListaEncadenada<Recurso>();
			for(int i = 0; i<arreglo.length;i++){
				Recurso actual = (Recurso) arreglo[i];
				if(actual.contiene(criterios[0])){
					lista.agregar(actual);
				}
			}
			return busquedaNormal(disminuirCriterios(criterios),lista.darArreglo());
		}
	}

	private String[] disminuirCriterios(String[] criterios) {
		String[] nueva = new String[criterios.length-1];
		for (int i = 1; i < criterios.length; i++) {
			nueva[i-1] = criterios[i];
		}
		return nueva;
	}

	private Object[] buscarXNivel(String[] criterios, Object[] arreglo, int nivel) {
		if(criterios.length == 0 || nivel == 0){
			return arreglo;
		}
		else{
			ListaEncadenada<Recurso> lista = new ListaEncadenada<Recurso>();
			for(int i = 0; i<arreglo.length;i++){
				Recurso actual = (Recurso) arreglo[i];
				if(actual.contiene(criterios[0])){
					lista.agregar(actual);
				}
			}
			return buscarXNivel(disminuirCriterios(criterios), lista.darArreglo(),nivel-1);
		}
	}

	private Object[] buscarXtiempo(String[] criterios, int segundos,Object[] arreglo, long inicio) {
		long tiempoTranscurrido = (new Date()).getTime() - inicio;
		if(criterios.length == 0 || tiempoTranscurrido > segundos*1000){
			return arreglo;
		}
		else{
			ListaEncadenada<Recurso> lista = new ListaEncadenada<Recurso>();
			for(int i = 0; i<arreglo.length && tiempoTranscurrido < segundos*1000;i++){
				Recurso actual = (Recurso) arreglo[i];
				if(actual.contiene(criterios[0])){
					lista.agregar(actual);
				}
				tiempoTranscurrido = (new Date()).getTime() - inicio;
			}
			return buscarXtiempo(disminuirCriterios(criterios), segundos,lista.darArreglo(),inicio);
		}
	}

	/**
	 * @see package0.ICupiSearch#crearCategoria(java.lang.String)
	 */
	public void crearCategoria(String nombre) {
		Categoria nueva = new Categoria(nombre);
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
	 * @see package0.ICupiSearch#comprimirCategorias()
	 */
	public void comprimirCategorias() {
		
	}


	/**
	 * @see package0.ICupiSearch#recuperarCategorias()
	 */
	public void recuperarCategorias() {

	}


	/**
	 * @throws IOException 
	 * @see package0.ICupiSearch#visualizarResultado(package0.Recurso)
	 */
	public String visualizarImagen(Recurso recurso) throws IOException {
		if(core != null){
			return scraper.descargarImagen(recurso.getImgUrl(),core.darDirectorioDatos().getPath());
		}
		else{
			return scraper.descargarImagen(recurso.getImgUrl(),"./data");
		}
		
	}

	public void guardar() throws FileNotFoundException, IOException {
		File ruta;
		if(core !=null){
			ruta = new File(core.darDirectorioDatos().getPath() + "/datos/exploraciones.dat/");
		}
		else{
			ruta = new File("./data/datos/exploraciones.dat/");
		}
		
		exploraciones.agregar(exploracionActual);
		ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(ruta));
		ois.writeObject(exploraciones);
		ois.close();
		System.out.println("guardo!");
	}

	public Exploracion getExploracionActual() {
		return exploracionActual;
	}
}