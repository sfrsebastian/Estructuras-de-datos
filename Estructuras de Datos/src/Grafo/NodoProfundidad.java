package Grafo;

public class NodoProfundidad<K extends Comparable<K>,V,A> extends Nodo<K,V,A> {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * La profundidad del nodo
	 */
	private int profundidad;
	
	//--------------------
	//CONSTRUCTOR
	//--------------------
	/**
	 * Crea un nuevo Nodo profundidad
	 * @param nVertice El vertice del nodo
	 * @param nArco El arco de llegada del nodo
	 * @param nProfundidad La profundidad del nodo
	 */
	public NodoProfundidad(Vertice<K, V, A> nVertice, Arco<K, V, A> nArco, int nProfundidad) {
		super(nVertice, nArco);
		profundidad = nProfundidad;
	}
	
	//--------------------
	//METODOS
	//--------------------
	/**
	 * Retorna la profundidad del nodo
	 * @return
	 */
	public int getProfundidad() {
		return profundidad;
	}
}
