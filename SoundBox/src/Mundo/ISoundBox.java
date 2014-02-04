package Mundo;
import javax.xml.datatype.Duration;

/**
 * @author Sebastian
 * @version 1.0
 * @created 03-Feb-2014 8:12:14 PM
 */
public interface ISoundBox {

	/**
	 * 
	 * @param nProyecto El proyecto a abrir != null
	 */
	public Proyecto abrirProyecto(Proyecto nProyecto);

	/**
	 * 
	 * @param nCanal 
	 */
	public Canal agregarCanal(Canal nCanal);

	/**
	 * 
	 * @param nCategoria 
	 */
	public String agregarCategoria(String nCategoria);

	/**
	 * 
	 * @param nSonidos
	 * @param nCanal 
	 */
	public Sample agregarSonido(Sample[] nSonidos, Canal nCanal);

	/**
	 * 
	 * @param nCategoria
	 * @param nSonido 
	 */
	public String asignarCategoria(String nCategoria, Sample nSonido);

	/**
	 * 
	 * @param nBpm 
	 */
	public double aumentarBpm(double nBpm);

	/**
	 * 
	 * @param nAutor 
	 */
	public Proyecto buscarProyectoPorAutor(String nAutor);

	/**
	 * 
	 * @param nNombre 
	 */
	public Proyecto buscarProyectoPorNombre(String nNombre);

	public Proyecto crearProyecto();

	/**
	 * 
	 * @param nProyecto 
	 */
	public Proyecto editarProyecto(Proyecto nProyecto);

	/**
	 * 
	 * @param nCanal 
	 */
	public Canal eliminarCanal(Canal nCanal);

	/**
	 * 
	 * @param nCategoria 
	 */
	public String eliminarCategoria(String nCategoria);

	/**
	 * @param String
	 * 
	 * @param nCategoria
	 */
	public Sample[] filtrarSonidos(String nCategoria);

	/**
	 * 
	 * @param nProyecto    p
	 */
	public Proyecto guardarProyecto(Proyecto nProyecto);

	public Duration pausar();

	/**
	 * 
	 * @param nDuracion    d
	 */
	public void reproducir(Duration nDuracion);

	public void stop();

}