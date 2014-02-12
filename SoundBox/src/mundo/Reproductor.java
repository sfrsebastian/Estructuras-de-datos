package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ListaOrdenada.ListaOrdenada;

/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Reproductor implements ISoundBox {

	private static final String RUTA_PROYECTOS = "./data/Proyectos/";
	private static final String RUTA_PROPIEDADES = "./data/reproductorProperties.rp/";
	private ListaOrdenada <Proyecto> proyectos;
	private ListaOrdenada <Categoria> categorias;
	private ListaOrdenada <Sample> sonidos;
	private Proyecto proyectoActual;
	
	public Reproductor(){
		proyectoActual = null;
		proyectos = new ListaOrdenada <Proyecto>();
		sonidos = new ListaOrdenada<Sample>();
		categorias = new ListaOrdenada<Categoria>();
		cargarPropiedades();
		cargarProyectos();
	}
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
	
	public Proyecto agregarProyecto(String nombre, String autor, int numeroCanales){
		Proyecto p = new Proyecto(autor, nombre, numeroCanales);
		proyectos.agregar(p);
		return p;
	}
	@Override
	public void abrirProyecto(Proyecto nProyecto){
		proyectoActual = nProyecto;
	}
	
	@Override
	public Proyecto darProyectoActual(){
		return proyectoActual;
	}
	
	public Object[] darCanales(){
		return proyectoActual.darCanales();
	}
	
	public Object[] darProyectos(){
		return proyectos.darElementos();
	}
	/**
	 * @param String
	 * 
	 * @param nCategoria
	 */
	@Override
	public Sample[] filtrarSonidosPorCategoria(Categoria nCategoria){
		return nCategoria.darSonidos();
	}

	@Override
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
	 * 
	 * @param nCategoria    s
	 */
	@Override
	public Categoria agregarCategoria(Categoria nCategoria){
		categorias.agregar(nCategoria);
		guardarSonidos();
		return nCategoria;
	}

	@Override
	public Categoria eliminarCategoria(Categoria nCategoria){
		nCategoria.eliminarDeSonidos();
		Categoria c = categorias.eliminar(nCategoria);
		guardarSonidos();
		return c;
	}
	/**
	 * 
	 * @param nCategoria
	 * @param nSonido    sa
	 */
	@Override
	public Categoria asignarCategoria(Categoria nCategoria, Sample nSonido){
		nCategoria.agregarSonido(nSonido);
		Categoria c = nSonido.agregarCategoria(nCategoria);
		guardarSonidos();
		
		return	c;
	}

	/**
	 * 
	 * @param nAutor    autor
	 */
	@Override
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
	 * 
	 * @param nNombre    nombre
	 */
	@Override
	public Proyecto buscarProyectoPorNombre(String nNombre){
		Object[] lista = proyectos.darElementos();
		for(int i = 0; i<lista.length;i++){
			if(((Proyecto) lista[i]).darNombre().equals(nNombre)){
				return (Proyecto) lista[i];
			}
		}
		return null;
	}
	public Object[] darSonidos() {
		return sonidos.darElementos();
	}
	
	public Object[] darCategorias() {
		return categorias.darElementos();
	}
	public void eliminarSonido(Sample sonido) {
		sonidos.eliminar(sonido);
	}
	public void eliminarProyecto(Proyecto proyecto) {
		proyectos.eliminar(proyecto);
	}
}
