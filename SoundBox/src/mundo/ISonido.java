package mundo;

import javafx.util.Duration;





/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:36 PM
 */
public interface ISonido {
	
	public Duration darDuracion();

	public String darNombre();

	public Duration pausar();

	/**
	 * 
	 * @param Duration
	 * @param bpm
	 */
	public void reproducir(Duration nDuracion, double nBpm);

	public void stop();

}