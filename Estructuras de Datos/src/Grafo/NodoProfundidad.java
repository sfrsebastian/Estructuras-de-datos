package Grafo;

public class NodoProfundidad<K extends Comparable<K>, V, A extends IInfoArco> extends Nodo<K,V,A> implements Comparable<NodoProfundidad<K, V, A>>{
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Identificador de clase
	 */
	private static final long serialVersionUID = 99675820239880077L;
	
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

	@Override
	public int compareTo(NodoProfundidad<K, V, A> o) {
		if(profundidad>o.getProfundidad()){
			return 1;
		}
		else if(profundidad<o.getProfundidad()){
			return -1;
		}
		else{
			return 0;
		}
	}
}
