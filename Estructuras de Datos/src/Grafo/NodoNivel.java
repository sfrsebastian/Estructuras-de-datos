package Grafo;

public class NodoNivel<K,V,A> extends Nodo<K,V,A> {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * El nivel del nodo
	 */
	private int nivel;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo Nodo nivel
	 * @param nVertice El vertice del nodo
	 * @param nArco El arco de llegada del nodo
	 * @param nNivel El nivel del nodo
	 */
	public NodoNivel(Vertice<K, V, A> nVertice, Arco<K, V, A> nArco, int nNivel) {
		super(nVertice, nArco);
		nivel = nNivel;
	}

	//--------------------
	//METODOS
	//--------------------
	/**
	 * Retorna el nivel del nodo
	 * @return El nivel del nodo
	 */
	public int getNivel() {
		return nivel;
	}
}
