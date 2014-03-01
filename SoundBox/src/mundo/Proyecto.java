package mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import Lista.Lista;
import ListaEncadenada.ListaEncadenada;

public class Proyecto implements Comparable <Proyecto>, Serializable {
	private static final String RUTA_GUARDADO = "./data/Proyectos/";
	private String autor;
	private String nombre;
	private Lista <Canal> canales;
	private double duracion;
	private Date fechaCreacion;

	/**
	 * Crea un nuevo proyecto
	 * @param nAutor El autor del proyecto
	 * @param nNombre EL nombre del proyecto
	 * @param numCanales El numero de canales para el proyecto
	 */
	public Proyecto(String nAutor, String nNombre, int numCanales){
		autor = nAutor;
		nombre = nNombre;
		canales = new ListaEncadenada <Canal>();
		for(int i = 0; i<numCanales;i++){
			canales.agregar(new Canal());
		}
		duracion = 0.0;
		Calendar c = Calendar.getInstance();
    	fechaCreacion = c.getTime();
	}
	
	/**
	 * Guarda el proyecto en la ruta dada
	 */
	public void guardarProyecto(){
		File f = new File(RUTA_GUARDADO + nombre + ".pr");
		try {
			ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(f));
			out.writeObject(this);
			out.close();
		} 
		catch (IOException e) {
		}
	}
	
	/**
	 * Agrega un nuevo canal al proyecto
	 * @param El canal a agregar
	 * @return El caal agregado, null de lo contrario
	 */
	public Canal agregarCanal(Canal nCanal){
		return canales.agregar(nCanal);
	}

	/**
	 * Elmina el canal dado por parametro
	 * @param nCanal El canal a eliminar
	 * @return El canal eliminado, null de lo contrario
	 */
	public Canal eliminarCanal(Canal nCanal){
		return canales.eliminar(nCanal);
	}
	
	/**
	 * Retorna el nombre del proyecto
	 * @return El nombre del proyecto
	 */
	public  String darNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del proyecto por le dado de parametro.
	 * @param nNombre El nuevo nombre del proyecto
	 */
	public void cambiarNombre(String nNombre) {
		nombre = nNombre;
	}
	
	/**
	 * Retorna el autor del proyecto
	 * @return El autor del proyecto
	 */
	public String darAutor() {
		return autor;
	}

	/**
	 * Cambia el autor del proyecto por el dado de parametro
	 * @param nAutor El nuevo autor
	 */
	public void cambiarAutor(String nAutor){
		autor = nAutor;
	}
	
	/**
	 * Retorna la fecha de creacion del pryecto
	 * @return la fecha de creacion del pryeto
	 */
	public Date darFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Retorna la duracion del proyecto
	 * @return La cantidad en seugundos de la duracion del proyecto
	 */
	public double darDuracion() {
		Object[] lista  = canales.darArreglo();
		double segundos = 0.0;
		for(int i = 0; i<lista.length;i++){
			Canal actual = (Canal)lista[i];
			segundos+=actual.darDuracionTotal();
		}
		duracion = segundos;
		return segundos;
	}
	
	/**
	 * Compara dos proyectos a partir de su nombre
	 */
	public int compareTo(Proyecto nProyecto) {
		if(nombre.compareTo(nProyecto.darNombre())<0){
			return -1;
		}
		else if(nombre.compareTo(nProyecto.darNombre())>0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Reproduce todos los samples del canal
	 */
	public void reproducir(){
		Object[] nCanales = canales.darArreglo();
		
		for (int i = 0; i < nCanales.length; i++) {
			Canal actual = (Canal)nCanales[i];
			actual.reproducir();
		}
	}
	
	/**
	 * Pausa el canal
	 */
	public void pausar(){
		Object[] nCanales = canales.darArreglo();
		
		for (int i = 0; i < nCanales.length; i++) {
			Canal actual = (Canal)nCanales[i];
			actual.pausar();
		}
	}
	
	/**
	 * Para el canala. Reinicia la reproduccion al primer elemento
	 */
	public void parar(){
		Object[] nCanales = canales.darArreglo();
		
		for (int i = 0; i < nCanales.length; i++) {
			Canal actual = (Canal)nCanales[i];
			actual.stop();
		}
	}
	
	/**
	 * Retora el nombre + el autor del proyecto
	 */
	public String toString(){
		return nombre + " - " + autor;
	}
	
	/**
	 * Retorna los canales del proyecto
	 * @return Los canales del proyecto
	 */
	public Object[] darCanales() {
		return canales.darArreglo();
	}
	
	/**
	 * Elimina el sonido dado de todos los canales
	 * @param sonido
	 */
	public void eliminarSonidosDeCanal(Sample sonido) {
		Object[] listaCanales = canales.darArreglo();
		
		for (int i = 0; i < listaCanales.length; i++) {
			Canal canalActual = (Canal)listaCanales[i];
			canalActual.eliminarSonido(sonido);
		}
	}
	
	/**
	 * Aumenta el bpm de todos los canales
	 * @return EL bpm actual de todos los canales
	 */
	public Double aumentarBPM() {
		Object[] nCanales = canales.darArreglo();
		if(nCanales.length > 0){

			for (int i = 0; i < nCanales.length; i++) {
				Canal actual = (Canal)nCanales[i];
				actual.aumentarBpm();
			}

			return ((Canal)nCanales[0]).darBPM();
		}else{
			return 0.0;
		}
	}

	/**
	 * Disminuye el bpm de todos los canales
	 * @return EL bpm actual de todos los canales
	 */
	public Double disminuirBPM() {
		Object[] nCanales = canales.darArreglo();
		if(nCanales.length > 0){

			for (int i = 0; i < nCanales.length; i++) {
				Canal actual = (Canal)nCanales[i];
				actual.disminuirBpm();
			}

			return ((Canal)nCanales[0]).darBPM();
		}else{
			return 0.0;
		}
	}
}
