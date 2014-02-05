

/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 10:04:54 PM
 */
public interface ISoundBox {

	/**
	 * Abre un nuevo proyecto guardado <br>
	 * @param nReproductor:  El proyecto a abrir != null <br>
	 * @return Reproductor: El proyecto que se habia guardado 
	 */
	public Reproductor abrirProyecto(Reproductor nReproductor);

	/**
	 * Agrega un nuevo canal a la lista de canales del proyecto
	 * @param nCanal El canal que se quiere agregar
	 * @return Canal: El canal que ha sido agregado
	 */
	public Canal agregarCanal(Canal nCanal);

	/**
	 * Agrega una nueva categoria a la lista de categorias del reproductor <br>
	 * @param nCategoria La nueva categoria que se quiere anadir nCategoria != "" && nCategoria !+ null <br>
	 * @return Categoria: La categoria anadida
	 */
	public Categoria agregarCategoria(Categoria nCategoria);

	/**
	 * Agrega un nuevo sonido a la lista de sonidos de un canal <br>
	 * @param nSonidos El nuevo sonido que se quiere anadir al canal <br>
	 * @param nCanal El canal al cual se le van a anadir los/el sonido/s <br>
	 * @return Sample El sonido anadido al canal
	 */
	public Sample agregarSonido(Sample[] nSonidos, Canal nCanal);

	/**
	 * Asigna una categoria a un sonido <br>
	 * @param nCategoria La categoria que se quiere asignar <br>
	 * @param nSonido El sonido al que se le va a asginar la categoria <br>
	 * @return TRUE si fue exitoso, FALSE en caso contrario
	 */
	public boolean asignarCategoria(Categoria nCategoria, Sample nSonido);

	/**
	 * 
	 * @param nBpm    d
	 */
	public double aumentarBpm(double nBpm);

	/**
	 * 
	 * @param nAutor    autor
	 */
	public Proyecto buscarProyectoPorAutor(String nAutor);

	/**
	 * 
	 * @param nNombre    nombre
	 */
	public Proyecto buscarProyectoPorNombre(String nNombre);

	public Reproductor crearProyecto();

	/**
	 * 
	 * @param nReproductor    p
	 * @param nNombre
	 * @param nAutor
	 */
	public Reproductor editarProyecto(Reproductor nReproductor, String nNombre, String nAutor);

	/**
	 * 
	 * @param nCanal    c
	 */
	public Canal eliminarCanal(Canal nCanal);

	/**
	 * 
	 * @param nCategoria    s
	 */
	public Categoria eliminarCategoria(Categoria nCategoria);

	/**
	 * @param String
	 * 
	 * @param nCategoria
	 */
	public Categoria[] filtrarSonidos(Categoria nCategoria);

	/**
	 * 
	 * @param nProyecto    p
	 */
	public Reproductor guardarProyecto(Reproductor nProyecto);

	public Duration pausar();

	/**
	 * 
	 * @param nDuracion    d
	 */
	public void reproducir(Duration nDuracion);

	public void stop();

}