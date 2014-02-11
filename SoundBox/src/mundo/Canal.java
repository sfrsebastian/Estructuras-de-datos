package mundo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import Lista.Lista;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Canal implements ISonido, ActionListener,Comparable<Canal>,Serializable {
	public static final String AUMENTAR_BPM = "+";
	public static final String DISMINUIR_BPM = "-";
	public static final String PLAY = "Play";
	public static final String PAUSE = "Pause";
	public static final String STOP = "Stop";
	private static final String DEFAULT = "Sin Nombre";
	
	private Lista <Sample> sonidos;
	private String nombre;
	private double bpm;
	private int sonidoActual;
	private boolean termino;
	private MediaPlayer player;

	public Canal(String nNombre){
		nombre = nNombre;
		sonidos = new Lista<Sample>();
		bpm = 1.0;
		sonidoActual = 0;
		termino = false;
	}
	
	public Canal(){
		nombre = DEFAULT;
		sonidos = new Lista<Sample>();
		bpm = 1.0;
		sonidoActual = 0;
		termino = false;
	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample agregarSonido(Sample nSample){
		sonidos.agregar(nSample);
		return nSample;
	}

	public void aumentarBpm(){
		bpm+=0.1;
		bpm = Math.floor(bpm * 10) / 10;
		player.setRate(bpm);
	}
	
	public void disminuirBpm(){
		bpm-=0.1;
		bpm = Math.floor(bpm * 10) / 10;
		player.setRate(bpm);
	}
	/**
	 * 
	 * @param Sample
	 */
	public Sample buscarSonido(Sample nSample){
		return sonidos.buscar(nSample);
	}

	public Object[] darSonidos(){
		return sonidos.darArreglo();
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
	public double darDuracionTotal() {
		Object[] recorrido = sonidos.darArreglo();
		double duracion = 0;
		for(int i = 0; i< recorrido.length;i++){
			Media sonidoActual = new Media(((Sample)recorrido[i]).darSrc());
			duracion+=sonidoActual.getDuration().toSeconds();
		}
		return duracion;
	}

	@Override
	public String darNombre() {
		return nombre;
	}

	@Override
	public void pausar() {
		player.pause();
	}

	@Override
	public void stop() {
		player.stop();
		sonidoActual = 0;
	}

	public void reproducir() {
		Object[] lista = sonidos.darArreglo();
		if(sonidoActual>=lista.length){
			termino = true;
		}
		if(!termino){
			Media reproduciendo = new Media (((Sample)lista[sonidoActual]).darSrc());
			inicializarPlayer(reproduciendo);
			player.play();
			player.setOnEndOfMedia(new Runnable(){
				public void run() {
					sonidoActual++;
					reproducir();
				}
			});	
		}
		else{
			sonidoActual = 0;
			termino = false;
		}
	}

	
	private void inicializarPlayer(Media reproduciendo) {
		player = new MediaPlayer(reproduciendo);
		player.setRate(bpm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando  = e.getActionCommand();
		if(comando.equals(AUMENTAR_BPM)){
			aumentarBpm();
		}
		else if(comando.equals(DISMINUIR_BPM)){
			disminuirBpm();
		}
		else if(comando.equals(PLAY)){
			reproducir();
		}
		else if(comando.equals(PAUSE)){
			pausar();
		}
		else{
			stop();
		}
	}

	@Override
	public int compareTo(Canal aComparar) {
		if(nombre.compareTo(aComparar.darNombre())<0){
			return -1;
		}
		else if(nombre.compareTo(aComparar.darNombre())>0){
			return 1;
		}
		else{
			return 0;
		}	
	}
	
	public String toString(){
		return nombre;
	}

}