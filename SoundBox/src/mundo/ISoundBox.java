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
	public void abrirProyecto(File f);

	/**
	 * Agrega un nuevo canal a la lista de canales del proyecto
	 * pre: La lista de canales ha sido inicializada
	 * @param nCanal El canal que se quiere agregar
	 * @return Canal: El canal que ha sido agregado
	 */
	public Canal agregarCanal(Canal nCanal);

	/**
	 * Agrega una nueva categoria a la lista de categorias del reproductor <br>
	 * pre: La lista de canales ha sido inicializada
	 * @param nCategoria La nueva categoria que se quiere anadir nCategoria != "" && nCategoria !+ null <br>
	 * @return Categoria: La categoria anadida
	 */
	public Categoria agregarCategoria(Categoria nCategoria);

	/**
	 * Asigna una categoria a un sonido <br>
	 * @param nCategoria La categoria que se quiere asignar <br>
	 * @param nSonido El sonido al que se le va a asginar la categoria <br>
	 * @return TRUE si fue exitoso, FALSE en caso contrario
	 */
	public Sample asignarCategoria(Categoria nCategoria, Sample nSonido);

	/**
	 * pre: El proyecto ya esta cargado <br>
	 * Cambia el bpm/tempo de reproduccion del proyecto <br>
	 * @param nBpm El nuevo tempo de reproduccion <br>
	 * @return El bpm que se ha cambiado
	 */ 
	public void aumentarBpm();

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
	 * Crea un nuevo proyecto con un autor, nombre y numero inicial de canales 
	 * @param autor El autor del proyecto
	 * @param nombre El nombre del proyecto
	 * @param numCanales El numero inicial de canales que va a tener el proyecto
	 * @return Reproductor: Un nuevo proyecto inicializado
	 */
	public Reproductor crearProyecto(String autor, String nombre, int numCanales);

	/**
	 * Edita un proyecto dado el reproductor y los cambios
	 * @param nReproductor El reproductor que se quiere editar
	 * @param nNombre El nuevo nombre del proyecto
	 * @param nAutor El nuevo autor del proyecto
	 */
	public void editarProyecto(String nNombre, String nAutor);

	/**
	 * Elimina el canal dado por parametro
	 * pre: La lista de canales ha sido inicializada
	 * @param nCanal El canal que se quiere eliminar
	 * @return El canal eliminado, null en caso contrario
	 * post: Se ha eliminado un canal de la lista
	 */
	public Canal eliminarCanal(Canal nCanal);

	/**
	 * Elimina la categoria dada por parametro
	 * pre: La lista de categorias ha sido inicializada
	 * @param nCategoria La categoria que se quiere eliminar
	 * @return La categoria eliminada, null en caso contrario
	 * post: Se ha eliminado una categoria de la lista
	 */
	public Categoria eliminarCategoria(Categoria nCategoria);

	/**
	 * Guarda el proyecto
	 * @param nProyecto El proyecto que se quiere guadar
	 * @return Retorna el reproductor guardado
	 * post: Se ha generado un archivo serializado con los datos del proyecto
	 */
	public boolean guardarProyecto();

	/**
	 * Pausa el proyecto en la duracion actual
	 * @return Duration: La duracion en la cual se pauso el proyecto
	 */
	public void pausar();

	/**
	 * Reproduce el proyecto desde una duracion dada por parametro
	 * @param nDuracion La duracion desde la cual inicia la reproduccion
	 */
	public void reproducir(Duration nDuracion);

	/**
	 * Frena la reproduccion totalmente del proyecto
	 */
	public void stop();

	public Sample agregarSonidoACanal(Sample nSonido, Canal nCanal);

	public Sample agregarSonidoALibreria(Sample nSonido);

	public Canal agregarSonidosACanal(Sample[] nSonidos, Canal nCanal);

	public Sample[] filtrarSonidosPorCategoria(Categoria nCategoria);

	public Sample[] darSonidos();

	boolean agregarSonidosALibreria(File[] nSonidos);



}