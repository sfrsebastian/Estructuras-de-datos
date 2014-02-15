package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Lista.Lista;
import ListaOrdenada.ListaOrdenada;

/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Reproductor implements ISoundBox {

	/**
	 * Constante que representa la carpeta donde se almacenan los proyectos
	 */
	private static final String RUTA_PROYECTOS = "./data/Proyectos/";
	
	/**
	 * Constante que representa La ruta donde se almacenan las propiedades del reproductor
	 */
	private static final String RUTA_PROPIEDADES = "./data/reproductorProperties.rp/";
	
	/**
	 * La lista ordenada de proyectos
	 */
	private ListaOrdenada <Proyecto> proyectos;
	
	/**
	 * La lista ordenada de categorias
	 */
	private ListaOrdenada <Categoria> categorias;
	
	/**
	 * La lista ordenada de sonidos
	 */
	private ListaOrdenada <Sample> sonidos;
	
	/**
	 * EL proyecto actualmente abierto
	 */
	private Proyecto proyectoActual;
	
	
	/**
	 * Crea un nuevo reproductor.<br>
	 * Inicializa la listas y carga propiedades.
	 */
	public Reproductor(){
		proyectoActual = null;
		proyectos = new ListaOrdenada <Proyecto>();
		sonidos = new ListaOrdenada<Sample>();
		categorias = new ListaOrdenada<Categoria>();
		cargarPropiedades();
		cargarProyectos();
	}
	
	/**
	 * Carga las propiedades desde la ruta
	 */
	@SuppressWarnings("unchecked")
	private void cargarPropiedades() {
		File propiedades = new File(RUTA_PROPIEDADES);
		if(propiedades.exists()){
			try{
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( propiedades ) );
				sonidos = (ListaOrdenada<Sample>) ois.readObject();
				categorias = (ListaOrdenada<Categoria>) ois.readObject();
				ois.close();
			}
			catch(Exception e){
				System.out.println("Error al cargar los sonidos - Clase Reproductor");
			}
		}
	}
	
	/**
	 * Carga los proyectos de la ruta
	 */
	private void cargarProyectos() {
		File folder = new File(RUTA_PROYECTOS);
		boolean agrego = false;
		
		if(folder.exists()){
			File[] files = folder.listFiles();
			for(int i = 0; i<files.length;i++){
				try{
					ObjectInputStream ois = new ObjectInputStream( new FileInputStream( files[i] ) );
					Proyecto actual = (Proyecto) ois.readObject( );
					proyectos.agregar(actual);
					ois.close();
					agrego = true;
				}
				catch(Exception e){
					//System.out.println("Catched!");
				}
			}
		}
		if(!agrego){
			folder.mkdirs();
		}	
	}
	
	/**
	 * Agrega un nuevo proyecto al reproductor
	 * @param nombre El nombre del proyecto
	 * @param autor El autor del proyecto
	 * @param numeroCanales el umero de canales del proyecto
	 * @return El proyecto agregado
	 */
	public Proyecto agregarProyecto(String nombre, String autor, int numeroCanales){
		Proyecto p = new Proyecto(autor, nombre, numeroCanales);
		proyectos.agregar(p);
		return p;
	}
	
	/**
	 * Abre el proyecto dado por parametro
	 * @pos EL proyecto actual cambia al nuevo
	 */
	public void abrirProyecto(Proyecto nProyecto){
		proyectoActual = nProyecto;
	}
	
	/**
	 * Retorna el proyecto actualmeente abierto
	 */
	public Proyecto darProyectoActual(){
		return proyectoActual;
	}
	
	/**
	 * Retorna los canales del proyecto actual
	 * @return los can
	 */
	public Object[] darCanales(){
		return proyectoActual.darCanales();
	}
	
	/**
	 * Retorna los proyectos del reproductor
	 * @return
	 */
	public Object[] darProyectos(){
		return proyectos.darElementos();
	}
	
	/**
	 * Filtra los sonidos por la categoria dada por parametro
	 * @param nCategoria La categoria para el filtro
	 * @return Un arreglo con los sonidos que cumplen el criterio.
	 */
	public Object[] filtrarSonidosPorCategoria(Categoria nCategoria){
		return nCategoria.darSonidos();
	}

	/**
	 * Agrega los sonidos dados por parametro a la libreria
	 * @param nSonidos Los sonidos a agregar
	 * @return true si se agregan correctamente, false de lo contrario.
	 */
	public boolean agregarSonidosALibreria(File[] nSonidos){
		Categoria vacia = new Categoria();

		for(int i = 0; i< nSonidos.length; i++){
			try{
				Sample nuevo = new Sample(nSonidos[i], vacia);
				sonidos.agregar(nuevo);
			}catch(Exception e){

			}
		}
		guardarSonidos();

		return true;
	}

	/**
	 * Guarda los sonidos y categorias en un archivo serializado.
	 */
	public void guardarSonidos(){
		File f = new File(RUTA_PROPIEDADES);
		try {
			ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(f));
			out.writeObject(sonidos);
			out.writeObject(categorias);
			out.close();
		} 
		catch (IOException e) {
		}
	}
	
	/**
	 * Agrega una nueva categoria a la lista de categorias del reproductor <br>
	 * pre: La lista de canales ha sido inicializada
	 * @param nCategoria La nueva categoria que se quiere anadir nCategoria != "" && nCategoria !+ null <br>
	 * @return Categoria: La categoria anadida
	 */
	public Categoria agregarCategoria(Categoria nCategoria){
		categorias.agregar(nCategoria);
		guardarSonidos();
		return nCategoria;
	}

	/**
	 * Elimina la categoria dada por parametro del reproductor.
	 * @param nCategoria La categoria a eliminar
	 * @return La categoria eliminada.
	 */
	public Categoria eliminarCategoria(Categoria nCategoria){
		nCategoria.eliminarDeSonidos();
		Categoria c = categorias.eliminar(nCategoria);
		guardarSonidos();
		return c;
	}
	
	/**
	 * Asigna una categoria a un sonido <br>
	 * @param nCategoria La categoria que se quiere asignar <br>
	 * @param nSonido El sonido al que se le va a asginar la categoria <br>
	 * @return TRUE si fue exitoso, FALSE en caso contrario
	 */
	public Categoria asignarCategoria(Categoria nCategoria, Sample nSonido){
		nCategoria.agregarSonido(nSonido);
		Categoria c = nSonido.agregarCategoria(nCategoria);
		guardarSonidos();
		
		return	c;
	}

	/**
	 * Busca los proyectos por autor <br>
	 * @param nAutor El autor del proyecto que se quiere buscar <br>
	 * @return El proyecto encontrado, null en caso contrario
	 */
	public Proyecto buscarProyectoPorAutor(String nAutor){
		Object[] lista = proyectos.darElementos();
		for(int i = 0; i<lista.length;i++){
			if(((Proyecto) lista[i]).darAutor().equals(nAutor)){
				return (Proyecto) lista[i];
			}
		}
		return null;
	}

	/**
	 * Busca el proyecto con el nombre dado por parametro <br>
	 * @param nNombre El nombre del proyecto que se quiere buscar <br>
	 * @return Proyecto: El proyecto encontrado, null en caso contrario
	 */
	public Proyecto buscarProyectoPorNombre(String nNombre){
		Object[] lista = proyectos.darElementos();
		for(int i = 0; i<lista.length;i++){
			if(((Proyecto) lista[i]).darNombre().equals(nNombre)){
				return (Proyecto) lista[i];
			}
		}
		return null;
	}
	
	/**
	 * Retorna los sonidos del reproductor
	 * @return Los sonidos de la biblioteca
	 */
	public Object[] darSonidos() {
		return sonidos.darElementos();
	}
	
	/**
	 * Retorna las categorias del reproductor
	 * @return Las categorias del reproductor
	 */
	public Object[] darCategorias() {
		return categorias.darElementos();
	}
	
	/**
	 * Elimina el sonido dado por parametro
	 * @param sonido EL sonido a eliminar
	 */
	public void eliminarSonido(Sample sonido) {
		sonidos.eliminar(sonido);
		Object[] nCategorias = categorias.darElementos();
		for (int i = 0; i < nCategorias.length; i++) {
			Categoria actual = (Categoria)nCategorias[i];
			Object[] sonidos = actual.darSonidos(); 
			for (int j = 0; j < sonidos.length; j++) {
				Sample actual1 = (Sample) sonidos[j];
				if(sonido.darNombre().equals(actual1.darNombre())){
					actual.eliminarSonido(actual1);
				}
			}
		}
	}
	
	/**
	 * Elimina el proyecto dado por parametro
	 * @param proyecto El proyecto a eliminar.
	 */
	public void eliminarProyecto(Proyecto proyecto) {
		proyectos.eliminar(proyecto);
	}
	
	/**
	 * Retorna la categoria segun el nombre dado por parametro
	 * @param nombre el nombre de la categoria
	 * @return La categoria encontrada
	 */
	public Categoria darCategoriaPorNombre(String nombre){
		Object[] cats = categorias.darElementos();
		for (int i = 0; i < cats.length; i++) {
			Categoria t = (Categoria)cats[i];
			if(t.darNombre().equals(nombre)){
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Filtra los sonidos segun el nombre dado por parametro
	 * @param filtro El filtro de los sonidos
	 * @return Un arreglo de sonidos que cumple con el filtro
	 */
	public Object[] filtrarSonidosPorNombre(String filtro) {
		Object[] lista = sonidos.darElementos();
		if(lista.length > 0){
			Lista<Sample> listaRetorno = new Lista<Sample>();
			for(int i = 0; i<lista.length;i++){
				if(((Sample) lista[i]).darNombre().equals(filtro)){
					listaRetorno.agregar((Sample) lista[i]);
				}
			}
			return listaRetorno.darArreglo();
		}
		return null;
	}
}
