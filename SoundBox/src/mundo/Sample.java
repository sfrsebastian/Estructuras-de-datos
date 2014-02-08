package mundo;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Sample implements ISonido, Comparable<Sample> {

	private String nombre;
	private File archivo;
	private double bpm;
	private MediaPlayer player;
	private Categoria categoria;
	private Duration estadoDuracion;
	
	public Sample(File f, Categoria nCategoria){
		JFXPanel fxPanel = new JFXPanel();
		bpm = 1.0;	
		categoria = nCategoria;
		archivo = f;
		nombre = archivo.getName();
		estadoDuracion = new Duration(0);
		Media sample = new Media(archivo.toURI().toString());
		player= new MediaPlayer(sample);
		verificarInvariante();
	}

	@Override
	public Duration darDuracion() {
		return player.getTotalDuration();
	}

	@Override
	public String darNombre() {
		return nombre;
	}

	public String cambiarNombre(String nNombre){
		nombre = nNombre;
		return nNombre;
	}
	
	public Categoria darCategoria(){
		return categoria;
	}
	
	public Categoria cambiarCategoria(Categoria nCategoria){
		categoria = nCategoria;
		return nCategoria;
	}
	@Override
	public Duration pausar() {
		player.pause();
		estadoDuracion = player.getCurrentTime();
		return estadoDuracion;
	}
	
	public void reproducir(Duration nDuracion, double nBpm) {
		player.seek(nDuracion);
		bpm = Math.floor(nBpm* 10) / 10;
		player.setRate(bpm);
		verificarInvariante();
		player.play();
	}

	@Override
	public void stop() {
		player.stop();
	}
	
	@Override
	public int compareTo(Sample aComparar) {
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
	
	private void verificarInvariante(){
		assert bpm>0: "El bpm debe ser mayor a cero";
	}
}