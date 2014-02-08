package mundo;

import javafx.util.Duration;





/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:36 PM
 */
public interface ISonido {
	
	public Duration darDuracionTotal();

	public String darNombre();

	public void pausar();

	/**
	 * 
	 * @param Duration
	 * @param bpm
	 */
	public void reproducir();

	public void stop();

}