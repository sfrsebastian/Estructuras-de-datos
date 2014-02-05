

/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 10:04:54 PM
 */
public interface ISoundBox {

	/**
	 * 
	 * @param nReproductor    El proyecto a abrir != null
	 */
	public Reproductor abrirProyecto(Reproductor nReproductor);

	/**
	 * 
	 * @param nCanal    c
	 */
	public Canal agregarCanal(Canal nCanal);

	/**
	 * 
	 * @param nCategoria    s
	 */
	public Categoria agregarCategoria(Categoria nCategoria);

	/**
	 * 
	 * @param nSonidos
	 * @param nCanal    c
	 */
	public Sample agregarSonido(Sample[] nSonidos, Canal nCanal);

	/**
	 * 
	 * @param nCategoria
	 * @param nSonido    sa
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