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
	public Object[] buscarResultados(String[] criterios, String valor) {
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