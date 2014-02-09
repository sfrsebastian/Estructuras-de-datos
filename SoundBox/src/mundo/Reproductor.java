package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
				
			}
		}
		else{
			sonidos = new ListaOrdenada <Sample>();
			categorias = new ListaOrdenada<Categoria>();
		}
		
	}
	
	private void cargarProyectos() {
		File folder = new File(RUTA_PROYECTOS);
		if(folder.exists()){
			File[] files = folder.listFiles();
			for(int i = 0; i<files.length;i++){
				try{
					ObjectInputStream ois = new ObjectInputStream( new FileInputStream( files[i] ) );
					Proyecto actual = (Proyecto) ois.readObject( );
					proyectos.agregar(actual);
					ois.close();
				}
				catch(Exception e){
					
				}
			}
		}
		else{
			proyectos = new ListaOrdenada <Proyecto>();
			folder.mkdirs();
		}	
	}
	
	@Override
	public void abrirProyecto(Proyecto nProyecto){
		proyectoActual = nProyecto;
	}
	
	@Override
	public Proyecto darProyectoActual(){
		return proyectoActual;
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
			Sample nuevo = new Sample(nSonidos[i], vacia);
			sonidos.agregar(nuevo);
		}
		return true;
	}

	/**
	 * 
	 * @param nCategoria    s
	 */
	@Override
	public Categoria agregarCategoria(Categoria nCategoria){
		categorias.agregar(nCategoria);
		return nCategoria;
	}

	@Override
	public Categoria eliminarCategoria(Categoria nCategoria){
		nCategoria.eliminarDeSonidos();
		return categorias.eliminar(nCategoria);
	}
	/**
	 * 
	 * @param nCategoria
	 * @param nSonido    sa
	 */
	@Override
	public Categoria asignarCategoria(Categoria nCategoria, Sample nSonido){
		nCategoria.agregarSonido(nSonido);
		return nSonido.agregarCategoria(nCategoria);
	}

	/**
	 * 
	 * @param nAutor    autor
	 */
	@Override
	public Proyecto buscarProyectoPorAutor(String nAutor){
		Proyecto[] lista = (Proyecto[]) proyectos.darElementos();
		for(int i = 0; i<lista.length;i++){
			if(lista[i].darAutor() == nAutor){
				return lista[i];
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
		Proyecto[] lista = (Proyecto[]) proyectos.darElementos();
		for(int i = 0; i<lista.length;i++){
			if(lista[i].darNombre() == nNombre){
				return lista[i];
			}
		}
		return null;
	}
}