package testGrafo;

import Grafo.IInfoArco;

public class Informacion implements IInfoArco {
	
	private float costo;
	public Informacion(float nCosto){
		costo = nCosto;
	}
	public int compareTo(IInfoArco o) {
		if(costo>o.getCosto()){
			return 1;
		}
		else if(costo<o.getCosto()){
			return -1;
		}
		else{
			return 0;
		}
	}

	@Override
	public float getCosto() {
		return costo;
	}
	@Override
	public float getCosto(String criterio) {
		return getCosto();
	}

}
