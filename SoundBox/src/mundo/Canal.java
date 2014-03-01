package mundo;

import java.io.Serializable;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Lista.Lista;
import ListaEncadenada.ListaEncadenada;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Canal implements Comparable<Canal>,Serializable {
	/**
	 * COnstante para representar un canal sin nomrbe
	 */
	private static final String DEFAULT = "Sin Nombre";
	
	/**
	 * La lista de sonidos del canal
	 */
	private Lista <Sample> sonidos;
	
	/**
	 * EL nombre del canal
	 */
	private String nombre;
	
	/**
	 * El bpm del canal
	 */
	private double bpm;
	
	/**
	 * El sonido reproduciendose actualmente
	 */
	private int sonidoActual;
	
	/**
	 * boolean que determina si se termino la reproduccion
	 */
	private boolean termino;
	
	/**
	 * El reproductor del canal
	 */
	private MediaPlayer player;

	
	//Constructor
	/**
	 * Se crea un nuevo canal con el nombre dado por parametro
	 * @param nNombre EL nombre del canal
	 */
	public Canal(String nNombre){
		nombre = nNombre;
		sonidos = new ListaEncadenada<Sample>();
		bpm = 1.0;
		sonidoActual = 0;
		termino = false;
	}
	/**
	 * Crea un nuevo canal con el nombre determinado
	 */
	public Canal(){
		nombre = DEFAULT;
		sonidos = new ListaEncadenada<Sample>();
		bpm = 1.0;
		sonidoActual = 0;
		termino = false;
	}

	/**
	 * Agrega el sonido dado por parametro al canal
	 * @param Sample El sonido a agregar
	 */
	public void agregarSonido(Object[] samples){
		for (int i = 0; i < samples.length; i++) {
			Sample s = (Sample)samples[i];
			sonidos.agregar(s);
		}
	}

	/**
	 * Aumenta el bpm de reproduccion en un cantidad de 0.1
	 */
	public void aumentarBpm(){
		bpm+=0.1;
		bpm = Math.floor(bpm * 10) / 10;
		if(player != null)
			player.setRate(bpm);
		
	}
	
	/**
	 * Disminuye el bpm de reproduccion en una cantidad de 0.1
	 */
	public void disminuirBpm(){
		bpm-=0.1;
		bpm = Math.floor(bpm * 10) / 10;
		if(player != null)
			player.setRate(bpm);
	}
	/**
	 * Busca el sonido dado por parametro
	 * @param Sample El sonido encontrado, null de lo contrario.
	 */
	public Sample buscarSonido(Sample nSample){
		return sonidos.buscar(nSample);
	}

	/**
	 * Retorna los sonidos del canal.
	 * @return EL arreglo de sonidos.
	 */
	public Object[] darSonidos(){
		return sonidos.darArreglo();
	}

	/**
	 * Elimina el sonido dado por parametro
	 * @param Sample El sonido eliminado.
	 */
	public Sample eliminarSonido(Sample nSample){
		sonidos.eliminar(nSample);
		return nSample;
	}

	/**
	 * Retorna la duracion totoal del canal.
	 * @return La duracion total del canal
	 */
	public double darDuracionTotal() {
		Object[] recorrido = sonidos.darArreglo();
		double duracion = 0;
		for(int i = 0; i< recorrido.length;i++){
			Media sonidoActual = new Media(((Sample)recorrido[i]).darSrc());
			duracion+=sonidoActual.getDuration().toSeconds();
		}
		return duracion;
	}

	/**
	 * Retorna el nombre del canal
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Pausa el canal
	 */
	public void pausar() {
		if(player != null)
			player.pause();
	}

	/**
	 * Para el canal. El contado de sonido actual se reinicia.
	 */
	public void stop() {
		if(player != null)
			player.stop();
		sonidoActual = 0;
	}

	/**
	 * Reproduce el canal. Se reprduce un sonido seguido del otro.
	 */
	public void reproducir() {
		JFXPanel panel = new JFXPanel();
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

	
	/**
	 * Inicializa el reproductor
	 * @param reproduciendo
	 */
	private void inicializarPlayer(Media reproduciendo) {
		player = new MediaPlayer(reproduciendo);
		player.setRate(bpm);
	}

	/**
	 * Metodo para comprar dos canales.
	 */
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
	
	/**
	 * Metodo para cambiar el nombre del canal
	 * @param nNombre
	 */
	public void cambiarNombre(String nNombre){
		nombre = nNombre;
	}
	
	/**
	 * Metodo to string. Retorna el nombre del canal.
	 */
	public String toString(){
		return nombre;
	}
	
	/**
	 * retorna el bpm del canal
	 * @return
	 */
	public Double darBPM() {
		return bpm;
	}
	
	

}