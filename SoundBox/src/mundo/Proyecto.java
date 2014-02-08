package mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

import javafx.util.Duration;
import Lista.Lista;
import ListaOrdenada.ListaOrdenada;

public class Proyecto implements Comparable <Proyecto> {
	private static final String RUTA_GUARDADO = "./data/Proyectos/";
	private String autor;
	private String nombre;
	private double bpm;
	private Lista <Canal> canales;
	private Duration duracion;
	private Date fechaCreacion;

	public Proyecto(String nAutor, String nNombre, int numCanales){
		autor = nAutor;
		nombre = nNombre;
		bpm = 1.0;
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

	public Sample agregarSonidoACanal(Sample nSonido, Canal nCanal){
		return nCanal.agregarSonido(nSonido);
	}

	/**
	 * 
	 * @param nBpm    d
	 */
	public void aumentarBpm(){
		notificarCanalesBpm(bpm);
	}

	public void disminuirBpm(){
		notificarCanalesBpm();
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

	public Duration darDuracion() {
		return duracion;
	}

	public double darBpm() {
		return bpm;
	}
	/**
	 * 
	 * @param nDuracion    d
	 */
	public void reproducir(Duration nDuracion){
		
	}

	public void pausar(){
		
	}

	public void stop(){

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
}
