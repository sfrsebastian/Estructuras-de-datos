package Grafo;

public class Arco<K extends Comparable<K>, V, A> implements Comparable<Arco<K, V, A>> {

	//---------------------------------------
	// Atributos
	//---------------------------------------
	
	/**
	 * La informacion del arco
	 */
	private A infoArco;
	
	/**
	 * El vertice de origen del arco
	 */
	private Vertice<K, V, A> origen;
	
	/**
	 * El vertice de destino del arco
	 */
	private Vertice<K, V, A> destino;
	
	//---------------------------------------
	// Constructor
	//---------------------------------------
	
	/**
	 * Construye un nuevo arco con un vertice inicial y otro de de destino
	 * @param vOrigen El vertice de origen del arco
	 * @param vDestino El vertice de destino del arco
	 * @param info La informacion del arco
	 */
	public Arco(Vertice<K,V,A> vOrigen, Vertice<K,V,A> vDestino, A info){
		origen = vOrigen;
		destino = vDestino;
		infoArco = info;
	}
	
	//---------------------------------------
	// Metodos
	//---------------------------------------
	
	@Override
	public int compareTo(Arco<K, V, A> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Retorna el vertice de destino del arco
	 * @return Vertice el vertice de destino del arco
	 */
	public Vertice<K, V, A> darDestino() {
		return destino;
	}

}
