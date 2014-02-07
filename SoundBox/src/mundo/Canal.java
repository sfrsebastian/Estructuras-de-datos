package mundo;

import javax.xml.datatype.Duration;

import Lista.Lista;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Canal implements ISonido {
	public static final String DEFAULT = "Sin Nombre";
	private Lista <Sample> sonidos;
	private String nombre;
	

	public Canal(String nNombre){
		nombre = nNombre;
		sonidos = new Lista<Sample>();
	}
	
	public Canal(){
		nombre = DEFAULT;
		sonidos = new Lista<Sample>();
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
		return null;
	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample eliminarSonido(Sample nSample){
		return null;
	}

	@Override
	public Duration darDuracion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String darNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration pausar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reproducir(Duration nDuracion, int bpm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}