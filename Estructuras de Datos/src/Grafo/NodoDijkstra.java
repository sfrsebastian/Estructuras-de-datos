package Grafo;

public class NodoDijkstra<K extends Comparable<K>, V, A extends IInfoArco> extends Nodo<K,V,A> implements Comparable<NodoDijkstra<K,V,A>>{
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Identificador de la clase
	 */
	private static final long serialVersionUID = 5330103687213876080L;
	
	/**
	 * El costo acumulado en el Nodo
	 */
	public float costo;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo Nodo Dijkstra con costo asociado
	 * @param nVertice El vertice del nodo
	 * @param nArco El arco de llegada del nodo
	 * @param nCosto El costo acumulado dellegada.
	 */
	public NodoDijkstra(Vertice<K, V, A> nVertice, Arco<K, V, A> nArco,float nCosto) {
		super(nVertice, nArco);
		costo = nCosto;
	}
	
	//--------------------
	//METODOS
	//--------------------
	/**
	 * Retorna el costo del nodo
	 * @return El costo del nodo
	 */
	public float getCosto() {
		return costo;
	}

	@Override
	public int compareTo(NodoDijkstra<K, V, A> o) {
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
}
