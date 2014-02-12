package mundo;

import java.io.File;

import javafx.util.Duration;
import ListaOrdenada.ListaOrdenada;

/**
 * @author Sebastian
 * @author Felipe Otalora
 * @version 1.1
 * @created 04-Feb-2014 10:04:54 PM
 */
public interface ISoundBox {

	/**
	 * Abre un nuevo proyecto guardado <br>
	 * @param nReproductor:  El proyecto a abrir != null <br>
	 * @return Reproductor: El proyecto que se habia guardado 
	 */
	public void abrirProyecto(Proyecto nProyecto);

	/**
	 * Da el proyecto actualmente abierto
	 * @return El proyecto abierto, null de lo contrario
	 */
	public Proyecto darProyectoActual();
	
	/**
	 * Filtra los sonidos por la categoria dada por parametro
	 * @param nCategoria La categoria para el filtro
	 * @return Un arreglo con los sonidos que cumplen el criterio.
	 */
	public Sample[] filtrarSonidosPorCategoria(Categoria nCategoria);

	/**
	 * Agrega los sonidos dados por parametro a la libreria
	 * @param nSonidos Los sonidos a agregar
	 * @return true si se agregan correctamente, false de lo contrario.
	 */
	public boolean agregarSonidosALibreria(File[] nSonidos);

	/**
	 * Agrega una nueva categoria a la lista de categorias del reproductor <br>
	 * pre: La lista de canales ha sido inicializada
	 * @param nCategoria La nueva categoria que se quiere anadir nCategoria != "" && nCategoria !+ null <br>
	 * @return Categoria: La categoria anadida
	 */
	public Categoria agregarCategoria(Categoria nCategoria);

	/**
	 * Elimina la categoria dada por parametro del reproductor.
	 * @param nCategoria La categoria a eliminar
	 * @return La categoria eliminada.
	 */
	public Categoria eliminarCategoria(Categoria nCategoria);

	/**
	 * Asigna una categoria a un sonido <br>
	 * @param nCategoria La categoria que se quiere asignar <br>
	 * @param nSonido El sonido al que se le va a asginar la categoria <br>
	 * @return TRUE si fue exitoso, FALSE en caso contrario
	 */
	public Categoria asignarCategoria(Categoria nCategoria, Sample nSonido);

	/**
	 * Busca los proyectos por autor <br>
	 * @param nAutor El autor del proyecto que se quiere buscar <br>
	 * @return El proyecto encontrado, null en caso contrario
	 */
	public Proyecto buscarProyectoPorAutor(String nAutor);

	/**
	 * Busca el proyecto con el nombre dado por parametro <br>
	 * @param nNombre El nombre del proyecto que se quiere buscar <br>
	 * @return Proyecto: El proyecto encontrado, null en caso contrario
	 */
	public Proyecto buscarProyectoPorNombre(String nNombre);

	/**
	 * Retorna los sonidos del reproductor
	 * @return Los sonidos de la biblioteca
	 */
	public Object[] darSonidos();

	/**
	 * Retorna las categorias del reproductor
	 * @return Las categorias del reproductor
	 */
	public Object[] darCategorias();

	/**
	 * Elimina el sonido dado por parametro
	 * @param sonido EL sonido a eliminar
	 */
	public void eliminarSonido(Sample sonido);

	/**
	 * Elimina el proyecto dado por parametro
	 * @param proyecto El proyecto a eliminar.
	 */
	public void eliminarProyecto(Proyecto proyecto);

	/**
	 * Guarda los sonidos y categorias en un archivo serializado.
	 */
	public void guardarSonidos();

}