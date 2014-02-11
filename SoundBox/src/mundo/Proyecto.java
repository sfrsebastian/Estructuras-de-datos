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
	private static final String RUTA_GUARDADO = "./data/Proyectos/";
	private String autor;
	private String nombre;
	private Lista <Canal> canales;
	private Duration duracion;
	private Date fechaCreacion;

	public Proyecto(String nAutor, String nNombre, int numCanales){
		autor = nAutor;
		nombre = nNombre;
		canales = new Lista <Canal>();
		for(int i = 0; i<numCanales;i++){
			canales.agregar(new Canal());
		}
		duracion = new Duration(0.0);
		Calendar c = Calendar.getInstance();
    	fechaCreacion = c.getTime();
	}
	/**
	 * 
	 * @param nProyecto    p
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
	 * 
	 * @param nCanal    c
	 */
	public Canal agregarCanal(Canal nCanal){
		return canales.agregar(nCanal);
	}

	public Canal eliminarCanal(Canal nCanal){
		return canales.eliminar(nCanal);
	}

	/**
	 * 
	 * @param nSonidos
	 * @param nCanal    c
	 */
	public Canal agregarSonidosACanal(Sample[] nSonidos, Canal nCanal){
		for(int i = 0; i<nSonidos.length;i++){
			nCanal.agregarSonido(nSonidos[i]);
		}
		return nCanal;
	}
	
	public  String darNombre() {
		return nombre;
	}

	public void cambiarNombre(String nNombre) {
		nombre = nNombre;
	}
	public String darAutor() {
		return autor;
	}

	public void cambiarAutor(String nAutor){
		autor = nAutor;
	}
	public Date darFechaCreacion() {
		return fechaCreacion;
	}

	public double darDuracion() {
		Object[] lista  = canales.darArreglo();
		double segundos = 0.0;
		for(int i = 0; i<lista.length;i++){
			Canal actual = (Canal)lista[i];
			segundos+=actual.darDuracionTotal();
		}
		return segundos;
	}
	
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
	
	public String toString(){
		return nombre + " - " + autor;
	}
	public Object[] darCanales() {
		return canales.darArreglo();
	}
}
