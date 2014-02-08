package mundo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Lista.Lista;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Sample implements ISonido, Comparable<Sample>, ActionListener {

	private static final String REPRODUCIRSONIDO = "Reproducir";
	private static final String PAUSARSONIDO = "Pausar";
	private static final String STOPSONIDO = "Stop";
	private String nombre;
	private File archivo;
	private double bpm;
	private MediaPlayer player;
	private Lista <Categoria> categorias;
	private Duration estadoDuracion;
	
	public Sample(File f, Categoria nCategoria){
		JFXPanel fxPanel = new JFXPanel();
		bpm = 1.0;
		categorias = new Lista <Categoria>();
		categorias.agregar(nCategoria);
		archivo = f;
		nombre = archivo.getName();
		estadoDuracion = new Duration(0);
		Media sample = new Media(archivo.toURI().toString());
		player= new MediaPlayer(sample);
		verificarInvariante();
	}

	@Override
	public Duration darDuracionTotal() {
		return player.getTotalDuration();
	}

	public Duration cambiarEstadoDuracion(Duration nDuracion){
		estadoDuracion = nDuracion;
		return estadoDuracion;
	}
	@Override
	public String darNombre() {
		return nombre;
	}

	public String cambiarNombre(String nNombre){
		nombre = nNombre;
		return nNombre;
	}
	
	public double darBpm(){
		return bpm;
	}
	
	public double aumentarBpm(){
		bpm+=0.1;
		bpm = Math.floor(bpm * 10) / 10;
		player.setRate(bpm);
		return bpm;
	}
	
	public double disminuirBpm(){
		bpm-=0.1;
		bpm = Math.floor(bpm * 10) / 10;
		player.setRate(bpm);
		return bpm;
	}
	public Categoria[] darCategoria(){
		return (Categoria[]) categorias.darArreglo();
	}
	
	public Categoria agregarCategoria(Categoria nCategoria){
		return categorias.agregar(nCategoria);
	}
	
	public Categoria eliminarCategoria(Categoria categoria) {
		return categorias.eliminar(categoria);	
	}

	public void reproducir() {
		
	}
	
	@Override
	public Duration pausar() {
		player.pause();
		estadoDuracion = player.getCurrentTime();	
		return estadoDuracion;
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

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(REPRODUCIRSONIDO)){
			reproducir();
		}
		else if(comando.equals(PAUSARSONIDO)){
			pausar();
		}
		else if(comando.equals(STOPSONIDO)){
			stop();
		}
		
	}
	private void verificarInvariante(){
		assert bpm>0: "El bpm debe ser mayor a cero";
	}
}