package mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.util.Duration;
import Lista.Lista;

public class Proyecto implements Comparable <Proyecto>, Serializable {
	
	//Atributos
	/**
	 * La ruta para almacenar el proyecto.
	 */
	private static final String RUTA_GUARDADO = "./data/Proyectos/";
	
	/**
	 * El autor del proyecto
	 */
	private String autor;
	
	/**
	 * El nombre del proyecto
	 */
	private String nombre;
	
	/**
	 * Los canales del proyecto
	 */
	private Lista <Canal> canales;
	
	/**
	 * La duracion del proyecto
	 */
	private double duracion;
	
	/**
	 * La fecha de creacion del proyecto
	 */
	private Date fechaCreacion;

	
	//Constructor
	/**
	 * Crea un nuevo proyecto con los parametros dados por parametro.
	 * @param nAutor
	 * @param nNombre
	 * @param numCanales
	 */
	public Proyecto(String nAutor, String nNombre, int numCanales){
		autor = nAutor;
		nombre = nNombre;
		canales = new Lista <Canal>();
		for(int i = 0; i<numCanales;i++){
			canales.agregar(new Canal());
		}
		duracion = 0.0;
		Calendar c = Calendar.getInstance();
    	fechaCreacion = c.getTime();
	}
	
	//Metodos
	/**
	 * Guarda el proyecto en la ruta predeterminada
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
	 * Agrega un canal al proyecto
	 * @param El canal a agregar
	 * @return El canal agregado
	 */
	public Canal agregarCanal(Canal nCanal){
		return canales.agregar(nCanal);
	}

	/**
	 * Elimina el canal dado por parametro
	 * @param nCanal El canal a eliminar
	 * @return El canal eliminado
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
	 * Cambia el nombre del proyecto por el dado de parametro.
	 * @param nNombre El nuevo nombre del proyecto.
	 */
	public void cambiarNombre(String nNombre) {
		nombre = nNombre;
	}
	
	/**
	 * Retorna el autor del Proyecto
	 * @return El nuevo autor del proyecto
	 */
	public String darAutor() {
		return autor;
	}

	/**
	 * Cambia el nombre del autor del proyecto
	 * @param nAutor El nuevo autor del proyecto
	 */
	public void cambiarAutor(String nAutor){
		autor = nAutor;
	}
	
	/**
	 * Retorna la fecha de creacion del proyecto
	 * @return La fecha de creacion del proyecto
	 */
	public Date darFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Retorna la duracion total del proyecto
	 * @return La duracion del proyecto
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
	 * Metodo de comparar Metodo para comparar un proyecto
	 */
	@Override
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
	 * Metodo que reproduce todos los canales del proyecto
	 */
	public void reproducir(){
		Object[] nCanales = canales.darArreglo();
		
		for (int i = 0; i < nCanales.length; i++) {
			Canal actual = (Canal)nCanales[i];
			actual.reproducir();
		}
	}
	
	/**
	 * Metodo que pausa los canales del proyecto
	 */
	public void pausar(){
		Object[] nCanales = canales.darArreglo();
		
		for (int i = 0; i < nCanales.length; i++) {
			Canal actual = (Canal)nCanales[i];
			actual.pausar();
		}
	}
	
	/**
	 * Metodo que para todos los canales del proyecto
	 */
	public void parar(){
		Object[] nCanales = canales.darArreglo();
		
		for (int i = 0; i < nCanales.length; i++) {
			Canal actual = (Canal)nCanales[i];
			actual.stop();
		}
	}
	
	/**
	 * Metodo to string del proyecto
	 */
	public String toString(){
		return nombre + " - " + autor;
	}
	
	/**
	 * metodo que retorna los canales del proyecto
	 * @return
	 */
	public Object[] darCanales() {
		return canales.darArreglo();
	}
	/**
	 * MEtodo que elimina un sonido de su respectivo canal
	 * @param sonido EL sonido a elimianar.
	 */
	public void eliminarSonidosDeCanal(Sample sonido) {
		Object[] listaCanales = canales.darArreglo();
		
		for (int i = 0; i < listaCanales.length; i++) {
			Canal canalActual = (Canal)listaCanales[i];
			canalActual.eliminarSonido(sonido);
		}
	}
}
