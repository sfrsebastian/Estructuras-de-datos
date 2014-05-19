package mundo;

import Grafo.IInfoArco;

public class InfoCostos implements IInfoArco {
	//--------------------
	//CONSTANTES
	//--------------------
	/**
	 * Constante que determina el costo de distancia
	 */
	public static final String DISTANCIA = "Distancia";
	
	/**
	 *Constante que determina el costo de tiempo
	 */
	public static final String TIEMPO = "Tiempo";
	
	/**
	 *Constante que determina el costo de rating
	 */
	public static final String RATING = "Rating";
	
	/**
	 *Constante que determina el costo de vuelos tardios
	 */
	public static final String TARDIOS = "Vuelos Tardios";
	
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * La distancia del vuelo
	 */
	private float distancia;
	
	/**
	 * El tiempo del vuelo
	 */
	private float tiempo;
	
	/**
	 * El rating del vuelo
	 */
	private float rating;
	
	/**
	 * Los vuelos tardios del vuelo
	 */
	private float tardios;
	
	/**
	 * El vuelo involucrado
	 */
	private Vuelo vuelo;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea una nueva clase con los costos de vuelos
	 * @param nVuelo El vuelo con los costos
	 */
	public InfoCostos(Vuelo nVuelo){
		distancia = vuelo.getDistancia();
		tiempo = vuelo.getDuracion();
		rating = (float) vuelo.getRating();
		tardios = vuelo.getTardios();
		vuelo = nVuelo;
	}
	
	@Override
	public float getCosto() {
		return getCosto(" ");
	}

	@Override
	public float getCosto(String arg) {
		if(arg.equals(RATING)){
			return rating;
		}
		else if(arg.equals(TARDIOS)){
			return tardios;
		}
		else if(arg.equals(TIEMPO)){
			return tiempo;
		}
		else{
			return distancia;
		}
	}

	/**
	 * Retorna el vuelo del arco
	 * @return
	 */
	public Vuelo getVuelo(){
		return vuelo;
	}
	
	@Override
	public int compareTo(IInfoArco o) {
		return ((InfoCostos) o).getVuelo().compareTo(vuelo);
	}
}
