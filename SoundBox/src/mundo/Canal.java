package mundo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.util.Duration;
import Lista.Lista;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Canal implements ISonido, ActionListener,Comparable<Canal> {
	public static final String DEFAULT = "Sin Nombre";
	public static final String REPRODUCIRCANALES = "ReproducirCanales";
	private Lista <Sample> sonidos;
	private String nombre;
	private int id;
	private double bpm;
	

	public Canal(String nNombre){
		nombre = nNombre;
		sonidos = new Lista<Sample>();
		bpm = 1.0;
	}
	
	public Canal(){
		nombre = DEFAULT;
		sonidos = new Lista<Sample>();
		bpm = 1.0;
	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample agregarSonido(Sample nSample){
		sonidos.agregar(nSample);
		return nSample;
	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample buscarSonido(Sample nSample){
		return sonidos.buscar(nSample);
	}

	public Sample[] darSonidos(){
		return (Sample[]) sonidos.darArreglo();
	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample eliminarSonido(Sample nSample){
		sonidos.eliminar(nSample);
		return nSample;
	}

	@Override
	public Duration darDuracion() {
		Sample[] recorrido = (Sample[]) sonidos.darArreglo();
		double duracion = 0;
		for(int i = 0; i< recorrido.length;i++){
			duracion+=recorrido[i].darDuracion().toSeconds();
		}
		Duration respuesta = new Duration(duracion);
		return respuesta;
	}

	@Override
	public String darNombre() {
		return nombre;
	}

	@Override
	public Duration pausar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reproducir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public int compareTo(Canal o) {
		// TODO Auto-generated method stub
		return 0;
	}

}