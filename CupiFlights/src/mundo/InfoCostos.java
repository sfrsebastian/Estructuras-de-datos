package mundo;

import Grafo.IInfoArco;

public class InfoCostos implements IInfoArco {
	
	public static final String DISTANCIA = "Distancia";
	public static final String TIEMPO = "Tiempo";
	public static final String RATING = "Rating";
	public static final String TARDIOS = "Vuelos Tardios";
	
	private float distancia;
	private float tiempo;
	private float rating;
	private float tardios;
	private Vuelo vuelo;
	
	public InfoCostos(float nDistancia, float nTiempo, float nRating, float nTardios, Vuelo nVuelo){
		distancia = nDistancia;
		tiempo = nTiempo;
		rating = nRating;
		tardios = nTardios;
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

	public Vuelo getVuelo(){
		return vuelo;
	}
	
	@Override
	public int compareTo(IInfoArco o) {
		return ((InfoCostos) o).getVuelo().compareTo(vuelo);
	}
}
