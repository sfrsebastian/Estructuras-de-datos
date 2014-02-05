package mundo;


/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Canal extends ISonido {

	private Sample sonidos;

	public Canal(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nombre
	 */
	public void Canal(String nombre){

	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample agregarSonido(Sample nSample){
		return null;
	}

	/**
	 * 
	 * @param Sample
	 */
	public Sample buscarSonido(Sample nSample){
		return null;
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

}