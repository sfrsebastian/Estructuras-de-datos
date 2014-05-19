package Grafo;

import java.io.Serializable;

public interface IInfoArco extends Serializable,Comparable<IInfoArco>{
	public float getCosto(String criterio);
	
	public float getCosto();
}
