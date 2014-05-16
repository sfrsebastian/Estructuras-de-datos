package Grafo;

import java.util.Comparator;

public class ComparadorElemento<K extends Comparable<K>,V extends Comparable<V>,A extends IInfoArco> implements Comparator<NodoDijkstra<K,V,A>> {

	@Override
	public int compare(NodoDijkstra<K, V, A> o1, NodoDijkstra<K, V, A> o2) {
		return o1.getVertice().compareTo(o2.getVertice());
	}

}
