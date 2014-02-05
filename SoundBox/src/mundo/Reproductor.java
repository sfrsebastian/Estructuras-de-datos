package mundo;

import java.util.Date;

import javax.xml.datatype.Duration;



/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Reproductor implements ISoundBox {

	private String autor;
	private double bpm;
	private Canal canales;
	private Categoria categorias;
	private Duration duracion;
	private Date fechaCreacion;
	private String nombre;
	private Reproductor proyectoAbierto;
	private Sample sonidos;

	public Reproductor(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param nNombre
	 * @param nAutor
	 * @param nNumeroCanales
	 */
	public void Reproductor(String nNombre, String nAutor, int nNumeroCanales){

	}

	public Sample[] darSonidos(){
		return null;
	}

	/**
	 * 
	 * @param nCanal    c
	 */
	public Canal eliminarCanal(Canal nCanal){
		return null;
	}

	/**
	 * 
	 * @param nCategoria    s
	 */
	public Categoria eliminarCategoria(Categoria nCategoria){
		return null;
	}

	/**
	 * @param String
	 * 
	 * @param nCategoria
	 */
	public Categoria[] filtrarSonidos(Categoria nCategoria){
		return null;
	}

	/**
	 * 
	 * @param nProyecto    p
	 */
	public Reproductor guardarProyecto(Reproductor nProyecto){
		return null;
	}

	public Duration pausar(){
		return null;
	}

	/**
	 * 
	 * @param nDuracion    d
	 */
	public void reproducir(Duration nDuracion){

	}

	public void stop(){

	}

	/**
	 * 
	 * @param nReproductor    El proyecto a abrir != null
	 */
	public Reproductor abrirProyecto(Reproductor nReproductor){
		return null;
	}

	/**
	 * 
	 * @param nCanal    c
	 */
	public Canal agregarCanal(Canal nCanal){
		return null;
	}

	/**
	 * 
	 * @param nCategoria    s
	 */
	public Categoria agregarCategoria(Categoria nCategoria){
		return null;
	}

	/**
	 * 
	 * @param nSonidos
	 * @param nCanal    c
	 */
	public Sample agregarSonido(Sample[] nSonidos, Canal nCanal){
		return null;
	}

	/**
	 * 
	 * @param nCategoria
	 * @param nSonido    sa
	 */
	public boolean asignarCategoria(Categoria nCategoria, Sample nSonido){
		return false;
	}

	/**
	 * 
	 * @param nBpm    d
	 */
	public double aumentarBpm(double nBpm){
		return 0;
	}

	/**
	 * 
	 * @param nAutor    autor
	 */
	public Reproductor buscarProyectoPorAutor(String nAutor){
		return null;
	}

	/**
	 * 
	 * @param nNombre    nombre
	 */
	public Reproductor buscarProyectoPorNombre(String nNombre){
		return null;
	}

	public Reproductor crearProyecto(String autor, String nombre, int numCanales) {
		// TODO Auto-generated method stub
		return null;
	}

	public Reproductor editarProyecto(Reproductor nReproductor, String nNombre,
			String nAutor) {
		// TODO Auto-generated method stub
		return null;
	}

}