package Grafo;

public class NodoNivel<K extends Comparable<K>, V extends Comparable<V>, A extends IInfoArco> extends Nodo<K,V,A> implements Comparable<NodoNivel<K,V,A>> {
	//--------------------
	//ATRIBUTOS
	//--------------------
	/**
	 * Identificador de la clase
	 */
	private static final long serialVersionUID = 2662241137223799096L;
	
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

	@Override
	public int compareTo(NodoNivel<K, V, A> o) {
		if(nivel>o.getNivel()){
			return 1;
		}
		else if(nivel<o.getNivel()){
			return -1;
		}
		else{
			return 0;
		}
	}
}
