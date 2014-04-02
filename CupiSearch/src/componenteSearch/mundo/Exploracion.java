package componenteSearch.mundo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ArbolAVl.ArbolBinarioAVLOrdenado;
import ArbolBinOrdenado.IArbolBinarioOrdenado;

public class Exploracion implements Comparable<Exploracion>, Serializable {

	//------------------------------------------
	// Atributos
	//------------------------------------------
	/**
	 * El tiempo total de busqueda de los recursos
	 */
	private long tiempoTotal;

	/**
	 * La cantidad de busquedas realizadas en la exploracion
	 */
	private int busquedasRealizadas;

	/**
	 * Los recursos de la exploracion
	 */
	private IArbolBinarioOrdenado<Recurso> recursos;
	
	/**
	 * Las fuentes utilizdas en la exploracion
	 */
	private ArbolBinarioAVLOrdenado<String> fuentes;
	
	/**
	 * La fecha de creacion de la exploracion
	 */
	private Date fecha;
	
	//------------------------------------------
	// Constructor
	//------------------------------------------
	/**
	 * Crea una nueva exploracion a partir del arbol de recurso y el tiempo dados por parametro.
	 * @param arbolRecursos El arbol con los recursos
	 * @param tiempo El tiempo de lae exploracion
	 */
	public Exploracion(ArbolBinarioAVLOrdenado<Recurso> arbolRecursos, long tiempo) {
		recursos = arbolRecursos;
		tiempoTotal = tiempo;
		busquedasRealizadas = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		fecha = new Date();
		dateFormat.format(fecha);
		fuentes = new ArbolBinarioAVLOrdenado<String>();
	}

	/**
	 * Agrega la fuente dada por parametro al arbol de fuentes
	 * @param url el url de la fuente
	 */
	public void agregarFuente(String url){
		fuentes.agregar(url);
	}
	
	/**
	 * Retorna la cantidad de recursos de la exploracion
	 * @return La cantidad de recrursos
	 */
	public int darCantidadRecursos(){
		return recursos.darPeso();
	}
	
	/**
	 * Aumenta el contador de busquedas realizadas
	 */
	public void aumentarBusqueda(){
		busquedasRealizadas++;
	}

	/**
	 * Retorna el arbol de recursos
	 * @return El arbol de recursos
	 */
	public IArbolBinarioOrdenado<Recurso> getRecursos() {
		return recursos;
	}

	/**
	 * Retorna el tiempo total de la exploracion
	 * @return el tiempo de la exploracion
	 */
	public long getTiempoTotal() {
		return tiempoTotal/1000;
	}

	/**
	 * Retorna el contador de busquedas realizadas
	 * @return
	 */
	public int getBusquedasRealizadas() {
		return busquedasRealizadas;
	}
	
	/**
	 * Retorna las fuentes utilizadas en la expliracion
	 * @return las fuentes de la exploracion
	 */
	public ArbolBinarioAVLOrdenado<String> getFuentes(){
		return fuentes;
	}

	/**
	 * Compara dos exploraciones a partir de sus cantidad de recrusos
	 */
	public int compareTo(Exploracion o) {
		if(darCantidadRecursos() > o.darCantidadRecursos())
			return 1;
		else if (darCantidadRecursos() < o.darCantidadRecursos())
			return -1;
		else
			return 0;
	}

	/**
	 * Metodo to string de la exploracion
	 */
	public String toString(){
		return fecha + "";
	}
}

